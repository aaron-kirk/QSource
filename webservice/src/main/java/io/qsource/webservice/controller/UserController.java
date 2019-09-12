package io.qsource.webservice.controller;

import io.qsource.webservice.entitiy.User;
import io.qsource.webservice.exchange.user.UserInfo;
import io.qsource.webservice.exchange.user.UserLogin;
import io.qsource.webservice.exchange.user.UserRegister;
import io.qsource.webservice.manager.UserManager;
import io.qsource.webservice.util.LoggingConstants;
import io.qsource.webservice.util.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserManager userManager;

    private final Log log = LogFactory.getLog(LoggingConstants.USER_LOG);

    public UserController() {

    }

    @PostMapping("/user/login")
    public UserInfo login(@RequestBody UserLogin userLogin) {
        User u = this.userManager.getByEmail(userLogin.getEmail());
        if (u == null) {
            log.warn("userLogin: invalid user");
            return null;
        }
        if (!Utils.checkPassword(userLogin.getPassword(), u.getPasswordHash())) {
            log.warn("userLogin: password invalid for '" + u.getEmail() + "'");
            return null;
        }
        UserInfo ret = new UserInfo();
        BeanUtils.copyProperties(u, ret);
        String token;
        do {
            token = Utils.generateToken();
        } while (this.userManager.tokenExists(token));
        ret.setToken(token);
        userManager.loginUser(u, token);
        log.info("userLogin: login ok for '" + u.getEmail() + "'");
        return ret;
    }

    @PostMapping("/user/register")
    public void userRegister(@RequestBody UserRegister userRegister) {
        if (userManager.userExistsByEmail(userRegister.getEmail())) {
            log.warn("userRegister: user already registered for email: '" + userRegister.getEmail() + "'");
            return;
        }
        User u = new User();
        BeanUtils.copyProperties(userRegister, u);
        final String salt = Utils.genSalt();
        final String passhash = Utils.hashPassword(userRegister.getPassword(), salt);
        u.setPasswordHash(passhash);
        u.setColor((byte) 0);
        u.setClasses("");
        userManager.registerUser(u);
        log.info("userRegister: user '" + u.getEmail() + "' registered");
    }
}
