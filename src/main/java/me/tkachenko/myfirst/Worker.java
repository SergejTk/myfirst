package me.tkachenko.myfirst;



import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Дмитрий on 11.07.2016.
 */



// заполнение таблицы БД
@Entity
@Table(name = "workers")
public class Worker {
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
    private Date abc = new Date(1992, 7, 12);
    @Id
    @Column(name = "def")
    private int def;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int getNumberinv() {
        return numberinv;
    }

    public void setNumberinv(int numberinv) {
        this.numberinv = numberinv;
    }

    public Date getAbc() {
        return abc;
    }

    public void setAbc(Date abc) {
        this.abc = abc;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
