package me.tkachenko.myfirst.workersdao;

import me.tkachenko.myfirst.model.Worker;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexk on 15.08.2016 22:59.
 */
public interface WorkersDAO {
    @Transactional(readOnly = true)
    List<Worker> getAllWorkers();

    @Transactional(readOnly = true)
    List<Worker> getPartWorkers(int start, int length, String columnName, boolean isAsc);

    @Transactional(readOnly = true)
    Number getTotalRow();
}
