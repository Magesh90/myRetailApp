package com.myretailapp.service

import com.myretailapp.constants.MyRetailAppConstants
import com.myretailapp.dao.ProductPriceDao
import com.myretailapp.domain.*
import com.myretailapp.exception.UnExpectedException
import com.myretailapp.http.client.RedskyRestClient
import com.myretailapp.mongodb.repositories.ProductPriceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component

import java.util.HashMap
import java.util.Map
import java.util.Optional

@Component
class ProductService {

    @Autowired
    private RedskyRestClient redskyRestClient

    @Value('${redskyrestclient.productdetails}')
    private String productDetailsUri

    @Autowired
    ProductPriceDao productPriceDao

    @Autowired
    private ProductPriceRepository productPriceRepository

    Map buildUriParams(int id) {
        HashMap<String, Integer> uriParams = new HashMap<>()
        uriParams.put(MyRetailAppConstants.PRODUCT_ID, id)
        uriParams
    }

    String getTitleNameFromProductDetails(Product product) {
        String title = product.item?.productDescription?.title
        if (!title) {
            throw new UnExpectedException("Unable to retrieve the title from Redsky")
        }
        title
    }

    ProductInformation getProductDetails(int id) {
        Price productPrice = null
        try {
            productPrice = productPriceRepository.findProductPriceById(id)
        } catch (Throwable exception) {
            productPriceDao.handleMongoDbException(exception)
        }
        ProductDetails productDetails = (ProductDetails) redskyRestClient.exchange(HttpMethod.GET, productDetailsUri, buildUriParams(id), null, ProductDetails.class)
        ProductInformation productInformation = new ProductInformation(id: id)
        productInformation.name = getTitleNameFromProductDetails(productDetails.product)
        productInformation.price = productPrice
        productInformation
    }

    void updateProductPrice(int id, Double price) {
        productPriceDao.updateProductPrice(id, price)
    }

    void updateProductPrice(Price priceInformation) {
        productPriceDao.updateProductPrice(priceInformation.id, priceInformation.value)
    }
}