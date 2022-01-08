package com.projects.java.social.services;
import java.util.List;
import java.util.Optional;

import com.projects.java.social.models.Buzz;
import com.projects.java.social.models.User;
import com.projects.java.social.repositories.BuzzRepository;
import com.projects.java.social.repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UserRepository userRepo;
    private final BuzzRepository buzzRepo;
    
    
    public UserService(UserRepository userRepo, BuzzRepository buzzRepo) {
        this.userRepo = userRepo;
        this.buzzRepo = buzzRepo;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String hashedCon = BCrypt.hashpw(user.getPasswordConfirmation(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setPasswordConfirmation(hashedCon);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);

    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }

    public Buzz findBuzzById(Long id) {
    	Optional<Buzz> b = buzzRepo.findById(id); 
    	
    	if(b.isPresent()) {
            return b.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    //check if zipped
    public Boolean isZipped(long bId, long uId){
        //get id of user and buzz
        User thisUser = findUserById(uId);
        Buzz thisBuzz = findBuzzById(bId);
        //get list of user zips
        List<Buzz> theseZips = thisUser.getZips();
        //check if zip is contained in side of user zipped buzzes array
        if(theseZips.contains(thisBuzz)){
            return true;
        }

        return false;
    }

    //zip buzz
    public User zipBuzz(long bId, long uId){
        //get id of user and buzz
        User thisUser = findUserById(uId);
        Buzz thisBuzz = findBuzzById(bId);
        
        //add buzz to users list of zips
        thisUser.getZips().add(thisBuzz);

        //save user
        return userRepo.save(thisUser);
    }

    
    //unzip buzzes
    public User unzipBuzz(long bId, long uId){
        //get id of user and buzz
        User thisUser = findUserById(uId);
        Buzz thisBuzz = findBuzzById(bId);
        
        //remove buzz to users list of zips
        thisUser.getZips().remove(thisBuzz);

        //save user
        return userRepo.save(thisUser);
    }

}
