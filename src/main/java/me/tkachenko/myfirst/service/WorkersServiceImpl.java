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



    /**
     * @return Returns an list containing all of the elements
     */
    @Transactional(readOnly = true)
    public List<Worker> getAllWorkers() {

        return workersDAO.getAllWorkers();
    }

    /**
     * @param start      Number of the first recording range
     * @param length     Amount rows of range
     * @param columnName Column is the sorting
     * @param isAsc      Sorting order (true:  Ascending,   false: descending)
     * @return Returns the list of the specified range
     */
    @Transactional(readOnly = true)
    public List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc) {

        return workersDAO.getPartWorkers(start, length, columnName, isAsc);
    }

    /**
     * @return Returns the total number of rows of the list
     */
    @Transactional(readOnly = true)
    public Number getTotalRow() {

        return workersDAO.getTotalRow();
    }
}
