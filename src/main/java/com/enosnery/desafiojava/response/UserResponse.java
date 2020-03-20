package com.enosnery.desafiojava.response;

import com.enosnery.desafiojava.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class UserResponse {
    public UUID uuid;
    public String created;
    public String modified;
    @JsonProperty("last_login")
    public String lastLogin;
    public String token;

    public UserResponse(){

    }

    public UserResponse(User user){
        this.uuid = user.getUuid();
        this.created = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(user.getCreated());
        this.modified =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(user.getModified());
        this.lastLogin =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(user.getLastLogin());
        this.token = user.getToken();
    }
}
