package com.projects.java.social.services;

import java.util.List;
import java.util.Optional;

import com.projects.java.social.models.Buzz;
import com.projects.java.social.repositories.BuzzRepository;

import org.springframework.stereotype.Service;


@Service
public class BuzzService {
    private final BuzzRepository buzzRepo;

    public BuzzService(BuzzRepository buzzRepo){
        this.buzzRepo = buzzRepo;
    }

    public List<Buzz> allBuzzes(){
        return (List<Buzz>)buzzRepo.findAll();
    }

    public Buzz createBuzz(Buzz d){
        return this.buzzRepo.save(d);
    }

    public Buzz findBuzzById(Long id){
        Optional<Buzz> optionalBuzz = buzzRepo.findById(id);
        if(optionalBuzz.isPresent()){
            return optionalBuzz.get();
        }
        return null;
    }

    public Buzz updateBuzz(Buzz b){
        
        return buzzRepo.save(b);
    }

    public void deleteBuzz(Long id){
        buzzRepo.deleteById(id);
    }

    
}
