package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SingleSelectionModel;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private static DataGrid<WorkerDTO> tableListWorkers = new DataGrid<>();
    private static List<WorkerDTO> workerDTOList = new ArrayList<>();


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        createDataGrid();

        //MySampleApplicationService.App.getInstance().getListWorkers(callback);


    }

    private static class DataProvider extends AsyncDataProvider<WorkerDTO> {
        @Override
        protected void onRangeChanged(HasData<WorkerDTO> display) {
            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            MySampleApplicationService.App.getInstance().getTotalRow(new AsyncCallback<Number>() {
                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    Window.alert("ERROR from SERVER !");
                }

                @Override
                public void onSuccess(Number result) {
                    tableListWorkers.setRowCount((int) result, true);
                }
            });


            MySampleApplicationService.App.getInstance().getPartWorkers(start, length, new AsyncCallback<List<WorkerDTO>>() {


                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    Window.alert("ERROR from SERVER");
                }

                @Override
                public void onSuccess(List<WorkerDTO> result) {

                    workerDTOList = result;
                    updateRowData(start, result);

                }
            });

        }
    }

    private static void createDataGrid() {



        tableListWorkers.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        Column<WorkerDTO, Number> defColumn = new Column<WorkerDTO, Number>(new NumberCell()) {
            @Override
            public Number getValue(WorkerDTO object) {
                return object.def;
            }
        };
        tableListWorkers.addColumn(defColumn, "ID");

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

        firstnameColumn.setSortable(true);

        // Add column to show the lastname.
        TextColumn<WorkerDTO> lastnameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.lastname;
            }
        };
        tableListWorkers.addColumn(lastnameColumn, "Lastname");


        DateCell dateCell = new DateCell();
        Column<WorkerDTO, Date> dateColumn = new Column<WorkerDTO, Date>(dateCell) {
            @Override
            public Date getValue(WorkerDTO object) {
                return object.abc;
            }
        };
        tableListWorkers.addColumn(dateColumn, "Birthday");

        Column<WorkerDTO, Number> numberinvColumn = new Column<WorkerDTO, Number>(new NumberCell()) {
            @Override
            public Number getValue(WorkerDTO object) {
                return object.numberinv;
            }
        };
        tableListWorkers.addColumn(numberinvColumn, "№ Sertificate");


        final SingleSelectionModel<WorkerDTO> selectionModel = new SingleSelectionModel<WorkerDTO>();
        tableListWorkers.setSelectionModel(selectionModel);

        // --------------------------------------------------------


        firstnameColumn.setSortable(true);
        ColumnSortEvent.ListHandler<WorkerDTO> columnSortHandler = new
                ColumnSortEvent.ListHandler<WorkerDTO>(workerDTOList);
        tableListWorkers.addColumnSortHandler(columnSortHandler);

        //------------------------------------------------------------


        DataProvider dataProvider = new DataProvider();
        dataProvider.addDataDisplay(tableListWorkers);

        SimplePager pager;
        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);
        pager.setDisplay(tableListWorkers);
        pager.setPageSize(20);


        //RootPanel.get().add(new Label("It is just  the  TEXT for TEST"));
        RootLayoutPanel rootPanel = RootLayoutPanel.get();
        DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.PX);
        layout.addNorth(new HTMLPanel("h1", "Workers List"), 80);


        layout.addSouth(pager, 220);

        layout.add(tableListWorkers);

        //VerticalPanel verticalPanel = new VerticalPanel();
        // verticalPanel.add(pager);
        rootPanel.add(layout);
        //rootPanel.add(verticalPanel);
        //RootPanel.get().add(tableListWorkers);
        //RootPanel.get("workersList").add(new Label("It is just the text"));

    }

    AsyncCallback<List<WorkerDTO>> callback = new AsyncCallback<List<WorkerDTO>>() {
        public void onFailure(Throwable caught) {
            // TODO: Do something with errors.
        }

        public void onSuccess(List<WorkerDTO> result) {
            tableListWorkers.setRowCount(result.size(), true);
            //workers = result;
            createDataGrid();
        }
    };


}
