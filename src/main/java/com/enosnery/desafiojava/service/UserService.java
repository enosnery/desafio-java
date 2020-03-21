package com.enosnery.desafiojava.service;

import com.enosnery.desafiojava.model.Telephone;
import com.enosnery.desafiojava.model.User;
import com.enosnery.desafiojava.repository.TelephoneRepository;
import com.enosnery.desafiojava.repository.UserRepository;
import com.enosnery.desafiojava.request.AddUserRequest;
import com.enosnery.desafiojava.request.LoginRequest;
import com.enosnery.desafiojava.request.PhoneRequest;
import com.enosnery.desafiojava.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    public Object addUser(AddUserRequest userForm) {
        if (userRepository.existsUserByEmail(userForm.email)) {
            return Constants.EMAIL_EXISTS;
        }else{
            User newUser = new User(userForm);
            for (PhoneRequest newPhone : userForm.phones) {
                Telephone phone = new Telephone(newPhone);
                phone = telephoneRepository.save(phone);
                if (phone.getId() != 0) {
                    newUser.getPhones().add(phone);
                }
            }
            newUser = userRepository.save(newUser);
            return newUser;
        }
    }

    public Object login(LoginRequest request){
        User loggingUser = userRepository.findByEmail(request.email);
        if(loggingUser != null) {
            if (userRepository.existsUserByEmailAndPassword(loggingUser.getEmail(), request.password)) {
                loggingUser.setLastLogin(new Date());
                return userRepository.save(loggingUser);
            }
        }
        return Constants.NOT_AUTHORIZED;
    }

    public boolean findAuthorization(String token){
        return userRepository.existsByToken(token);
    }

    public Object validateUser(String guid, String token){
        if(findAuthorization(token)) {
            User user = userRepository.findByUuid(UUID.fromString(guid));
            if (user != null) {
                if (user.getToken().equalsIgnoreCase(token)) {
                    if (validateSessionTime(user.getLastLogin().toInstant())) {
                        return user;
                    }else{
                        return Constants.INVALID_SESSION;
                    }
                }
            }
        }
        return Constants.NOT_AUTHORIZED;
    }

    public boolean validateSessionTime(Instant lastLogin){
        Instant currentTime = new Date().toInstant();
        return Duration.between(lastLogin, currentTime).toMinutes() <= 30;
    }
}
