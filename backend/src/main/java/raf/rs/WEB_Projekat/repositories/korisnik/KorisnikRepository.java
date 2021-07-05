package raf.rs.WEB_Projekat.repositories.korisnik;

import raf.rs.WEB_Projekat.entities.Korisnik;

import java.util.List;

public interface KorisnikRepository {
    public Korisnik addUser(Korisnik korisnik);
    public List<Korisnik> alUsers();
    public Korisnik findUser(Integer id);
    public void deleteUser(Integer id);
    public String checkCredentials(String email, String password);
    public Korisnik findByEmail(String email);
    public Korisnik updateUser(Korisnik user);
}
