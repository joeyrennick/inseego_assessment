package inseego.assessment;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class codeExerciseTest {

    private static final Logger logger = LoggerFactory.getLogger(codeExerciseTest.class);
    private List<Order> orders;


    @BeforeClass
    public void beforeClass(){
        //Load the file here
        orders = new ArrayList<>();
        initializeOrders("orders.csv");
    }

    @AfterMethod
    public void afterMethod(){
        if(!orders.isEmpty()){
            Order.printOrderByUserID(getUniqueUserIDs());
        }
    }


    @Test
    public void happyPathTest(){
        //get single order
        Order order = orders.get(0);
        order.placeOrder();
        checkOut();
        /*
        Validate results
         */
        Order orderVerify = order.getOrderFromDB();
        //Now verify that all items within orderVerify from the DB, match those items within the original order obj
        Assert.assertEquals(order.getOrderId(), orderVerify.getOrderId());
    }

    @Test
    public void addMorThanThreeTest(){

        int orderCount = 0;
        for(Order order: orders){
            if(orderCount < 3) {
                try {
                    order.placeOrder();
                } catch (Exception e) {
                    logger.error("Test failed with the following message {}", e.toString());
                    Assert.fail();

                }
            }
            else{

                try {
                    order.placeOrderExceedingMax();
                    //verification in the UI would have occured within order.placeOrderExceedingMax()

                } catch (Exception e) {
                    logger.error("Test failed with the following message {}", e.toString());
                    Assert.fail();
                }
                break;
            }
            orderCount++;
            /*
            On the fourth attempt we would want to verify that the appropriate error
            was displayed within the UI.
             */
        }

    }

    @Test
    public void addMoreThanOneVersionTest(){
        initializeOrders("ordersInvalidVersion.csv");

        int orderCount = 0;
        for(Order order: orders){
            if(orderCount < 1) {
                try {
                    order.placeOrder();
                } catch (Exception e) {
                    logger.error("Test failed with the following message {}", e.toString());
                    Assert.fail();

                }
            }
            else{

                try {
                    order.placeOrderInvalidVersion();
                    //verification in the UI would have occured within order.placeOrderExceedingMax()

                } catch (Exception e) {
                    logger.error("Test failed with the following message {}", e.toString());
                    Assert.fail();
                }
                break;
            }
            orderCount++;
        }
    }

    protected void checkOut(){
        //Invoke a method from a page obj to select the Checkout button
    }

    public List<String> getUniqueUserIDs(){
        List<String> userId = new ArrayList<>();
        if(!orders.isEmpty()){
            for(Order order: orders){
                if(!userId.contains(order.getUserId())){
                    userId.add(order.getUserId());
                }
            }
        }
        return userId;
    }
    public void initializeOrders(String filePath){
        Path pathToFile = Paths.get(filePath);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                Cart cart;
                String[] attributes = line.split(",");
                if(attributes[0].contains("cartv1")){
                    cart = new CartV1Impl();
                }
                else{
                    cart = new CartV2Impl();
                }
                User user = new User("UserName1", attributes[2]);
                Order order = new Order(attributes[3], cart, attributes[1], user);
                orders.add(order);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
