package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Дмитрий on 24.08.2016.
 */
public class WorkerUpdate {
    WorkerDTO workerDTO;
    final Logger logger = Logger.getLogger("Logger");
    DialogBox dialogBox = new DialogBox();
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    VerticalPanel verticalPanel = new VerticalPanel();
    VerticalPanel verticalPanel2 = new VerticalPanel();
    VerticalPanel verticalPanel3 = new VerticalPanel();
    TextBox textBoxForName = new TextBox();
    TextBox textBoxForFirstName = new TextBox();
    TextBox textBoxForLastName = new TextBox();
    DatePicker datePicker = new DatePicker();
    DateBox dateBox = new DateBox();
    ListBox listBoxCourse = new ListBox();



    WorkerUpdate(WorkerDTO workerDTO) {
        this.workerDTO = workerDTO;

    }

    public WorkerDTO getWorkerDTO() {
        return workerDTO;
    }


    void createDialogBox() {

        dialogBox.setGlassEnabled(true);

        verticalPanel.setSpacing(12);
        verticalPanel2.setSpacing(12);
        verticalPanel3.setSpacing(12);

        horizontalPanel.setSpacing(30);


        /*
        datePicker.setYearArrowsVisible(true);
        datePicker.setYearAndMonthDropdownVisible(true);
        // show 31 years in the years dropdown. The range of years is centered on the selected date
        datePicker.setVisibleYearCount(31);
        datePicker.setValue(new Date(), true);
        */

        // Create a DateBox
        DateTimeFormat dateFormat = DateTimeFormat.getMediumDateFormat();
        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBox.getDatePicker().setYearArrowsVisible(true);
        dateBox.getDatePicker().setVisibleYearCount(31);


        listBoxCourse.setVisibleItemCount(1);
        for (int i = 1; i <= 5; i++) {
            listBoxCourse.addItem("course " + i);
        }
        // if button "ADD" is pressed that "workerDTO == null"   ahd fields have to empty
        if (workerDTO != null) {
            textBoxForName.setText(workerDTO.getName());
            textBoxForFirstName.setText(workerDTO.getFirstname());
            textBoxForLastName.setText(workerDTO.getLastname());
        }


        verticalPanel.add(new HTML("NAME"));
        verticalPanel.add(textBoxForName);
        verticalPanel.add(new HTML("FIRSTNAME"));
        verticalPanel.add(textBoxForFirstName);
        verticalPanel.add(new HTML("LASTNAME"));
        verticalPanel.add(textBoxForLastName);


        Button removeWorker = new Button("REMOVE", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Add code here
                MySampleApplicationService.App.getInstance().deleteWorker(workerDTO, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO: Do something with errors.
                        //Window.alert("ERROR from SERVER !!!");

                        logger.log(Level.SEVERE, "ERROR total row from SERVER !!!");

                    }

                    @Override
                    public void onSuccess(Void v) {
                        Window.alert("WORKER IS DELETED");

                    }
                });
                dialogBox.hide();

            }
        });
        // Add a close button at the bottom of the dialog
        Button closeButton = new Button(
                "Ready", new ClickHandler() {
            public void onClick(ClickEvent event) {
                workerDTO = updateWorker();
                //Window.alert("Name =   " +  workerDTO.getName() + "    Kurs=  " + workerDTO.getKurs());
                MySampleApplicationService.App.getInstance().updateWorker(workerDTO, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO: Do something with errors.
                        //Window.alert("ERROR from SERVER !!!");

                        logger.log(Level.SEVERE, "ERROR total row from SERVER !!!");

                    }

                    @Override
                    public void onSuccess(Void v) {
                        Window.alert("WORKER IS CHANGED");

                    }
                });
                dialogBox.hide();


            }
        });

        Button cancel = new Button("Cancel", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        verticalPanel.add(removeWorker);
        verticalPanel.add(closeButton);

        //verticalPanel.add(closeButton);
        verticalPanel2.add(new HTML("Input Date (YYYY MMM DD)"));
        verticalPanel2.add(dateBox);

        verticalPanel3.add(new HTML("Choose the course"));
        verticalPanel3.add(listBoxCourse);
        verticalPanel.add(cancel);

        // horizontalPanel.add(datePicker);

        horizontalPanel.add(verticalPanel);
        horizontalPanel.add(verticalPanel2);
        horizontalPanel.add(verticalPanel3);


        dialogBox.setWidget(horizontalPanel);

        dialogBox.setText("You selected  " + workerDTO.getDef());


        dialogBox.center();
        dialogBox.show();


    }

    WorkerDTO updateWorker() {

        workerDTO.setName(textBoxForName.getText());
        workerDTO.setFirstname(textBoxForFirstName.getText());
        workerDTO.setLastname(textBoxForLastName.getText());
        workerDTO.setKurs(listBoxCourse.getSelectedIndex() + 1);
        workerDTO.setAbc(dateBox.getValue());
        return workerDTO;
    }


}
