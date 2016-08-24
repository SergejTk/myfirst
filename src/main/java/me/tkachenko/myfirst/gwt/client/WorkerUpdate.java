package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;

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
        dialogContents.setSpacing(4);
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
        dialogBox.setPixelSize(250, 190);
        dialogBox.setPopupPosition(800, 730);
        dialogBox.show();

    }


}
