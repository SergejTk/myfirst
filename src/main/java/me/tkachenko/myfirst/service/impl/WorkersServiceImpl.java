package me.tkachenko.myfirst.service.impl;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.service.WorkersService;
import me.tkachenko.myfirst.workersDAO.WorkersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ������� on 20.07.2016.
 */

@Service
public class WorkersServiceImpl implements WorkersService {
    @Autowired
    private WorkersDAO workersDAO;

    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {
        return workersDAO.getAllWorkers();
    }


}
