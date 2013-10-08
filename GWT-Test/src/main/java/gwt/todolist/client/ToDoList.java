package gwt.todolist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class ToDoList implements EntryPoint, AsyncCallback<String> {
    private final TextBox TEXT_BOX = new TextBox();
    private final Panel LIST_PANEL = new VerticalPanel();
    private ServerCallAsync call = GWT.create(ServerCall.class);

    private final ClickHandler CLICK_HANDLER =
        new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String nameFieldText = TEXT_BOX.getText();

                if (nameFieldText.isEmpty()) return;

                final CheckBox cb = new CheckBox(nameFieldText);
                cb.setStyleName("checkbox");

                cb.addValueChangeHandler(
                        VALUE_CHANGED_HANDLER);

                LIST_PANEL.add(cb);
            }
        };

    private final ValueChangeHandler<Boolean> VALUE_CHANGED_HANDLER =
        new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(
                ValueChangeEvent<Boolean> booleanValueChangeEvent) {

                CheckBox cb = (CheckBox) booleanValueChangeEvent.getSource();

                if (cb.getValue()) {
                    cb.addStyleName("removed");
                    return;
                }

                cb.removeStyleName("removed");
            }
        };

    @Override
    public void onModuleLoad() {
        Panel inputPanel = new HorizontalPanel();
        inputPanel.setTitle("input");

        LIST_PANEL.setTitle("list");

        Grid grid = new Grid(2, 1);
        grid.setWidget(0, 0, inputPanel);
        grid.setWidget(1, 0, LIST_PANEL);

        RootPanel.get("toDoWidget").add(grid);

        final Button addButton = new Button("Add");
//        TEXT_BOX.setText("I need to...");

        inputPanel.add(TEXT_BOX);
        inputPanel.add(addButton);

        addButton.addClickHandler(
            CLICK_HANDLER);

        call.call(this);
    }

    @Override
    public void onFailure(Throwable throwable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSuccess(String s) {
        TEXT_BOX.setText(s);
    }
}
