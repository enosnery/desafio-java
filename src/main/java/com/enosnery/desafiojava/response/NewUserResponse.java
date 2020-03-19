package com.enosnery.desafiojava.response;

import com.enosnery.desafiojava.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class NewUserResponse {
    public UUID id;
    public String created;
    public String modified;
    @JsonProperty("last_login")
    public String lastLogin;
    public String token;

    public NewUserResponse(){

    }

    public NewUserResponse(User user){
        this.id = user.getGuid();
        this.created = new SimpleDateFormat("dd/MM/yyyy").format(user.getCreated());
        this.modified =  new SimpleDateFormat("dd/MM/yyyy").format(user.getModified());
        this.lastLogin =  new SimpleDateFormat("dd/MM/yyyy").format(user.getLastLogin());
        this.token = user.getToken();
    }
}
