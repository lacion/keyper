package co.lgmk.keyper;

import co.lgmk.keyper.core.Person;
import co.lgmk.keyper.db.PersonDao;
import co.lgmk.keyper.db.GenericDAO;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class KeyperGuiceModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(new TypeLiteral<GenericDAO<Person, Long>>() {}).to(PersonDao.class);
    }

}
