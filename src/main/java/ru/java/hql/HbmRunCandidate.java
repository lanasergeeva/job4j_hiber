package ru.java.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;


public class HbmRunCandidate {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Ivan", 2, 2800.58);
            Candidate two = Candidate.of("Oleg", 5, 3000.99);
            Candidate three = Candidate.of("Valeriy", 1, 800.60);
            session.save(one);
            session.save(two);
            session.save(three);

            Query query = session.createQuery("from Candidate");
            for (Object st : query.list()) {
                System.out.println(st);
            }

            Query query2 = session.createQuery(
                    "update Candidate c set c.name = :newName, "
                            + "c.experience = :newExp, c.salary =:newSalary where c.id = :fId"
            );
            query2.setParameter("newName", "Victor");
            query2.setParameter("newExp", 3);
            query2.setParameter("newSalary", 1250.77);
            query2.setParameter("fId", 3);
            query2.executeUpdate();

            Query query3 = session.createQuery("from Candidate c where c.id = 1");
            System.out.println(query3.uniqueResult());

            List<Candidate> victorName = session
                    .createQuery("from Candidate c where c.name = :fName")
                    .setParameter("fName", "Victor").list();
            System.out.println(victorName);

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 2);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
