package paramonov.valentin.guice;

import com.google.inject.Inject;
import paramonov.valentin.guice.annotation.Work;
import paramonov.valentin.guice.database.Database;

public class App {
    private Database db;

    @Inject
    public App(@Work Database db) {
        this.db = db;
    }

    public String getDBName() {
        return db.getName();
    }
}
