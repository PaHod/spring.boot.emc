package com.spring.boot.emc.welcome;

import com.spring.boot.emc.model.Doctor;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public void sendGreetingsToNewDoctor(Doctor savedDoctor) {
        //send greeting in any possible way.
    }
}
