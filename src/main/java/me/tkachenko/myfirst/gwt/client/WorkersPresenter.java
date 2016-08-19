package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;

/**
 * Created by Дмитрий on 18.08.2016.
 */
public class WorkersPresenter {
    View view;
    boolean isInit;


    public interface View extends IsWidget {

        void setDataProvider(AbstractDataProvider<WorkerDTO> provider);

        ColumnSortList.ColumnSortInfo getSortInfo();
    }

    WorkersPresenter(View view) {
        this.view = view;
    }

    void go(HasWidgets container) {
        if (!isInit) {
            DataProvider provider = new DataProvider();
            view.setDataProvider(provider);
            isInit = true;
        }
        container.add(view.asWidget());

    }

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

            //final ColumnSortList sortList = view.getTableListWorkers().getColumnSortList();
            ColumnSortList.ColumnSortInfo sortInfo = view.getSortInfo();
            if (sortInfo != null) {
                columnName = sortInfo.getColumn().getDataStoreName();
                isAsc = sortInfo.isAscending();
            }

            MySampleApplicationService.App.getInstance().getTotalRow(new AsyncCallback<Number>() {
                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    Window.alert("ERROR from SERVER !!!");
                }

                @Override
                public void onSuccess(Number result) {
                    updateRowCount(result.intValue(), true);

                }
            });


            MySampleApplicationService.App.getInstance().getPartWorkers(start, length, columnName, isAsc, new AsyncCallback<List<WorkerDTO>>() {


                @Override
                public void onFailure(Throwable caught) {
                    // TODO: Do something with errors.
                    Window.alert("ERROR from SERVER");
                }

                @Override
                public void onSuccess(List<WorkerDTO> result) {

                    updateRowData(start, result);

                }
            });


        }

    }
}
