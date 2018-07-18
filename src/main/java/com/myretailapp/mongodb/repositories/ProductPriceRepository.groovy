package com.myretailapp.mongodb.repositories

import com.myretailapp.domain.Price
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ProductPriceRepository extends MongoRepository<Price, String> {

    @Query(value = "{'id':?0}")
    Price findProductPriceById(int id)
}
