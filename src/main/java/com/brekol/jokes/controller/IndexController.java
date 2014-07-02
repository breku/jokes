package com.brekol.jokes.controller;

import com.brekol.jokes.model.User;
import com.brekol.jokes.service.CounterService;
import com.brekol.jokes.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Controller
public class IndexController {

    @Autowired
    private MongoService mongoService;

    @Autowired
    private CounterService counterService;

    @RequestMapping("/index")
    public String index() {
        mongoService.getMongoOperation().remove(new Query(), User.class);
        User u1 = new User(counterService.getNextSequence("users"), "u1", "u1@o2.pl");
        User u2 = new User(counterService.getNextSequence("users"), "u2", "u2@o2.pl");


        MongoOperations mongoOperation = mongoService.getMongoOperation();
        mongoService.getMongoOperation().save(u1);
        mongoService.getMongoOperation().save(u2);

        // query to search user
        Query searchUserQuery = new Query(Criteria.where("username").is("u1"));

        // find the saved user again.
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        System.out.println("2. find - savedUser : " + savedUser);

        // update password
        mongoOperation.updateFirst(searchUserQuery,
                Update.update("email", "new password"), User.class);

        // find the updated user object
        User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);

        System.out.println("3. updatedUser : " + updatedUser);

        // delete
        mongoOperation.remove(searchUserQuery, User.class);

        // List, it should be empty now.
        List<User> listUser = mongoOperation.findAll(User.class);
        System.out.println("4. Number of user = " + listUser.size());

        return "index";
    }


}
