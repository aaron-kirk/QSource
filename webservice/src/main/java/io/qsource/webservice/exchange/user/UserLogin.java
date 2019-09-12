package io.qsource.webservice.exchange.user;

public class UserLogin {
    
    private String email;
    private String password;
    
    public UserLogin(){
        
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
