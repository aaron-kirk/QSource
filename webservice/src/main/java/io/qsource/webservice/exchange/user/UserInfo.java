package io.qsource.webservice.exchange.user;

public class UserInfo {
    
    private String email;
    private String token;
    private String name;
    
    public UserInfo(){
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
