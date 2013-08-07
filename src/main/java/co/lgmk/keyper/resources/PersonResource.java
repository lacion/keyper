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

    @POST
    public Person createPerson(Person person)
    {
        KeyRepository.getTransaction().begin();
        Person p = new Person();

        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setJobTitle(person.getJobTitle());

        KeyRepository.save(p);
        KeyRepository.getTransaction().commit();
        return p;
    }

    @Path("/key/{personId}")
    @POST
    public Person createKey(@PathParam("personId") Integer personId, Key key)
    {
        KeyRepository.getTransaction().begin();
        Person p = KeyRepository.findById(personId);

        p.addKey(key);

        KeyRepository.save(p);
        KeyRepository.getTransaction().commit();
        return p;
    }

    @GET
    @Path("/list")
    public List<Person> list()
    {
        List<Person> ret = null;
        KeyRepository.getTransaction().begin();
        ret = KeyRepository.findAll();
        KeyRepository.getTransaction().commit();
        return ret;
    }
}