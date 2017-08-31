package scheduloney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scheduloney.api.entities.Account;
import scheduloney.api.entities.User;
import scheduloney.api.repository.AccountRepository;
import scheduloney.api.repository.UserRepository;

import java.util.Optional;

/**
 * AccountController
 * <p>
 * Created by Thomas Lelievre on 30/08/2017.
 */

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewAccount(@RequestParam String name
            , @RequestParam String description) {
        Account a = new Account();
        a.setName(name);
        a.setDescription(description);
        accountRepository.save(a);
        return "Saved";
    }

    @GetMapping(path = "/{accountId}")
    public @ResponseBody
    Account getAccount(@PathVariable long accountId) {
        Account a = accountRepository.findOne(accountId);
        return a;
    }

    @GetMapping(path = "/{accountId}/update")
    public @ResponseBody String updateAccount(@PathVariable long accountId, @RequestParam Optional<String> name, @RequestParam Optional<String> description) {
        Account account = accountRepository.findOne(accountId);
        boolean updated = false;
        if (name.isPresent()) {
            account.setName(name.get());
            updated = true;
        }
        if (description.isPresent()) {
            account.setDescription(description.get());
            updated = true;
        }
        if (updated) {
            accountRepository.save(account);
            return "account updated";
        }
        return "nothing to update";
    }

    @GetMapping(path = "/{accountId}/delete")
    public @ResponseBody String deleteAccount(@PathVariable long accountId) {
        accountRepository.delete(accountId);
        return "account deleted";
    }

    @GetMapping(path = "/{accountId}/members/add")
    public @ResponseBody
    String addMember(@PathVariable(value = "accountId") long accountId, @RequestParam long userId) {
        Account account = accountRepository.findOne(accountId);
        User user = userRepository.findOne(userId);
        account.getMembers().add(user);
        accountRepository.save(account);
        return "User added";
    }

    @GetMapping(path = "/{accountId}/members/remove")
    public @ResponseBody
    String removeMember(@PathVariable(value = "accountId") long accountId, @RequestParam long userId) {
        Account account = accountRepository.findOne(accountId);
        User user = userRepository.findOne(userId);
        account.getMembers().remove(user);
        accountRepository.save(account);
        return "User removed";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
