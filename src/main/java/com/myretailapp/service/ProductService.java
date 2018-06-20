package com.myretailapp.service;

import com.myretailapp.constants.MyRetailAppConstants;
import com.myretailapp.dao.ProductPriceDao;
import com.myretailapp.domain.Price;
import com.myretailapp.domain.ProductDetails;
import com.myretailapp.domain.ProductInformation;
import com.myretailapp.http.client.RedskyRestClient;
import com.myretailapp.mongodb.repositories.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    Map buildUriParams(int id) {
        HashMap<String, Integer> uriParams = new HashMap<>();
        uriParams.put(MyRetailAppConstants.PRODUCT_ID, id);
        return uriParams;
    }

    public ProductInformation getProductDetails(int id) {
        ProductDetails productDetails = null;
        productDetails = (ProductDetails) redskyRestClient.exchange(HttpMethod.GET, productDetailsUri, buildUriParams(id), null, ProductDetails.class);
        ProductInformation productInformation = new ProductInformation();
        productInformation.setName(productDetails.getProduct().getItem().getProductDescription().getTitle());
        productInformation.setId(id);
        Price productPrice = productPriceRepository.findProductPriceById(id);
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