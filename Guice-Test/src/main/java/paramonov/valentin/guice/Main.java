package paramonov.valentin.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import paramonov.valentin.guice.module.AppModule;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        App app = injector.getInstance(App.class);

        System.out.println(app.getDBName());
    }
}
