import com.myretailapp.constants.MyRetailAppConstants
import com.myretailapp.service.ProductService
import junit.framework.TestCase
import org.junit.Test

import java.util.Map

class ProductServiceTest extends TestCase {
    ProductService productService = new ProductService()

    @Test
    void testUriParams() {
        Map uriParams = productService.buildUriParams(123)
        assertEquals(123, uriParams.get(MyRetailAppConstants.PRODUCT_ID))
    }
}
