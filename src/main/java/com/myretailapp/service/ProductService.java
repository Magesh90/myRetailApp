package com.myretailapp.service;

import com.myretailapp.constants.MyRetailAppConstants;
import com.myretailapp.dao.ProductPriceDao;
import com.myretailapp.domain.*;
import com.myretailapp.exception.UnExpectedException;
import com.myretailapp.http.client.RedskyRestClient;
import com.myretailapp.mongodb.repositories.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private RedskyRestClient redskyRestClient;

    @Value("${redskyrestclient.productdetails}")
    private String productDetailsUri;

    @Autowired
    ProductPriceDao productPriceDao;

    @Autowired
    private ProductPriceRepository productPriceRepository;

    public Map buildUriParams(int id) {
        HashMap<String, Integer> uriParams = new HashMap<>();
        uriParams.put(MyRetailAppConstants.PRODUCT_ID, id);
        return uriParams;
    }

    String getTitleNameFromProductDetails(Product product) {
        return Optional.of(product).map(Product::getItem).
                map(Item::getProductDescription).
                map(ProductDescription::getTitle).orElseGet(() -> {
            throw new UnExpectedException("Unable to retrieve the title from Redsky");
        });
    }

    public ProductInformation getProductDetails(int id) {
        ProductDetails productDetails = null;
        productDetails = (ProductDetails) redskyRestClient.exchange(HttpMethod.GET, productDetailsUri, buildUriParams(id), null, ProductDetails.class);
        ProductInformation productInformation = new ProductInformation();
        productInformation.setName(getTitleNameFromProductDetails(productDetails.getProduct()));
        productInformation.setId(id);
        Price productPrice = null;
        try {
            productPrice = productPriceRepository.findProductPriceById(id);
        } catch (Throwable exception) {
            productPriceDao.handleMongoDbException(exception);
        }
        productInformation.setPrice(productPrice);
        return productInformation;
    }

    public void updateProductPrice(int id, Double price) {
        productPriceDao.updateProductPrice(id, price);
    }

    public void updateProductPrice(Price priceInformation) {
        productPriceDao.updateProductPrice(priceInformation.getId(), priceInformation.getValue());
    }
}