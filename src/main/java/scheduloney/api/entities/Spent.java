package scheduloney.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Spent Entity
 * <p>
 * Created by Thomas Lelievre on 31/08/2017.
 */
@Entity
public class Spent {

    private long id;
    private BigDecimal amount;
    private Account account;
    private User payor;
    private Set<User> recipients;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    @OneToOne
    public User getPayor() {
        return payor;
    }

    public void setPayor(User payor) {
        this.payor = payor;
    }

    @OneToMany
    public Set<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<User> recipients) {
        this.recipients = recipients;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
