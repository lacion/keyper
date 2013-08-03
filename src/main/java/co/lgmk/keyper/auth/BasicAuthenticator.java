package co.lgmk.keyper.auth;

import com.codahale.dropwizard.auth.AuthenticationException;
import com.codahale.dropwizard.auth.Authenticator;
import com.codahale.dropwizard.auth.basic.BasicCredentials;

import co.lgmk.keyper.core.User;
import com.google.common.base.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User>
{
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        if ("secret".equals(credentials.getPassword()))
        {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
