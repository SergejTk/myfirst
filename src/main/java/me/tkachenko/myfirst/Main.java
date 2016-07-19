package me.tkachenko.myfirst;

import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.workersgenerator.Worker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by ִלטענטי on 19.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        List<Worker> list;
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        WorkersDAOListAllWorkers test = (WorkersDAOListAllWorkers) context.getBean("getListAllWorkers");
        list = test.getListWorkers();

    }
}
