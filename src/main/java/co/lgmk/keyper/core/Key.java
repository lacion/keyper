package co.lgmk.keyper.core;
import javax.persistence.Entity;

@Entity
public class Key extends Base {

    private static final long serialVersionUID = -4693647736354542489L;

    private String name;
    private String key;
    private boolean main;

    public Key() {
    }

    public Key(String name, String key)
    {
        this.name = name;
        this.key = key;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public boolean getMain()
    {
        return main;
    }

    public void setMain(boolean main)
    {
        this.main = main;
    }

}