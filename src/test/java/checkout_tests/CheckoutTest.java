package checkout_tests;

import factory.PageObjectFactory;
import org.junit.jupiter.api.Test;
import utils.BaseTestConfig;

public class CheckoutTest extends BaseTestConfig {


    @Test
    public void shouldTestCheckout() throws InterruptedException {
        PageObjectFactory.createMainPage(webDriver)
                .expandProductCategory()
                .selectProductCategory()
                .getRandomProduct()
                .addProductToCart()
                .repeatPickingProductOperations()
                .goToCheckoutPage()
                .fillListWithCheckoutProducts()
                .compereCheckoutWithOrderedProducts()
                .continueCheckout()
                .selectSameBindingAddress()
                .fillTheContactForm()
                .verifyItemPrice()
                .verifyTotalShippingPrice()
                .purchaseCheckout()
                .getAndAssertDataFromTable()
                .assertTotalTransactionCost();
    }
}
