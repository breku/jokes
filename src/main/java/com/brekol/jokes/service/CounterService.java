package com.brekol.jokes.service;

import com.brekol.jokes.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Service
public class CounterService {

    @Autowired
    private MongoService mongoService;

    public long getNextSequence(String collectionName) {
        Query query = Query.query(Criteria.where("_id").is(collectionName));
        Update update = new Update().inc("seq", 1);
        Counter counter = mongoService.getMongoOperation().findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true), Counter.class);
        if (counter == null) {
            mongoService.getMongoOperation().save(new Counter(collectionName, 0));
            counter = mongoService.getMongoOperation().findAndModify(query, update,
                    FindAndModifyOptions.options().returnNew(true), Counter.class);
        }

        return counter.getSeq();
    }
}
