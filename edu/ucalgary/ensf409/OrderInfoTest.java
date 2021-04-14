package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;



/**
* @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a> 
* @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
* @version 1.0
* @since 1.0
 */

public class OrderInfoTest {
    
    @Test
    //test that the constructor works
    public void testConstructor(){
        OrderInfo testObj = new OrderInfo();
        assertTrue("OrderInfo object failed to be constructed: ",testObj != null);
    }

    @Test
    //test that orderForm actually creates an output file
    public void testOrderFormCreatesOutput(){
        Inventory testInv = new Inventory();
        OrderInfo testObj = new OrderInfo();
        File testFile = new File("OrderInfo.txt");
        String expectedPath = testFile.getAbsolutePath();
        String dummy = "dummy";
        testObj.orderForm(dummy,dummy,5,dummy);
        assertEquals("", testObj.file.getAbsolutePath(), expectedPath); //have to redo

    }

    //test that updateInventory performs its job

}
