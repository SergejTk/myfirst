package me.tkachenko.myfirst;

import me.tkachenko.myfirst.workersgenerator.WorkersGenerator;

/**
 * Created by Sergej on 19.07.2016.
 */
public class Main {

    public static void main(String[] args) {
       /* List<Worker> list;
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);


        WorkersService test = context.getBean(WorkersService.class);
        list = test.getAllWorkers();

        for (Worker worker : list)
            System.out.println(worker);
            */
        WorkersGenerator.createWorker();
    }
}
