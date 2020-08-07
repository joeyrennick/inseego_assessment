package inseego.assessment;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.List;

public class Order {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    private final String orderId;
    private Cart cart;
    private String plan;
    private User user;

    public Order(String orderId, Cart cart, String plan, User user) {
        this.orderId = orderId;
        this.cart = cart;
        this.plan = plan;
        this.user = user;
    }

    public void placeOrder(){
        cart.addToCart(this.plan);

    }

    public void placeOrderExceedingMax(){
        cart.addMoreThanThree(this.plan);
    }

    public void placeOrderInvalidVersion(){
        cart.addOrderInvalidVersion(this.plan);
    }

    public String getOrderId(){
        return this.orderId;
    }
    public String getUserId(){
        return this.user.getUserId();
    }

    //Typically you would have a separate DAO class which would perform action on the DB,
    //I'm including some of these operations here for simplicity sake
    public Order getOrderFromDB(){
        Order order = null;
        // Here we will use the userId to retried the order from the database
        // example:
        //order = DBclassObj.getOrderByID(this.user.getUserId());
        return order;
    }

    public List<Order> getOrdersByUserId(String userId){
        List<Order> order = null;
        // Here we will use the userId to retried the order from the database
        // example:
        //order = DBclassObj.getOrdersByID(userId);
        return order;
    }

    public static void printOrderByUserID(List<String> userIds){
            List<Order> orders = null;
            for(String id: userIds){
                // Here we will use the userId to retried the order from the database
                //orders = DBclassObj.getOrdersByID(userId);
                for(Order order: orders){
                    logger.debug("UserID: {}, Product Plan = {}", order.orderId, order.plan);
                }
            }


    }

}
