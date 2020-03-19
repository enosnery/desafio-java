package com.enosnery.desafiojava.service;

import com.enosnery.desafiojava.model.Telephone;
import com.enosnery.desafiojava.model.User;
import com.enosnery.desafiojava.repository.TelephoneRepository;
import com.enosnery.desafiojava.repository.UserRepository;
import com.enosnery.desafiojava.request.AddUserRequest;
import com.enosnery.desafiojava.request.PhoneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    public User addUser(AddUserRequest userForm){
        User newUser = new User(userForm);
        for(PhoneRequest newPhone : userForm.phones){
            Telephone phone = new Telephone(newPhone);
            phone =  telephoneRepository.save(phone);
            if(phone.getId() != 0){
                newUser.getPhones().add(phone);
            }
        }
        newUser = userRepository.save(newUser);
        return newUser;
    }
}
