package com.sdajava.hibernate2;

import org.hibernate.*;
import static com.sdajava.hibernate2.utility.HibernateUtil.getSession;
import static com.sdajava.hibernate2.utility.SqlQueries.*;

public class Main {

    public static void main(final String[] args) throws Exception {
        Session session = getSession();
        sqlInsertRow(session, "Adam słodowy o innych",
                            "Adam Kwaśny",
                              "12-10-2011",
                              "tez brakujacy opis");
        sqlEditRow(session,4, "Adam na wakacjach");
        //for (int i=3; i<17; i++)
          //  sqlDeleteRow(session,17);
        sqlSelectAll(session);
        session.close();
    }
}

