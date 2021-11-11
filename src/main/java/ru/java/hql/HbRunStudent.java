package ru.java.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


public class HbRunStudent {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);

            Query query = session.createQuery("from Student");
            for (Object st : query.list()) {
                System.out.println(st);
            }

            Query query2 = session.createQuery(
                    "update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId"
            );
            query2.setParameter("newAge", 22);
            query2.setParameter("newCity", "London");
            query2.setParameter("fId", 1);
            query2.executeUpdate();

            Query query3 = session.createQuery("from Student s where s.id = 1");
            System.out.println(query3.uniqueResult());

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
