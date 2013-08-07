package co.lgmk.keyper;

import co.lgmk.keyper.resources.PersonResource;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class KeyperService extends Service<KeyperConfiguration> {

    private GuiceBundle<KeyperConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new KeyperService().run(args);
    }

    @Override
    public void initialize(Bootstrap<KeyperConfiguration> bootstrap) {

        bootstrap.setName("Keyper");


        guiceBundle = GuiceBundle.<KeyperConfiguration>newBuilder()
                .addModule(new KeyperGuiceModule())
                .addModule(new JpaPersistModule("data"))
                .enableAutoConfig("co.lgmk")
                .setConfigClass(KeyperConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
    }


    @Override
    public void run(KeyperConfiguration config, Environment environment)
            throws Exception {

        PersistService persistService = guiceBundle.getInjector().getInstance(PersistService.class);
        persistService.start();

        environment.addResource(guiceBundle.getInjector().getInstance(PersonResource.class));
    }



}