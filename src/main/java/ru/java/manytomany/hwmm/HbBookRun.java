package ru.java.manytomany.hwmm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbBookRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = Book.of("Добрые предзнаменования");
            Book two = Book.of("Гарри Поттер и Узник Азкабана");
            Book three = Book.of("Мастер и Маргарита");
            Book four = Book.of("Обловов");

            Author first = Author.of("Нил Гейман");
            Author second = Author.of("Терри Пратчет");
            first.getBooks().add(one);
            second.getBooks().add(one);

            Author third = Author.of("Джоан Роулинг");
            Author fourth = Author.of("Михаил Булгаков");
            Author fifth = Author.of("Иван Гончаров");
            third.getBooks().add(two);
            fourth.getBooks().add(three);
            fifth.getBooks().add(four);

            session.save(first);
            session.save(second);
            session.save(third);
            session.save(fourth);
            session.save(fifth);

            Author a = session.get(Author.class, 4);
            session.remove(a);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
