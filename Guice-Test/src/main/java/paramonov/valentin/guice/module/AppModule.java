package paramonov.valentin.guice.module;

import com.google.inject.AbstractModule;
import paramonov.valentin.guice.annotation.Test;
import paramonov.valentin.guice.annotation.Work;
import paramonov.valentin.guice.database.Database;
import paramonov.valentin.guice.database.TestingDatabase;
import paramonov.valentin.guice.database.WorkingDatabase;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).
            annotatedWith(Work.class).
                to(WorkingDatabase.class);

        bind(Database.class).
            annotatedWith(Test.class).
                to(TestingDatabase.class);
    }
}
