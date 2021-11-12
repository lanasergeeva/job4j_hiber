package ru.java.hql.hwfetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbRunAdd {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Vacancy vOne = Vacancy.of("SQL admin", "Saratov", 76356.99);
            Vacancy vTwo = Vacancy.of("Java developer", "Simferopol", 75325.80);
            session.save(vOne);
            session.save(vTwo);

            BaseOfVacancies bs = BaseOfVacancies.of("ivan_programmer");
            bs.addVac(vOne);
            bs.addVac(vTwo);
            session.save(bs);

            CandidateHW candidateHW = CandidateHW.of("Ivan", 2, 75000);
            candidateHW.setBaseOfVacancies(bs);
            session.save(candidateHW);

            Vacancy vThree = Vacancy.of("Abap developer", "Tula", 40000.00);
            Vacancy vFour = Vacancy.of("Java junior", "Pscov", 52000.58);
            session.save(vThree);
            session.save(vFour);

            BaseOfVacancies bs2 = BaseOfVacancies.of("maximka");
            bs2.addVac(vThree);
            bs2.addVac(vFour);
            session.save(bs2);

            CandidateHW second = CandidateHW.of("Maxim", 0, 40000);
            second.setBaseOfVacancies(bs2);
            session.save(second);


            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
