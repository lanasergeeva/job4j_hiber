package ru.java.hql.hwfetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRunFetch {
    public static void main(String[] args) {
        List<CandidateHW> list = null;
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            try {
                SessionFactory sf = new MetadataSources(registry)
                        .buildMetadata()
                        .buildSessionFactory();
                Session session = sf.openSession();
                session.beginTransaction();
                list = session.createQuery(
                        "select distinct c from CandidateHW c "
                                + "join fetch c.baseOfVacancies b "
                                + "join fetch b.vacList v ").list();
                session.getTransaction().commit();
                session.close();
            }  catch (Exception e) {
                e.printStackTrace();
            } finally {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            System.out.println(list);
        }
    }

