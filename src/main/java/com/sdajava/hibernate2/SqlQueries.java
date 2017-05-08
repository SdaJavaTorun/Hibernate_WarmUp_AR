package com.sdajava.hibernate2;

import com.sdajava.hibernate2.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        } finally {
            session.close();
        }
    }
}

