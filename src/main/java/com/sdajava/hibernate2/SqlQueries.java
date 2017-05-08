package com.sdajava.hibernate2;

import com.sdajava.hibernate2.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SqlQueries {

    public static void sqlSelectAll(Session session) {
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            List<Book> books =
                    session.createQuery(
                            "FROM " + Book.class.getName()).list();

            for (Book book : books) {
                System.out.println(" Tytuł: " + book.getTitle());
                System.out.println(" Autor: " + book.getAuthor());
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static void sqlInsertRow(Session session) throws ParseException {
        Transaction tx = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = df.parse("12-10-2011");
        Book book =
                new Book("Adam słodowy o innych",
                        "Adam Kwaśny",
                        d1,
                        "tez brakujacy opis");
        try
        {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static void sqlEditRow(Session session) throws ParseException {
        Transaction tx = null;
        try
        {
            String hsl = "update book set title =: tytul where id =: id ";
            Query query = session.createQuery(hsl);
            query.setParameter("tytul", "adas na wakacjach");
            query.setParameter("id", 3);
            int result = query.executeUpdate();
            tx = session.beginTransaction();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}

