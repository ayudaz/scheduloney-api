package scheduloney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scheduloney.api.entities.Account;
import scheduloney.api.entities.Spent;
import scheduloney.api.entities.User;
import scheduloney.api.repository.AccountRepository;
import scheduloney.api.repository.SpentRepository;
import scheduloney.api.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Spent Controller
 * <p>
 * Created by Thomas Lelievre on 31/08/2017.
 */

@RestController
@RequestMapping(path = "/account/{accountId}/spents")
public class SpentController {

    @Autowired
    private SpentRepository spentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewSpent(@PathVariable long accountId, @RequestParam BigDecimal amount, @RequestParam long payorId) {
        Account account = accountRepository.findOne(accountId);
        User payor = userRepository.findOne(payorId);
        Spent spent = new Spent();
        spent.setAccount(account);
        spent.setPayor(payor);
        spent.setAmount(amount);
        spentRepository.save(spent);

        return "spent added";
    }

    @GetMapping(path = "/{spentId}")
    public @ResponseBody
    Spent getSpent(@PathVariable long spentId) {
        return spentRepository.findOne(spentId);
    }

    @GetMapping(path = "/{spentId}/update")
    public @ResponseBody
    String updateSpent(@PathVariable long spentId, @RequestParam Optional<BigDecimal> amount, @RequestParam Optional<Long> payorId) {
        Spent spent = spentRepository.findOne(spentId);
        if (amount.isPresent()) {
            spent.setAmount(amount.get());
        }
        if (payorId.isPresent()) {
            User payor = userRepository.findOne(payorId.get());
            spent.setPayor(payor);
        }
        spentRepository.save(spent);
        return "spent updated";
    }

    @GetMapping(path = "/{spentId}/remove")
    public @ResponseBody
    String removeSpent(@PathVariable long spentId) {
        spentRepository.delete(spentId);
        return "spent deleted";
    }

    @GetMapping(path = "/{spentId}/addRecipient")
    public @ResponseBody
    String addRecipient(@PathVariable long spentId, @RequestParam long recipientId) {
        Spent spent = spentRepository.findOne(spentId);
        User recipient = userRepository.findOne(recipientId);
        spent.getRecipients().add(recipient);
        spentRepository.save(spent);
        return "recipient added";
    }

    @GetMapping(path = "/{spentId}/removeRecipient")
    public @ResponseBody
    String removeRecipient(@PathVariable long spentId, @RequestParam long recipientId) {
        Spent spent = spentRepository.findOne(spentId);
        User recipient = userRepository.findOne(recipientId);
        spent.getRecipients().remove(recipient);
        spentRepository.save(spent);
        return "recipient removed";
    }
}
