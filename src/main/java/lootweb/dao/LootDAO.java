package lootweb.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import lootweb.domain.Boss;
import lootweb.domain.Loot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//TODO: comment, clean up
public class LootDAO {

    public static SessionFactory getSessionFactory() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .addAnnotatedClass(Boss.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        return sessionFactory;
    }

    public static List<Boss> getBosses() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Boss> criteria = builder.createQuery(Boss.class);
        criteria.from(Boss.class);
        List<Boss> bosses = session.createQuery(criteria).getResultList();
        session.close();
        return bosses;
    }

    //TODO: get boss by id?

    public static List<Loot> getLoot() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Loot> criteria = builder.createQuery(Loot.class);
        criteria.from(Loot.class);
        List<Loot> loots = session.createQuery(criteria).getResultList();
        session.close();
        return loots;
    }

    public static void addLoot(final Loot loot) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(loot);
        session.getTransaction().commit();
        session.close();
    }
}
