package ru.java.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashSet;
import java.util.Set;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Driver first = Driver.of("driver1");
            Driver second = Driver.of("driver2");
            Driver third = Driver.of("driver3");
            Driver fourth = Driver.of("driver4");
            session.save(first);
            session.save(second);
            session.save(third);
            session.save(fourth);


            Engine firstEng = Engine.of("engine1");
            Engine secEng = Engine.of("engine2");
            session.save(firstEng);
            session.save(secEng);

            Car firstCar = Car.of("Hyunday", firstEng);
            Car secCar = Car.of("Honda", secEng);
            Car thirdCar = Car.of("Lada", secEng);
            firstCar.addDriver(first);
            secCar.addDriver(second);
            thirdCar.addDriver(third);
            thirdCar.addDriver(fourth);
            session.save(firstCar);
            session.save(secCar);
            session.save(thirdCar);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

