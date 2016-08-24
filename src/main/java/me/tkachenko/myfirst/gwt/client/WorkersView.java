package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.Date;

/**
 * Created by Sergej on 18.08.2016.
 */
public class WorkersView implements WorkersPresenter.View {

    private DataGrid<WorkerDTO> tableListWorkers = new DataGrid<>();
    private DockLayoutPanel layout;

    public WorkersView() {
        layout = createDataGrid();
    }

    public DataGrid<WorkerDTO> getTableListWorkers() {
        return tableListWorkers;
    }

    @Override
    public void setDataProvider(AbstractDataProvider<WorkerDTO> provider) {
        provider.addDataDisplay(tableListWorkers);


    }

    @Override
    public ColumnSortList.ColumnSortInfo getSortInfo() {
        ColumnSortList sortList = getTableListWorkers().getColumnSortList();
        return sortList.size() > 0 ? sortList.get(0) : null;
    }

    // Create table (DataGrid)
    DockLayoutPanel createDataGrid() {

        tableListWorkers.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        // Add column to show the ID.
        Column<WorkerDTO, Number> defColumn = new Column<WorkerDTO, Number>(new NumberCell()) {
            @Override
            public Number getValue(WorkerDTO object) {
                return object.getDef();
            }
        };
        defColumn.setDataStoreName("def");
        tableListWorkers.addColumn(defColumn, "ID");

        // Add column to show the name.
        TextColumn<WorkerDTO> nameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.getName();
            }
        };
        tableListWorkers.addColumn(nameColumn, "Name");
        nameColumn.setDataStoreName("name");
        nameColumn.setSortable(true);

        // Add column to show the firstname.
        TextColumn<WorkerDTO> firstnameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.getFirstname();
            }
        };
        tableListWorkers.addColumn(firstnameColumn, "Firstname");
        firstnameColumn.setDataStoreName("firstname");
        firstnameColumn.setSortable(true);

        // Add column to show the lastname.
        TextColumn<WorkerDTO> lastnameColumn = new TextColumn<WorkerDTO>() {
            @Override
            public String getValue(WorkerDTO object) {
                return object.getLastname();
            }
        };
        tableListWorkers.addColumn(lastnameColumn, "Lastname");
        lastnameColumn.setDataStoreName("lastname");
        lastnameColumn.setSortable(true);

        DateCell dateCell = new DateCell();
        Column<WorkerDTO, Date> dateColumn = new Column<WorkerDTO, Date>(dateCell) {
            @Override
            public Date getValue(WorkerDTO object) {
                return object.getAbc();
            }
        };
        tableListWorkers.addColumn(dateColumn, "Birthday");
        dateColumn.setDataStoreName("abc");
        dateColumn.setSortable(true);

        Column<WorkerDTO, Number> numberinvColumn = new Column<WorkerDTO, Number>(new NumberCell()) {
            @Override
            public Number getValue(WorkerDTO object) {
                return object.getNumberinv();
            }
        };
        tableListWorkers.addColumn(numberinvColumn, "№ Sertificate");


        final SingleSelectionModel<WorkerDTO> selectionModel = new SingleSelectionModel<WorkerDTO>();
        tableListWorkers.setSelectionModel(selectionModel);

        // *****************-------------------------------------------------------
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                WorkerDTO workerDTO = selectionModel.getSelectedObject();
                if (workerDTO != null) {
                    //Window.alert( "You selected  " + workerDTO.getDef());
                    new WorkerUpdate(workerDTO).createDialogBox();
                }
            }

        });

        //*******************----------------------------------------------------------------------
        SimplePager pager;
        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, true, 0, true);
        pager.setDisplay(tableListWorkers);
        pager.setPageSize(20);


        // --------------------------------------------------------


        ColumnSortEvent.AsyncHandler columnSortHandler = new
                ColumnSortEvent.AsyncHandler(tableListWorkers);
        tableListWorkers.addColumnSortHandler(columnSortHandler);


        //------------------------------------------------------------


        layout = new DockLayoutPanel(Style.Unit.PX);
        layout.addNorth(new HTMLPanel("h1", "Workers List"), 80);
        layout.addSouth(pager, 220);
        layout.add(tableListWorkers);


        return layout;

    }


    @Override
    public Widget asWidget() {
        return layout;
    }
}
