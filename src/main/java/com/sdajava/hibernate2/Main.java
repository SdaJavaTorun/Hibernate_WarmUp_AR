package com.sdajava.hibernate2;

import org.hibernate.*;
import static com.sdajava.hibernate2.HibernateUtil.getSession;
import static com.sdajava.hibernate2.SqlQueries.sqlInsertRow;
import static com.sdajava.hibernate2.SqlQueries.sqlSelectAll;


public class Main {

    public static void main(final String[] args) throws Exception {
        Session session = getSession();
        sqlSelectAll(session);
        sqlInsertRow(session);
        session.close();
    }
}

