package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
//        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
//        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
//        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
//        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor20PercentDiscount() {
//        List<Product> products = asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(80, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor20PercentDiscount() {
//        List<Product> products = asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT));
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        products.put("", asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT)));
        Order order = cart.checkout();

        assertEquals(5, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceForBuyTwoGetOne() {
        Product product = new Product(PRICE, "BULK_BUY_2_GET_1_ABCD", PRODUCT);
        HashMap<String, List<Product>> products = new HashMap<String, List<Product>>();
        ShoppingCart cart = new ShoppingCart(customer, products);
        cart.addProduct(product);
        cart.addProduct(product);
        cart.addProduct(product);
        Order order = cart.checkout();

        assertEquals(200, order.getTotalPrice(), 0.0);
    }
}
