package io.qsource.webservice.manager;

import io.qsource.webservice.entitiy.User;
import io.qsource.webservice.repo.TokenRepo;
import io.qsource.webservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private TokenRepo tokenRepo;
    
    public UserManager(){
        
    }
    
    public User getByEmail(String email){
        return this.userRepo.findByEmailIgnoreCase(email);
    }
    
    public boolean tokenExists(String token){
        return this.tokenRepo.existsByToken(token);
    }

    public boolean userExistsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public void registerUser(User u) {
        this.userRepo.save(u);
    }

    /**
     * logs in a user
     *
     * @param u the user to login
     * @param token the token to add
     */
    public void loginUser(User u, String token) {
        User user = userRepo.findByEmailIgnoreCase(u.getEmail());
        // don't reset token if id < 0 as that is used for debugging and should not get reset
        //except Ethan Ferguson gets the id '-1'
        if (user != null && (user.getId() >= -1 || user.getTokens().isEmpty())) {
            user.addToken(token);
            userRepo.save(user);
        }

    }
}
