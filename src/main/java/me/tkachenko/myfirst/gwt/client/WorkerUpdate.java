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

import java.util.Date;
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
    TextBox textBoxForName = new TextBox();
    TextBox textBoxForFirstName = new TextBox();
    TextBox textBoxForLastName = new TextBox();
    DatePicker datePicker = new DatePicker();
    DateBox dateBox = new DateBox();
    ListBox listBoxCourse = new ListBox();
    FlowPanel flowPanel = new FlowPanel();


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

        horizontalPanel.setSpacing(30);


        textBoxForName.setText(workerDTO.getName());
        textBoxForFirstName.setText(workerDTO.getFirstname());
        textBoxForLastName.setText(workerDTO.getLastname());


        datePicker.setYearArrowsVisible(true);
        datePicker.setYearAndMonthDropdownVisible(true);
        // show 31 years in the years dropdown. The range of years is centered on the selected date
        datePicker.setVisibleYearCount(31);
        datePicker.setValue(new Date(), true);

        // Create a DateBox
        DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBox.getDatePicker().setYearArrowsVisible(true);


        listBoxCourse.setVisibleItemCount(1);
        for (int i = 1; i <= 5; i++) {
            listBoxCourse.addItem("course " + i);
        }


        verticalPanel.add(new HTML("NAME"));
        verticalPanel.add(textBoxForName);
        verticalPanel.add(new HTML("FirstName"));
        verticalPanel.add(textBoxForFirstName);
        verticalPanel.add(new HTML("LastName"));
        verticalPanel.add(textBoxForLastName);

        // Add a CreateWorker button
        Button createWorker = new Button("ADD", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Add code here
            }
        });

        Button removeWorker = new Button("REMOVE", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Add code here
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

        verticalPanel.add(closeButton);
        verticalPanel.add(createWorker);
        verticalPanel.add(removeWorker);

        //verticalPanel.add(closeButton);
        verticalPanel2.add(new HTML("Input Date"));
        verticalPanel2.add(dateBox);
        // horizontalPanel.add(datePicker);

        horizontalPanel.add(verticalPanel);
        horizontalPanel.add(verticalPanel2);
        horizontalPanel.add(listBoxCourse);


        dialogBox.setWidget(horizontalPanel);
        /*
        flowPanel.add(verticalPanel);
        flowPanel.add(verticalPanel2);
        flowPanel.add(listBoxCourse);
        flowPanel.add(closeButton);
        flowPanel.add(createWorker);
        flowPanel.add(removeWorker);
        */


        dialogBox.setText("You selected  " + workerDTO.getDef());


        dialogBox.center();
        dialogBox.show();

    }

    WorkerDTO updateWorker() {

        workerDTO.setName(textBoxForName.getText());
        workerDTO.setFirstname(textBoxForFirstName.getText());
        workerDTO.setLastname(textBoxForLastName.getText());
        workerDTO.setKurs(listBoxCourse.getSelectedIndex() + 1);

        return workerDTO;
    }


}
