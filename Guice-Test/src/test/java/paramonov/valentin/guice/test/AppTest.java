package paramonov.valentin.guice.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import paramonov.valentin.guice.App;
import paramonov.valentin.guice.module.AppModule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppTest {
    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new AppModule());
    }

    @Test
    public void testTestScope() throws Exception {
        App app = injector.getInstance(TestApp.class);

        String dbname = app.getDBName();

        assertThat(dbname, equalTo("I'm a testing database."));
    }

    @Test
    public void testWorkScope() throws Exception {
        App app = injector.getInstance(App.class);

        String dbname = app.getDBName();

        assertThat(dbname, equalTo("I'm a working database."));
    }
}
