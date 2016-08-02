package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private static DataGrid<WorkerDTO> tableListWorkers = new DataGrid<>();
    private static List<WorkerDTO> workers = new ArrayList<>();


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {


        MySampleApplicationService.App.getInstance().getListWorkers(callback);



    }

    private static void createDataGrid() {
        SimplePager pager;
        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);
        pager.setDisplay(tableListWorkers);
        pager.setPageSize(20);


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


        //RootPanel.get().add(new Label("It is just  the  TEXT for TEST"));
        RootLayoutPanel rootPanel = RootLayoutPanel.get();
        DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.PX);
        layout.addNorth(new HTMLPanel("h1", "Workers List"), 80);
        layout.add(tableListWorkers);
        VerticalPanel verticalPanel = new VerticalPanel();

        verticalPanel.add(pager);
        //layout.add(pager);
        rootPanel.add(layout);
        rootPanel.add(verticalPanel);
        //RootPanel.get().add(tableListWorkers);
        //RootPanel.get("workersList").add(new Label("It is just the text"));

    }


    AsyncCallback<List<WorkerDTO>> callback = new AsyncCallback<List<WorkerDTO>>() {
        public void onFailure(Throwable caught) {
            // TODO: Do something with errors.
        }

        public void onSuccess(List<WorkerDTO> result) {
            workers = result;
            createDataGrid();
        }
    };


}
