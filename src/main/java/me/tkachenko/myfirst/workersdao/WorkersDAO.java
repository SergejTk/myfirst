package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;

import java.util.List;

/**
 * Created by alexk on 15.08.2016 22:59.
 */
public interface WorkersDAO {
    /**
     * @return Returns an list containing all of the elements
     */
    List<Worker> getAllWorkers();


    /**
     * @param start      Number of the first recording range
     * @param length     Amount rows of range
     * @param columnName Column is the sorting
     * @param isAsc      Sorting order (true:  Ascending,   false: descending)
     * @return Returns the list of the specified range
     */
    List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc);

    /**
     * @return Returns the total number of rows of the list
     */
    Number getTotalRow();
}
