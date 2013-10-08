package gwt.todolist.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("call")
public interface ServerCall extends RemoteService {
    String call();
}
