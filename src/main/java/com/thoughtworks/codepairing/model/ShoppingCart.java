package com.thoughtworks.codepairing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;
import static java.util.Arrays.asList;

public class ShoppingCart {
    private HashMap<String, List<Product>> products;
    private Customer customer;

    public ShoppingCart(Customer customer, HashMap<String, List<Product>> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        if(products.containsKey(product.getProductCode())){
            products.get(product.getProductCode()).add(product);
        }else{
            List<Product> productList= new ArrayList<>();
            productList.add(product);
            products.put(product.getProductCode(), productList);
        }
    }

    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        for(Map.Entry<String, List<Product>> entry: products.entrySet()){
//            int quantity = entry.getValue().size();
            if(entry.getKey().startsWith("BULK_BUY_2_GET_1")){
                Product product = entry.getValue().get(0);
//                System.out.println(product.getPrice());
                int quantity = entry.getValue().size();
//                System.out.println(quantity);
                int discountQuantity = quantity / 3;
                double totalDiscount = product.getPrice() * discountQuantity;
                totalPrice = product.getPrice() * quantity - totalDiscount;
                System.out.println(totalPrice);
            }else {
                for (Product product : entry.getValue()) {
                    double discount = 0;
                    if (product.getProductCode().startsWith("DIS_10")) {
                        discount = (product.getPrice() * 0.1);
                        loyaltyPointsEarned += (product.getPrice() / 10);
                    } else if (product.getProductCode().startsWith("DIS_15")) {
                        discount = (product.getPrice() * 0.15);
                        loyaltyPointsEarned += (product.getPrice() / 15);
                    } else if (product.getProductCode().startsWith("DIS_20")) {
                        discount = (product.getPrice() * 0.2);
                        loyaltyPointsEarned += (product.getPrice() / 20);
                    } else {
                        loyaltyPointsEarned += (product.getPrice() / 5);
                    }

                    totalPrice += product.getPrice() - discount;
                }
            }
        }


        return new Order(totalPrice, loyaltyPointsEarned);
    }

    @Override
    public String toString() {
        String present1 = "Customer: " + customer.getName() + "\n" + "Bought:  \n";
        String present2 = "";
        for(Map.Entry<String, List<Product>> entry: products.entrySet()){
            Product p = entry.getValue().get(0);
            present2 += "-" + p.getName() + ", " + p.getPrice() + ", " + "quantity: " + entry.getValue().size() + "\n";
        }
        return (present1 + present2);
    }
}
