
package ku.kinkao.service;

import ku.kinkao.model.User;
import ku.kinkao.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SignupService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public int createUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        newUser.setPassword(hashedPassword);

        repository.save(newUser);
        return 1;
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
