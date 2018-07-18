package com.myretailapp.controller

import com.myretailapp.domain.Price
import com.myretailapp.domain.ProductInformation
import com.myretailapp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductService productService

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    ProductInformation productDetails(@PathVariable("id") int id) {
         productService.getProductDetails(id)
    }

    @RequestMapping(value = "/{id}/{price}", method = RequestMethod.PUT)
    @ResponseBody
    void updateProductPriceThroughPath(@PathVariable("id") int id,@PathVariable("price") Double price) {
        productService.updateProductPrice(id,price)
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    void updateProductPriceThroughBody(@RequestBody Price price) {
        productService.updateProductPrice(price)
    }
}