package scheduloney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scheduloney.api.entities.Account;
import scheduloney.api.repository.UserRepository;
import scheduloney.api.entities.User;

import java.util.Optional;

/**
 * UserController
 * <p>
 * Created by Thomas Lelievre on 30/08/2017.
 */

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/{userId}")
    public @ResponseBody
    User getUser(@PathVariable long userId) {
        return userRepository.findOne(userId);
    }

    @GetMapping(path = "/{userId}/update")
    public @ResponseBody
    String updateUser(@PathVariable long userId, @RequestParam Optional<String> name, @RequestParam Optional<String> email) {
        User user = userRepository.findOne(userId);
        boolean updated = false;
        if (name.isPresent()) {
            user.setName(name.get());
            updated = true;
        }
        if (email.isPresent()) {
            user.setEmail(email.get());
            updated = true;
        }
        if (updated) {
            userRepository.save(user);
            return String.format("user %s updated", user.getId());
        }
        return "nothing to update";
    }

    @GetMapping(path = "/{userId}/remove")
    public @ResponseBody String removeUser(@PathVariable long userId) {
        userRepository.delete(userId);
        return "user removed";
    }

    @GetMapping(path = "/{userId}/accounts")
    public @ResponseBody
    Iterable<Account> getUserAccounts(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user.getAccounts();
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
