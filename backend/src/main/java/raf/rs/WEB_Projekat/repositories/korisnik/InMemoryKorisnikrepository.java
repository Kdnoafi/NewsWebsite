package raf.rs.WEB_Projekat.repositories.korisnik;

import raf.rs.WEB_Projekat.entities.Korisnik;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryKorisnikrepository implements KorisnikRepository {

    private static List<Korisnik> users = new CopyOnWriteArrayList<>();

    public InMemoryKorisnikrepository() {}

    @Override
    public Korisnik addUser(Korisnik user) {
        int id = users.size();
        user.setId(id);
        users.add(Math.toIntExact(id), user);

        return user;
    }

    @Override
    public List<Korisnik> alUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public Korisnik findUser(Integer id) {
        return users.get(id);
    }

    @Override
    public void deleteUser(Integer id) {
        users.remove(id.intValue());
    }

    @Override
    public String checkCredentials(String email, String password) {
        return null;
    }

    @Override
    public Korisnik findByEmail(String email) {
        return null;
    }

    @Override
    public Korisnik updateUser(Korisnik user) {
        return null;
    }
}
