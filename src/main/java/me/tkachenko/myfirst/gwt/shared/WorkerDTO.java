package me.tkachenko.myfirst.gwt.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SergejT on 01.08.2016.
 */

/**
 * Transfer Object to hold the details for Project
 */
public class WorkerDTO implements Serializable {
    private String name;
    private String firstname;
    private String lastname;
    private int groups;
    private String adr = "Gadukino_city";
    private int kurs;
    private int ball;
    private int numberinv;
    private Date abc;
    private int def;

    public WorkerDTO() {
    }

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
