package co.lgmk.keyper.core;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="Person.GetAll", query =
                "from Person g")
})
public class Person extends Base {

    private static final long serialVersionUID = -2362683596950421365L;

    public String firstName;
    public String lastName;
    public String jobTitle;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name="PersonId", referencedColumnName="id")
    private Set<Key> keyList = new HashSet<Key>();

    public void addKey(Key t) {
        this.keyList.add(t);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public Set<Key> getKeyList() {
        return keyList;
    }
}