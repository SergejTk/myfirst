package me.tkachenko.myfirst.utils;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;

import me.tkachenko.myfirst.model.Worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Sergej on 11.07.2016.
 */
public class WorkersGenerator {

    public static void createWorker(String... args) {


        String[] names = new String[]{"Vanja", "Petja", "Pafnutiy", "Modest", "Osja", "Lev", "Evlampij", "Sigizmund", "Foma"};
        String[] firstnames = new String[]{"Pupkin", "Aaa", "Bbb", "Ccc", "Ddd", "Fff", "Ggg", "Hhh", "Zuzin", "Svintus"};
        String[] lastnames = new String[]{"Qwer", "Asdf", "Zxcv", "Bnmm", "Jkld", "Dghgj", "Fofon", "Coco", "Rrrrr", "Pushh", "Xyz", "Lari", "Givi"};

        Random r = new Random();
        String path = "C:\\workers.csv";
        if (args.length != 0) path = args[0];


        try (PrintWriter pw = new PrintWriter(path)) {

            for (int i = 1; i <= 10000; i++) {
                Worker worker = new Worker();
                worker.setName(names[i % names.length]);
                worker.setFirstname(firstnames[i % firstnames.length]);
                worker.setLastname(lastnames[i % lastnames.length]);
                worker.setKurs(r.nextInt(4) + 1);
                worker.setNumberinv(Math.abs(r.nextInt()));
                worker.setBall(r.nextInt(9) + 1);
                worker.setAbc(generateDate());
                worker.setDef(i);
                pw.printf("%s,%s,%s,%d,%s,%d,%d,%d,%tF,%d\n",

                        worker.getName(), worker.getFirstname(), worker.getLastname(), worker.getGroups(),
                        worker.getAdr(), worker.getKurs(), worker.getBall(), worker.getNumberinv(),
                        worker.getAbc(), worker.getDef());


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static Date generateDate() {
        Random random = new Random();
        int year = 1985 + random.nextInt(15);
        int month = random.nextInt(11);
        int day = random.nextInt(27) + 1;

        GregorianCalendar greg = new GregorianCalendar(year, month, day);

        return greg.getTime();
    }
}
