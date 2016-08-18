package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.List;

/**
 * Created by ִלטענטי on 18.08.2016.
 */
public class Presenter {
    MyView view;

    public interface SetDataProvider {

        void setDataProvider(DataProvider provider);
    }

    Presenter(MyView view) {
        this.view = view;
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

            final ColumnSortList sortList = view.getTableListWorkers().getColumnSortList();
            ColumnSortList.ColumnSortInfo sortInfo;
            if (sortList.size() > 0) {
                sortInfo = sortList.get(0);
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
                    view.getTableListWorkers().setRowCount(result.intValue(), true);
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
