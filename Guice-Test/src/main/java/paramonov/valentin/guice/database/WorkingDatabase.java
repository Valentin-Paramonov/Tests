package paramonov.valentin.guice.database;

public class WorkingDatabase implements Database {
    @Override
    public String getName() {
        return "I'm a working database.";
    }
}
