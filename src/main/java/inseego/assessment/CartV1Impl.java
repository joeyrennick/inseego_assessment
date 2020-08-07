package inseego.assessment;

import java.util.ArrayList;
import java.util.List;

public class CartV1Impl extends CartCommon implements Cart{

    public CartV1Impl() {
    }

    @Override
    public void addToCart(String plan) {
        /*
        this would contain the logic to actually add the order to the Cort
        This specific implementation, which is for version1, would look something like

        - Select Product
        - Select Plan

        This would of course be using the Selenium library, and also be using a Page object model,
        encapsulating the approprate element IDs within the page in question.

         */
    }

    @Override
    public void addMoreThanThree(String plan) {
        /*
        This would be similar to the addToCart() methods in CartV1Impl and CartV2Impl,
        except that we would be verify, with the help of Selenium, that an error
        is thrown in the UI after the attempt has bee made
        This specific implementation, which is for version1, would look something like

       - Select Product
        - Select Plan
        - verify that error is thrown in the UI
         */
    }

    @Override
    public void addOrderInvalidVersion(String plan) {
        /*
        Similar as addMoreThanThree, ecept that we would be verifying that an error is displayed
        when the user tries to select a version that is different from one that is already added to the cart
         */
    }


}
