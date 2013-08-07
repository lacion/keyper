package co.lgmk.keyper.db;

import java.util.List;

import javax.persistence.EntityManager;

import co.lgmk.keyper.core.Person;

import com.google.inject.Inject;

public class PersonDao extends GenericDAOImpl<Person, Long>{

    @Inject
    public PersonDao(EntityManager man) {
        super(man);
        entityClass=Person.class;
    }

    @SuppressWarnings("unchecked")
    public Person findById(Integer id) {
        return (Person)getEntityManager()
                .createNativeQuery("SELECT * FROM Person g where g.id = " + id.toString(), Person.class).getSingleResult();
    }

}