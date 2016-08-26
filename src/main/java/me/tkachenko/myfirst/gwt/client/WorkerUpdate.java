package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.Date;

/**
 * Created by Дмитрий on 24.08.2016.
 */
public class WorkerUpdate {
    WorkerDTO workerDTO;

    final DialogBox dialogBox = new DialogBox();
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    VerticalPanel verticalPanel = new VerticalPanel();
    VerticalPanel verticalPanel2 = new VerticalPanel();
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


        verticalPanel.add(new HTML("Name"));
        verticalPanel.add(textBoxForName);
        verticalPanel.add(new HTML("FirstName"));
        verticalPanel.add(textBoxForFirstName);
        verticalPanel.add(new HTML("LastName"));
        verticalPanel.add(textBoxForLastName);
        // Add a close button at the bottom of the dialog
        Button closeButton = new Button(
                "Ready", new ClickHandler() {
            public void onClick(ClickEvent event) {
                workerDTO = updateWorker();
                dialogBox.hide();


            }
        });
        verticalPanel.add(closeButton);
        verticalPanel2.add(new HTML("Input Date"));
        verticalPanel2.add(dateBox);
        // horizontalPanel.add(datePicker);
        horizontalPanel.add(verticalPanel);
        horizontalPanel.add(verticalPanel2);
        horizontalPanel.add(listBoxCourse);


        dialogBox.setWidget(horizontalPanel);
        dialogBox.setText("You selected  " + workerDTO.getDef());


        // dialogBox.setPixelSize(250, 375);
        //dialogBox.setPopupPosition(800, 730);
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
