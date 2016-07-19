package me.tkachenko.myfirst;

import me.tkachenko.myfirst.workersgenerator.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ִלטענטי on 15.07.2016.
 */
@Component

public class WorkersDAOListAllWorkers {

    @Autowired
    private WorkersDAO workersDAO;

    WorkersDAOListAllWorkers(WorkersDAO workersDAO) {
        this.workersDAO = workersDAO;
    }

    public List<Worker> getListWorkers() {
        return workersDAO.getAllWorkers();
    }


}
