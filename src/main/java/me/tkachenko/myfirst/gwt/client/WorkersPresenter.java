package me.tkachenko.myfirst.gwt.client;


import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergej on 18.08.2016.
 */
public class WorkersPresenter {
    View view;
    boolean isInit;


    public interface View extends IsWidget {

        /**
         * Create provider for View
         *
         * @param provider the provider is used
         */
        void setDataProvider(AbstractDataProvider<WorkerDTO> provider);

        /**
         * Get a ColumnSortList.ColumnSortInfo for View
         * @return an  instance
         */
        ColumnSortList.ColumnSortInfo getSortInfo();
    }

    WorkersPresenter(View view) {
        this.view = view;
    }

    /**
     * initializes provider
     * @param container used Widget for image display
     */
    void go(HasWidgets container) {
        if (!isInit) {
            view.setDataProvider(new DataProvider());
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
            final Logger logger = Logger.getLogger("Logger");
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

                    logger.log(Level.SEVERE, "ERROR total row from SERVER !!!");

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
                    logger.log(Level.SEVERE, "ERROR data from SERVER !!!");
                }

                @Override
                public void onSuccess(List<WorkerDTO> result) {

                    updateRowData(start, result);

                }
            });


        }

    }
}
