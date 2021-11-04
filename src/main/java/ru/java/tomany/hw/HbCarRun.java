package ru.java.tomany.hw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbCarRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            MarkCar mark = MarkCar.of("Hyunday");
            ModelCar model1 = ModelCar.of("Accent");
            ModelCar model2 = ModelCar.of("Elantra");
            ModelCar model3 = ModelCar.of("Getz");
            ModelCar model4 = ModelCar.of("Solaris");
            ModelCar model5 = ModelCar.of("Kona");
            mark.addModel(model1);
            mark.addModel(model2);
            mark.addModel(model3);
            mark.addModel(model4);
            mark.addModel(model5);
            session.save(mark);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
