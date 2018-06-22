

import com.myretailapp.constants.MyRetailAppConstants
import com.myretailapp.http.client.RedskyRestClient
import com.myretailapp.service.ProductService
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    RedskyRestClient redskyRestClient
    ProductService productService

    void setup() {
        productService = new ProductService(
                redskyRestClient: redskyRestClient,
                productDetailsUri : 'mockString'
        )
    }

    void 'test productService uriParams'() {

        when:
        Map uriParams = productService.buildUriParams(123)

        then:
        uriParams
        uriParams[MyRetailAppConstants.PRODUCT_ID] == 123
    }
}
