package raf.rs.WEB_Projekat.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.rs.WEB_Projekat.filters.AuthFilter;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;
import raf.rs.WEB_Projekat.repositories.komentar.CommentRepository;
import raf.rs.WEB_Projekat.repositories.komentar.MySqlCommentRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;
import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;
import raf.rs.WEB_Projekat.services.ArticleService;
import raf.rs.WEB_Projekat.services.CategoryService;
import raf.rs.WEB_Projekat.services.CommentService;
import raf.rs.WEB_Projekat.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class ApiConfiguration extends ResourceConfig {

    public ApiConfiguration() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlKorisnikRepository.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bind(MySqlVestRepository.class).to(VestRepository.class).in(Singleton.class);
                this.bind(MySqlKategorijaRepository.class).to(KategorijaRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class).in(Singleton.class);
                this.bindAsContract(ArticleService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(CommentService.class);
            }
        };
        register(binder);

        packages("raf.rs.WEB_Projekat");
    }
}
