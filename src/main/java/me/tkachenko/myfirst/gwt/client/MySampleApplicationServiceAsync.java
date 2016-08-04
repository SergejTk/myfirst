package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getListWorkers(AsyncCallback<List<WorkerDTO>> async);

    void getPartWorkers(int start, int length, AsyncCallback<List<WorkerDTO>> async);
}
