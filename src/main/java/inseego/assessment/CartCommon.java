package inseego.assessment;

import java.util.ArrayList;
import java.util.List;

public class CartCommon {

    protected List<Order> orders;

    public CartCommon(){
        this.orders = new ArrayList<Order>();
    }

    protected List<Order> getOrders(){
        return this.orders;
    }
}
