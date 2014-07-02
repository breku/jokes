package com.brekol.jokes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Service
public class MongoService {

    @Autowired
    private MongoTemplate template;


    public MongoOperations getMongoOperation() {
        return (MongoOperations) template;
    }

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }
}
