package raf.rs.WEB_Projekat.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.security.PasswordHash;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    public UserService() {}

    @Inject
    private KorisnikRepository userRepository;

    public Korisnik addUser(Korisnik user) {
        return this.userRepository.addUser(user);
    }

    public List<Korisnik>  allUsers() {
        return this.userRepository.alUsers();
    }

    public Korisnik findUser(Integer id) {
        return this.userRepository.findUser(id);
    }

    public Korisnik findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteUser(id);
    }

    public String login(String email, String password)
    {
        String hashedPassword = PasswordHash.sha256(password);

        Korisnik user = this.userRepository.findByEmail(email);
       /* if (user == null || !PasswordHash.sha256(user.getLozinka()).equals(hashedPassword)) {
            return null;
        }*/
        if (user == null || !user.getLozinka().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("role", user.getTip())
                .withClaim("id", user.getId())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        JSONObject obj = new JSONObject(token);
        String parsed = obj.getString("token");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(parsed);

        String email = jwt.getSubject();

        Korisnik user = this.userRepository.findByEmail(email);

        if (user == null){
            return false;
        }

        if(jwt.getClaim("role").asString().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }
}
