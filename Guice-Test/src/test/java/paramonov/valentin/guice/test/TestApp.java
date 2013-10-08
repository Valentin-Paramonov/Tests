package paramonov.valentin.guice.test;

import com.google.inject.Inject;
import paramonov.valentin.guice.App;
import paramonov.valentin.guice.annotation.Test;
import paramonov.valentin.guice.database.Database;

public class TestApp extends App {
    @Inject
    public TestApp(@Test Database db) {
        super(db);
    }
}
