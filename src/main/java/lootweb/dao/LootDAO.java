package lootweb.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lootweb.domain.Boss;
import lootweb.domain.Loot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//TODO: comment, clean up
@Repository
public class LootDAO {

    private SessionFactory sessionFactory;

    private SessionFactory getSessionFactory() {
        if(sessionFactory != null) {
            return sessionFactory;
        }
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .addAnnotatedClass(Boss.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        this.sessionFactory = sessionFactory;
        return sessionFactory;
    }

    public List<Boss> getBosses() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Boss> criteria = builder.createQuery(Boss.class);
        criteria.from(Boss.class);
        List<Boss> bosses = session.createQuery(criteria).getResultList();
        session.close();
        return bosses;
    }

    public List<Loot> getLootByBossName(final String bossName) {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Loot> criteria = builder.createQuery(Loot.class);
        Root<Loot> root = criteria.from(Loot.class);
        criteria.where(builder.equal(root.get("boss").get("name"), bossName));
        List<Loot> loots = session.createQuery(criteria).getResultList();
        session.close();
        return loots;
    }

    public List<Loot> getLoot() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Loot> criteria = builder.createQuery(Loot.class);
        criteria.from(Loot.class);
        List<Loot> loots = session.createQuery(criteria).getResultList();
        session.close();
        return loots;
    }

    public void addLoot(final List<Loot> loots) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        for(Loot loot : loots) {
            session.save(loot);
        }
        session.getTransaction().commit();
        session.close();
    }
}
