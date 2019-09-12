package io.qsource.webservice.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="qsource_user")
public class User implements Serializable{
    
    private static final long TIMEOUT = 432000000; //5 days

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name="email", nullable=false, length = 128, unique = true)
    private String email;

    @Column(name="name", nullable=false, length = 128)
    private String name;

    @Column(name="password_hash", nullable=false, length = 256)
    private String passwordHash;

    @Column(name="color", nullable=false)
    private byte color;

    @Column(name="classes", nullable=false, length = 1024)
    private String classes;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<Token> tokens;
    
    public User(){}
    
    public void removeOldTokens(){
        this.tokens.removeIf(n->(System.currentTimeMillis() - n.getDateAdded().getTime()) > TIMEOUT);
    }
    
    public boolean isValidToken(String token){
        return this.tokens.stream().anyMatch(n->n.getToken().equals(token));
    }
    
    public void addToken(String token){
        removeOldTokens();
        Token t = new Token();
        t.setDateAdded(new Date());
        t.setToken(token);
        t.setUser(this);
        this.tokens.add(t);
    }
    
    public boolean removeToken(String token){
        return this.tokens.removeIf(n->n.getToken().equals(token));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
    
    
}
