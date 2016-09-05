package me.tkachenko.myfirst.service;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.workersdao.WorkersDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sergej on 20.07.2016.
 */

@Service
public class WorkersServiceImpl implements WorkersService {
    @Resource
    private WorkersDAO workersDAO;


    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {

        return workersDAO.getAllWorkers();
    }


    @Transactional(readOnly = true)
    public List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc) {

        return workersDAO.getPartWorkers(start, length, columnName, isAsc);
    }


    @Transactional(readOnly = true)
    public Number getTotalRow() {

        return workersDAO.getTotalRow();
    }

    @Transactional
    public void updateWorker(Worker worker) {
        workersDAO.updateWorker(worker);
    }

    @Transactional
    public void deleteWorker(Worker worker) {
        workersDAO.deleteWorker(worker);
    }
}
