package me.tkachenko.myfirst.service;

import me.tkachenko.myfirst.model.Worker;

import java.util.List;

/**
 * Created  by Sergej on 21.07.2016.
 */
public interface WorkersService {
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


    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);
}
