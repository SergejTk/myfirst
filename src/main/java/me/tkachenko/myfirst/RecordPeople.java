package me.tkachenko.myfirst;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Дмитрий on 11.07.2016.
 */



// заполнение таблицы БД
@Entity
@Table(name = "people")
public class RecordPeople {
    @Column(name = "name")
    private String name;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "groups")
    private int groups = 3;
    @Column(name = "adr")
    private String adr = "Gadukino_city";
    @Column(name = "kurs")
    private int kurs = 1;
    @Column(name = "ball")
    private int ball = 3;
    @Column(name = "numberinv")
    private int numberinv = 2325;
    @Column(name = "abc")
    private int abc = 0;
    @Id
    @Column(name = "def")
    private int def;

    private String[] names = new String[]{"Vanja", "Petja", "Pafnutiy", "Modest", "Osja", "Lev"};
    private String[] firstnames = new String[]{"Pupkin", "Aaa", "Bbb", "Ccc", "Ddd", "Fff", "Ggg", "Hhh"};
    private String[] lastnames = new String[]{"Qwer", "Asdf", "Zxcv", "Bnmm", "Jkld"};

    public void record(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        for(int i = 1; i <= 20; i++) {
            Transaction tx = session.beginTransaction();

            name = names[i % names.length];
            firstname = firstnames[i % firstnames.length];
            lastname = lastnames[i % lastnames.length];
            def = i;

            session.save(this);
            tx.commit();
        }
        session.close();
    }




}
