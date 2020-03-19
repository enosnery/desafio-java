package com.enosnery.desafiojava.request;

import java.util.List;

public class AddUserRequest {
    public String name;
    public String email;
    public String password;
    public List<PhoneRequest> phones;
}
