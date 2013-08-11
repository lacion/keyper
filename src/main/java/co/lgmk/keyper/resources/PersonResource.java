package co.lgmk.keyper.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import co.lgmk.keyper.core.Person;
import co.lgmk.keyper.core.Key;
import co.lgmk.keyper.db.PersonDao;
import co.lgmk.keyper.core.User;

import com.yammer.dropwizard.auth.Auth;

import com.google.inject.Inject;
import com.google.inject.persist.UnitOfWork;

import java.util.List;


@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    public PersonDao KeyRepository;

    @Inject private UnitOfWork unitOfWork;

    @Inject
    public PersonResource(PersonDao KeyRepository)
    {
        this.KeyRepository = KeyRepository;
    }

    /*
     * Creates a person resource, a person is the basic holder of keys and can hold
     * several keys
     */
    @POST
    public Person createPerson(@Auth User user, Person person)
    {
        KeyRepository.getTransaction().begin();
        Person p = new Person();

        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setJobTitle(person.getJobTitle());
        p.setUserName(person.getUserName());

        KeyRepository.save(p);
        KeyRepository.getTransaction().commit();
        return p;
    }

    /*
     * Creates a key resource and adds them to a person
     */
    @Path("/key/{personId}")
    @POST
    public Person createKey(@Auth User user, @PathParam("personId") Integer personId, Key key)
    {
        KeyRepository.getTransaction().begin();
        Person p = KeyRepository.findById(personId);

        p.addKey(key);

        KeyRepository.save(p);
        KeyRepository.getTransaction().commit();
        return p;
    }

    /*
     * Lists all persons and their keys
     */
    @GET
    @Path("/list")
    public List<Person> listAll(@Auth User user)
    {
        List<Person> ret = null;
        KeyRepository.getTransaction().begin();
        ret = KeyRepository.findAll();
        KeyRepository.getTransaction().commit();
        return ret;
    }

    /*
     * List a person and its keys
     */
    @GET
    @Path("/{personId}")
    public Person list(@Auth User user, @PathParam("personId") Integer personId)
    {
        Person ret = null;
        KeyRepository.getTransaction().begin();
        ret = KeyRepository.findById(personId);
        KeyRepository.getTransaction().commit();
        return ret;
    }
}