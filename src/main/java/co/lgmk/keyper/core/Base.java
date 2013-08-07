package co.lgmk.keyper.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;



@MappedSuperclass
public abstract class Base implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 8608953068007538072L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "version")
    private int version = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "veraenderung_zeitpunkt")
    @Transient
    private Date lastUpdate;

    protected void copy(final Base source)
    {
        this.id = source.id;
        this.version = source.version;
        this.lastUpdate = source.lastUpdate;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Base))
        {
            return false;
        }
        final Base other = (Base) obj;
        if (this.id != null && other.id != null)
        {
            if (this.id != other.id)
            {
                return false;
            }
        }
        return true;
    }

    public Long getId()
    {
        return this.id;
    }

    @Deprecated
    public void setId(final Long id)
    {
        this.id = id;
    }

    public int getVersion()
    {
        return this.version;
    }

    @SuppressWarnings("unused")
    private void setVersion(final int version)
    {
        this.version = version;
    }

    public Date getLastUpdate()
    {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }
}