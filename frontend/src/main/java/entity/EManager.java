package entity;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class EManager {
    Injector injector = Guice.createInjector(new JpaPersistModule("persistenceUnitName"));
    Configuration cfh;

    public class MyInitializer {
        @Inject
        MyInitializer(PersistService service) {
            service.start();

            // At this point JPA is started and ready.
        }
    }

    public class MyService {
        @Inject
        EntityManager em;

        @Transactional
        public void createNewPerson() {
            em.persist(new User());
        }
    }
}

