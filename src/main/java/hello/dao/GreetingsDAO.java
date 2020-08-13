package hello.dao;

import hello.dto.Greeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class GreetingsDAO {

	public static SessionFactory getSessionFactory() {
		StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();

		Metadata metadata = new MetadataSources(standardServiceRegistry)
				.addAnnotatedClass(Greeting.class)
				.getMetadataBuilder()
				.build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();
		return sessionFactory;
	}

	public static Greeting getGreetingForId(final Integer id) {
		Session session = getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		Greeting greeting = session.get(Greeting.class, id);

		t.commit();
		return greeting;
	}

}
