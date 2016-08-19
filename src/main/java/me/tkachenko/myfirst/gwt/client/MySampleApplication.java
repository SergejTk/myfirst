package me.tkachenko.myfirst.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {



    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        WorkersView workersView = new WorkersView();
        WorkersPresenter presenter = new WorkersPresenter(workersView);
        presenter.go(RootLayoutPanel.get());


    }


}
