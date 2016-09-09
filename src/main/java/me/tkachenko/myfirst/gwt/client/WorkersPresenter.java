package me.tkachenko.myfirst.gwt.client;


import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.*;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergej on 18.08.2016.
 */
public class WorkersPresenter implements WorkerUpdate.ChangeWorker {
    private View view;
    private boolean isInit;
    DataProvider provider = new DataProvider();
    private final Logger logger = Logger.getLogger("WorkersPresenter");
    private WorkerUpdate workerUpdate = new WorkerUpdate(this);


    public interface View extends IsWidget {

        DataGrid<WorkerDTO> getTableListWorkers();

        /**
         * Create provider for View
         *
         * @param provider the provider is used
         */
        void setDataProvider(AbstractDataProvider<WorkerDTO> provider);

        /**
         * Get a ColumnSortList.ColumnSortInfo for View
         *
         * @return an  instance
         */
        ColumnSortList.ColumnSortInfo getSortInfo();

        void refreshTable();

        void setRemoveHandler(FieldUpdater handler);


        Button getCreateWorkerButton();
    }


    WorkersPresenter(View view) {
        this.view = view;
    }

    /**
     * initializes provider
     *
     * @param container used Widget for image display
     */
    void go(HasWidgets container) {
        if (!isInit) {
            view.setDataProvider(provider);
            view.setRemoveHandler(new FieldUpdater() {
                @Override
                public void update(int index, Object object, Object value) {
                    deleteWorker((WorkerDTO) object);
                }
            });

            final SingleSelectionModel<WorkerDTO> selectionModel = new SingleSelectionModel<WorkerDTO>();
            view.getTableListWorkers().setSelectionModel(selectionModel);

            selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
                public void onSelectionChange(SelectionChangeEvent event) {
                    WorkerDTO workerDTO = selectionModel.getSelectedObject();
                    if (workerDTO != null) {
                        //Window.alert( "You selected  " + workerDTO.getDef());
                        workerUpdate.createDialogBox(workerDTO);


                    }
                }

            });

            view.getCreateWorkerButton().addClickHandler(new ClickHandler() {
                                                             @Override
                                                             public void onClick(ClickEvent event) {
                                                                 workerUpdate.createDialogBox(new WorkerDTO());
                                                             }
                                                         }
            );

            isInit = true;

        }


        container.add(view.asWidget());


    }


    /**
     * Create Provider
     */
    public class DataProvider extends AsyncDataProvider<WorkerDTO> {

        @Override
        protected void onRangeChanged(HasData<WorkerDTO> display) {

            Range range = display.getVisibleRange();
            final int start = range.getStart();
            int length = range.getLength();

            // Column's  name for sort
            String columnName = null;

            // Order sort
            boolean isAsc = true;


            ColumnSortList.ColumnSortInfo sortInfo = view.getSortInfo();
            if (sortInfo != null) {
                columnName = sortInfo.getColumn().getDataStoreName();
                isAsc = sortInfo.isAscending();
            }
            // return total number of rows of list
            MySampleApplicationService.App.getInstance().getTotalRow(new AsyncCallback<Number>() {
                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    //Window.alert("ERROR from SERVER !!!");

                    logger.log(Level.SEVERE, "ERROR total row from SERVER !!!", caught);


                }

                @Override
                public void onSuccess(Number result) {
                    updateRowCount(result.intValue(), true);

                }
            });


            // Returns the list of the specified range

            MySampleApplicationService.App.getInstance().getPartWorkers(start, length, columnName, isAsc, new AsyncCallback<List<WorkerDTO>>() {


                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    //Window.alert("ERROR from SERVER");
                    logger.log(Level.SEVERE, "ERROR data from SERVER !!!", caught);
                }

                @Override
                public void onSuccess(List<WorkerDTO> result) {

                    updateRowData(start, result);

                }
            });


        }

    }

    @Override
    public void updateWorker(WorkerDTO workerDTO) {
        MySampleApplicationService.App.getInstance().updateWorker(workerDTO, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
                //Window.alert("ERROR from SERVER !!!");

                logger.log(Level.SEVERE, "ERROR update from SERVER !!!", caught);

            }

            @Override
            public void onSuccess(Void v) {
                Window.alert("WORKER IS CHANGED");
                view.refreshTable();


            }
        });
    }

    @Override
    public void deleteWorker(WorkerDTO workerDTO) {
        MySampleApplicationService.App.getInstance().deleteWorker(workerDTO, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
                //Window.alert("ERROR from SERVER !!!");

                logger.log(Level.SEVERE, "ERROR  from SERVER !!!", caught);

            }

            @Override
            public void onSuccess(Void v) {
                Window.alert("WORKER IS DELETED");
                view.refreshTable();
            }
        });
    }
}
