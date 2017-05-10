package com.sdajava.hibernate2.utility;

import com.sdajava.hibernate2.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.sound.midi.Soundbank;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SqlQueries {

    public static Book sqlSelectOne (Session session, int bookId){
        Transaction tx = null;
        Book book = null;
        try {
            tx = session.beginTransaction();
            book = session.get(Book.class, bookId);
            System.out.println("Wyswietlenie 1 ksiazki");
            System.out.println(" Id: " + book.getId());
            System.out.println(" Tytuł: " + book.getTitle());
            System.out.println(" Autor: " + book.getAuthor());
            System.out.println(" Data: " + book.getPublished());
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return book;
    }

    public static boolean sqlSelectAll(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Book> books =
                    session.createQuery(
                            "FROM " + Book.class.getName()).list();

            for (Book book : books) {
                System.out.println(" Id: " + book.getId());
                System.out.println(" Tytuł: " + book.getTitle());
                System.out.println(" Autor: " + book.getAuthor());
                System.out.println(" Data: " + book.getPublished());
            }
            tx.commit();
            return true;
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public static boolean sqlInsertRow(
            Session session, String title, String author, String date, String desc)
            throws ParseException {
        Transaction tx = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = df.parse(date);
        Book book =
                new Book(title, author, d1, desc);
        try {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
            return true;
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public static boolean sqlEditRow(Session session, int id, String value)
            throws ParseException{
        Transaction tx = null;
        try {
            String hsl = "update " + Book.class.getName() +
                    " set title=:tytul where id=:id";
            // poczatek transakcji - zawsze przed zapytaniami
            tx = session.beginTransaction();

            Query query = session.createQuery(hsl);
            query.setParameter("tytul", value)
                 .setParameter("id", id)
                 .executeUpdate();
            // zakonczenie transakcji
            tx.commit();
            return true;
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public static boolean sqlDeleteRow (Session session, int rowId)
                                throws ParseException{
        Transaction tx = null;
        try {
            // poczatek transakcji - zawsze przed zapytaniami
            tx = session.beginTransaction();
            String hsl = "delete " + Book.class.getName() +
                    " where id=:id";

            Query query = session.createQuery(hsl);
            query.setParameter("id", rowId)
                    .executeUpdate();
            // zakonczenie transakcji
            tx.commit();
            return true;
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
}

