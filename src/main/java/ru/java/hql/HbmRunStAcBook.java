package ru.java.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunStAcBook {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            BookSec bFirst = BookSec.of("Братья Карамазовы", "nph");
            BookSec bSec = BookSec.of("Обломов", "oph");
            session.save(bFirst);
            session.save(bSec);

            Account account = Account.of("literaturoved");
            account.addBook(bFirst);
            account.addBook(bSec);
            session.save(account);

            Student student = Student.of("Ivan", 25, "Saratov");
            student.setAccount(account);
            session.save(student);



            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
