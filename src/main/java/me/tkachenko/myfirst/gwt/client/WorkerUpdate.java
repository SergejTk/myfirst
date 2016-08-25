package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

import java.util.Date;

/**
 * Created by Дмитрий on 24.08.2016.
 */
public class WorkerUpdate {
    WorkerDTO workerDTO;

    WorkerUpdate(WorkerDTO workerDTO) {
        this.workerDTO = workerDTO;

    }

    void createDialogBox() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setGlassEnabled(true);

        VerticalPanel dialogContents = new VerticalPanel();
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        TextBox textBoxForName = new TextBox();
        TextBox textBoxForFirstName = new TextBox();
        TextBox textBoxForLastName = new TextBox();

        textBoxForName.setText(workerDTO.getName());
        textBoxForFirstName.setText(workerDTO.getFirstname());
        textBoxForLastName.setText(workerDTO.getLastname());

        DatePicker datePicker = new DatePicker();
        datePicker.setYearArrowsVisible(true);
        datePicker.setYearAndMonthDropdownVisible(true);
        // show 31 years in the years dropdown. The range of years is centered on the selected date
        datePicker.setVisibleYearCount(31);
        datePicker.setValue(new Date(), true);

        horizontalPanel.add(textBoxForName);
        horizontalPanel.add(textBoxForFirstName);
        horizontalPanel.add(textBoxForLastName);
        horizontalPanel.add(datePicker);

        dialogContents.add(horizontalPanel);
        dialogContents.setSpacing(2);
        dialogBox.setWidget(dialogContents);
        dialogBox.setText("You selected  " + workerDTO.getDef());

        // Add a close button at the bottom of the dialog
        Button closeButton = new Button(
                "Ready", new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        dialogContents.add(closeButton);
        dialogBox.setPixelSize(250, 375);
        dialogBox.setPopupPosition(800, 730);
        dialogBox.show();

    }


}
