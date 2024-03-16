package org.gini;

import lombok.extern.slf4j.Slf4j;
import org.gini.model.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceTest {


    public void doSomething(User user) {

        log.info("In service {}", user);


        if(user.age() == 20){
            throw new IllegalArgumentException("Buuuuuuuuuuuuuuum");
        }



    }



}
