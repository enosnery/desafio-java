package com.enosnery.desafiojava.model;

import com.enosnery.desafiojava.request.AddUserRequest;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private UUID uuid = UUID.randomUUID();

    private String name;

    private String email;

    private String password;

    private Date created = new Date();

    private Date modified;

    private Date lastLogin;

    private String token;

    @OneToMany
    private List<Telephone> phones;

    public User(){
    }

    public User(AddUserRequest form){
        this.email = form.email;
        this.name = form.name;
        this.password = DigestUtils.md5DigestAsHex(form.password.getBytes());
        this.phones = new ArrayList<>();
        this.modified = new Date();
        this.lastLogin = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID guid) {
        this.uuid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Telephone> getPhones() {
        return phones;
    }

    public void setPhones(List<Telephone> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
