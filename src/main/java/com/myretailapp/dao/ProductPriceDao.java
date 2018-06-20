package com.myretailapp.dao;

import com.myretailapp.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public void updateProductPrice(int id, Double price) {
        Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("value", price);
        mongoTemplate.findAndModify(query, update, Price.class);
    }
}
