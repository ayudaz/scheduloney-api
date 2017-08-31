package scheduloney.api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Account
 * <p>
 * Created by Thomas Lelievre on 31/08/2017.
 */
@Entity
public class Account {

    private long id;
    private String name;
    private String description;
    private Set<User> members;
    private List<Spent> spents;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(max = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    @JsonManagedReference
    public List<Spent> getSpents() {
        return spents;
    }

    public void setSpents(List<Spent> spents) {
        this.spents = spents;
    }
}
