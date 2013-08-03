package co.lgmk.keyper;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import com.codahale.dropwizard.auth.basic.BasicAuthProvider;

import co.lgmk.keyper.auth.BasicAuthenticator;
import co.lgmk.keyper.resources.*;

public class KeyperApplication extends Application<KeyperConfiguration>
{

    public static void main(String[] args) throws Exception
    {
        new KeyperApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<KeyperConfiguration> bootstrap)
    {
    }

    @Override
    public void run(KeyperConfiguration configuration, Environment environment)
    {
        environment.jersey().register(new BasicAuthProvider<>(new BasicAuthenticator(), "Keyper Auth"));
        environment.jersey().register(new HelloWorldResource());
    }

}