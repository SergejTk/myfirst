package me.tkachenko.myfirst.service;

import me.tkachenko.myfirst.model.Worker;

import java.util.List;

/**
 * Created  by Sergej on 21.07.2016.
 */
public interface WorkersService {
    List<Worker> getAllWorkers();

    List<Worker> getPartWorkers(int start, int length);

    List getTotalRow();
}
