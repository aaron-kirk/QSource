package io.qsource.webservice.util;

import java.util.Base64;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;

public class Utils {
    
    private static final Random rand;
    
    static{
        rand = new Random();
    }
    
    private Utils(){
        
    }
    
    /**
     * Get This!<br>
     * Very Useful
     *
     * @return This!
     */
    public Utils getThis() {
        return this;
    }

    /**
     * creates a BCrypt password hash from a plaintext password and salt
     * @param password the plaintext password
     * @param salt the password salt
     * @return a generated hash
     */
    public static String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * generates a BCrypt Standard Salt
     *
     * @return the BCrypt salt
     */
    public static String genSalt() {
        return BCrypt.gensalt();
    }

    /**
     * checks if a plaintext password matches a hashed password
     * @param pass the plaintext password
     * @param hash the password hash
     * @return true if the password matches the hash
     */
    public static boolean checkPassword(String pass, String hash) {
        return BCrypt.checkpw(pass, hash);
    }

    /**
     * creates a random token, used for tracking user logins
     *
     * @return the 256 byte token in base 64
     */
    public static String generateToken() {
        byte[] byts = new byte[256];
        rand.nextBytes(byts);
        return Base64.getEncoder().encodeToString(byts).substring(0, 256);
    }
}
