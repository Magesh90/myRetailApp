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
        if (title) {
            title
        } else {
            throw new UnExpectedException("Unable to retrieve the title from Redsky")
        }
    }

    ProductInformation getProductDetails(int id) {
        ProductDetails productDetails = (ProductDetails) redskyRestClient.exchange(HttpMethod.GET, productDetailsUri, buildUriParams(id), null, ProductDetails.class)
        ProductInformation productInformation = new ProductInformation()
        productInformation.setName(getTitleNameFromProductDetails(productDetails.getProduct()))
        productInformation.setId(id)
        Price productPrice = null
        try {
            productPrice = productPriceRepository.findProductPriceById(id)
        } catch (Throwable exception) {
            productPriceDao.handleMongoDbException(exception)
        }
        productInformation.setPrice(productPrice)
         productInformation
    }

    void updateProductPrice(int id, Double price) {
        productPriceDao.updateProductPrice(id, price)
    }

    void updateProductPrice(Price priceInformation) {
        productPriceDao.updateProductPrice(priceInformation.getId(), priceInformation.getValue())
    }
}