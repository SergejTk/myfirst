package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SingleSelectionModel;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private DataGrid<WorkerDTO> tableListWorkers = new DataGrid<>();
    private List<WorkerDTO> workers = new ArrayList<>();
    final Label label = new Label();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        workers = MySampleApplicationService.App.getInstance().getListWorkers(new MyAsyncCallback());

        tableListWorkers.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        // Add column to show the name.
        TextColumn<WorkerDTO> nameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.name;
            }
        };
        tableListWorkers.addColumn(nameColumn, "Name");

        // Add column to show the firstname.
        TextColumn<WorkerDTO> firstnameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.firstname;
            }
        };
        tableListWorkers.addColumn(firstnameColumn, "Firstname");

        // Add column to show the lastname.
        TextColumn<WorkerDTO> lastnameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.lastname;
            }
        };
        tableListWorkers.addColumn(lastnameColumn, "Lastname");

        final SingleSelectionModel<WorkerDTO> selectionModel = new SingleSelectionModel<WorkerDTO>();
        tableListWorkers.setSelectionModel(selectionModel);
        tableListWorkers.setRowCount(workers.size(), true);

        tableListWorkers.setRowData(0, workers);


        RootPanel.get().add(new Label("It is just  the  TEXT for TEST"));
        RootLayoutPanel rootPanel = RootLayoutPanel.get();
        DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.PX);
        layout.addNorth(new HTMLPanel("h1", "Workers List"), 80);
        layout.add(tableListWorkers);
        rootPanel.add(layout);
        //RootPanel.get().add(tableListWorkers);
        RootPanel.get("workersList").add(new Label("It is just the text"));

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



}
