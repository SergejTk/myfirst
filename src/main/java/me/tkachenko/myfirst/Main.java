package me.tkachenko.myfirst;

import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.service.WorkersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by ������� on 19.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        List<Worker> list;
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        WorkersService service = context.getBean(WorkersService.class);
        list = service.getAllWorkers();
        for (Worker worker : list)
            System.out.println(worker);
    }
}
