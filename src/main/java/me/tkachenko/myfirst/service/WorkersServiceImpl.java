package me.tkachenko.myfirst.service;

import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.workersdao.WorkersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sergej on 20.07.2016.
 */

@Service
public class WorkersServiceImpl implements WorkersService {
    @Resource
    private WorkersDAO workersDAO;
    private List<Worker> workerList;

    //@Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {

        workerList = workersDAO.getAllWorkers();
        return workerList;
    }


}
