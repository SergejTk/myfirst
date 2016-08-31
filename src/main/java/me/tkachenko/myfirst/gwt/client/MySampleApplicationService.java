package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {


    /**
     * @return Returns an list containing all of the elements
     */
    List<WorkerDTO> getListWorkers();


    /**
     * @param start      Number of the first recording range
     * @param length     Amount rows of range
     * @param columnName Column is the sorting
     * @param isAsc      Sorting order (true:  Ascending,   false: descending)
     * @return Returns the list of the specified range
     */
    List<WorkerDTO> getPartWorkers(int start, int length, String columnName, boolean isAsc);


    /**
     * @return Returns the total number of rows of the list
     */
    Number getTotalRow();


    void updateWorker(WorkerDTO workerDTO);

    /**
     * Utility/Convenience class.
     * Use MySampleApplicationService.App.getInstance() to access static instance of MySampleApplicationServiceAsync
     */
    public static class App {
        private static MySampleApplicationServiceAsync ourInstance = GWT.create(MySampleApplicationService.class);

        public static synchronized MySampleApplicationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
