package gwt.todolist.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import gwt.todolist.client.ServerCall;

public class Server
    extends RemoteServiceServlet
    implements ServerCall {

    @Override
    public String call() {
        return "I need to...";
    }
}
