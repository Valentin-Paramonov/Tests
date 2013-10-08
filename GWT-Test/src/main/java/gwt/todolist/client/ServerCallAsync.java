package gwt.todolist.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServerCallAsync {
    void call(AsyncCallback<String> async);
}
