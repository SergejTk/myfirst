package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import me.tkachenko.myfirst.model.Worker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private DataGrid<Worker> tableListWorkers = new DataGrid<>();
    private List<Worker> workers = new ArrayList<>();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        createWorkerForTest();

        tableListWorkers.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        tableListWorkers.setRowData(workers);

        RootPanel.get().add(tableListWorkers);

    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }

    }

    private void createWorkerForTest() {
        String[] names = new String[]{"Vanja", "Petja", "Pafnutiy", "Modest", "Osja", "Lev"};
        String[] firstnames = new String[]{"Pupkin", "Aaa", "Bbb", "Ccc", "Ddd", "Fff", "Ggg", "Hhh"};
        String[] lastnames = new String[]{"Qwer", "Asdf", "Zxcv", "Bnmm", "Jkld"};

        Random r = new Random();

        for (int i = 1; i <= 20; i++) {
            Worker worker = new Worker();
            worker.setName(names[i % names.length]);
            worker.setFirstname(firstnames[i % firstnames.length]);
            worker.setLastname(lastnames[i % lastnames.length]);
            worker.setKurs(r.nextInt(4) + 1);
            worker.setNumberinv(Math.abs(r.nextInt()));
            worker.setBall(r.nextInt(9) + 1);
            worker.setAbc(generateDate());
            worker.setDef(i);

            workers.add(worker);
        }
    }

    private Date generateDate() {
        Random random = new Random();
        int year = 85 + random.nextInt(15);
        int month = random.nextInt(11);
        int day = random.nextInt(27) + 1;

        Date greg = new Date(year, month, day);

        return greg;
    }
}
