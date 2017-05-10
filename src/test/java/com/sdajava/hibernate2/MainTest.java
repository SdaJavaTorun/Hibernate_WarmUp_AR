package com.sdajava.hibernate2;

import com.sdajava.hibernate2.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Table;
import java.text.ParseException;
import java.util.List;

import static com.sdajava.hibernate2.utility.SqlQueries.sqlInsertRow;
import static com.sdajava.hibernate2.utility.SqlQueries.sqlSelectOne;
import static org.junit.Assert.*;

public class MainTest {
    private static SessionFactory factory=null;

    @BeforeClass
    public static void setup() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void shouldBeEqualListSizeWithRecordInDB() {
        try (Session session = factory.openSession()) {
            List<Book> list = session.createQuery("from Book", Book.class).list();
            assertEquals(list.size(), 23);
        }
    }

    @Test
    public void shouldBeNotNullIfOjectExist(){
        try (Session session = factory.openSession()) {
            Book book = sqlSelectOne (session, 22);
            assertNotNull(book);
        }
    }

    @Test
    public void shouldBeTrueIfInsertFailed () throws ParseException {
        try (Session session = factory.openSession()) {
            boolean result = sqlInsertRow(session,
                    "Marysia w oborze",
                    "Jean Cloud Vandal",
                    "12-12-1913",
                    "O smaotnej Marysi, ktora los opuscil");
            assertTrue(result);
        }
    }

}