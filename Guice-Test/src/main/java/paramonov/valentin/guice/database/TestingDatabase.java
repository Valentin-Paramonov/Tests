package paramonov.valentin.guice.database;

public class TestingDatabase implements Database {
    @Override
    public String getName() {
        return "I'm a testing database.";
    }
}
