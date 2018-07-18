package com.myretailapp.dao

import com.mongodb.MongoTimeoutException
import com.myretailapp.constants.MyRetailAppConstants
import com.myretailapp.domain.Price
import com.myretailapp.exception.MyRetailAppTimeoutException
import com.myretailapp.exception.UnExpectedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component

@Component
class ProductPriceDao {

    @Autowired
    private MongoTemplate mongoTemplate

    void updateProductPrice(int id, Double price) {
        Query query = new Query()
        query.addCriteria(Criteria.where(MyRetailAppConstants.ID).is(id))
        Update update = new Update()
        update.set(MyRetailAppConstants.VALUE, price)
        try {
            mongoTemplate.findAndModify(query, update, Price.class)
        } catch (Throwable exception) {
            handleMongoDbException(exception)
        }
    }

    void handleMongoDbException(Throwable exception) {
        if (exception instanceof MongoTimeoutException) {
            throw new MyRetailAppTimeoutException("DB connection timed-out")
        }
        throw new UnExpectedException("Unexpected exception occurred during database connection, hence cannot find/update product price")
    }
}
