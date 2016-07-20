package me.tkachenko.myfirst.service.impl;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.service.WorkersServis;
import me.tkachenko.myfirst.workersDAO.WorkersDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ִלטענטי on 20.07.2016.
 */

@Service
public class WorkersServiceImpl implements WorkersServis {
    @Resource
    private WorkersDAO workersDAO;
    private List<Worker> workerList;

    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {
        workerList = workersDAO.getAllWorkers();
        return workerList;
    }


}
