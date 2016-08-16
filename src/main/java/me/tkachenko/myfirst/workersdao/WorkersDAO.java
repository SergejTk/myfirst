package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;

import java.util.List;

/**
 * Created by alexk on 15.08.2016 22:59.
 */
public interface WorkersDAO {
    List<Worker> getAllWorkers();

    List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc);

    Number getTotalRow();
}
