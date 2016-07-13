package me.tkachenko.myfirst;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by ִלטענטי on 11.07.2016.
 */
public class WorkersGenerator {

    public static void main(String[] args){

        //SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Session session = sessionFactory.openSession();
        String[] names = new String[]{"Vanja", "Petja", "Pafnutiy", "Modest", "Osja", "Lev"};
        String[] firstnames = new String[]{"Pupkin", "Aaa", "Bbb", "Ccc", "Ddd", "Fff", "Ggg", "Hhh"};
        String[] lastnames = new String[]{"Qwer", "Asdf", "Zxcv", "Bnmm", "Jkld"};

        Random r = new Random();
        String path = "C:\\workers.csv";
        if(args.length != 0) path = args[0];


        //Transaction tx = session.beginTransaction();
        //session.flush();

        try (PrintWriter pw = new PrintWriter(path))
       {

            for(int i = 1; i <= 20; i++) {
                Worker worker = new Worker();
                worker.setName(names[i % names.length]);
                worker.setFirstname(firstnames[i % firstnames.length]);
                worker.setLastname(lastnames[i % lastnames.length]);
                worker.setKurs(r.nextInt(4) + 1);
                worker.setNumberinv(Math.abs(r.nextInt()));
                worker.setBall(r.nextInt(9) +1);
                worker.setAbc(dateGenerator());
                worker.setDef(i);
                pw.printf("%s,%s,%s,%d,%s,%d,%d,%d,%tF,%d\n" ,

                        worker.getName(), worker.getFirstname(), worker.getLastname(), worker.getGroups(),
                        worker.getAdr(), worker.getKurs(), worker.getBall(), worker.getNumberinv(),
                        worker.getAbc(), worker.getDef());


            }
            //session.save(worker);
            //tx.commit();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        //session.close();
        //sessionFactory.close();

    }
    private static Date dateGenerator(){
        Random random = new Random();
        int year = 1985 + random.nextInt(15);
        int month = random.nextInt(11);
        int day = random.nextInt(27) + 1;

        GregorianCalendar greg = new GregorianCalendar(year, month, day);

        return greg.getTime();
    }
}
