package com.geekster.Instagram.services;

import com.geekster.Instagram.models.AuthenticationToken;
import com.geekster.Instagram.models.User;
import com.geekster.Instagram.repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    ITokenRepo iTokenRepo;

    public void saveToken(AuthenticationToken token){
        iTokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user){
        return iTokenRepo.findByUser(user);
    }

    public boolean authenticate(String email,String token){
        AuthenticationToken authenticationToken = iTokenRepo.findFirstByToken(token);
        Optional<String> expectedMail = Optional.ofNullable(authenticationToken.getUser().getEmail());
        if(expectedMail.isPresent()){
            return true;
        }else
            return false;
    }
}
