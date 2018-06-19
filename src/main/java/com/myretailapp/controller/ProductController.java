package com.myretailapp.controller;

import com.myretailapp.domain.ProductInformation;
import com.myretailapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductInformation productDetails(@PathVariable("id") int id) {
        return productService.getProductDetails(id);
    }
}