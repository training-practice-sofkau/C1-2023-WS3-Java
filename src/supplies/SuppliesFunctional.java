package supplies;


import supplies.sales.Database;
import supplies.sales.Product;
import supplies.sales.Sale;

import java.util.*;
import java.util.stream.Collectors;


public class SuppliesFunctional {
    static ArrayList<Sale> sales = Database.loadDatabase();
    public static void main(String[] args) {
        sales.forEach(System.out::println);
        menu();
        loadMenu();


    }

    public static void menu(){
        System.out.println("Supplies sales");
        System.out.println("1. Sales located in NY that use / does not use a coupon");
        System.out.println("2. Customers that gives the lower satisfaction score.");
        System.out.println("3. Sold supplies");
        System.out.println("4. Sales categorized per year");
        System.out.println("5. Amount of products per tag");
        System.out.println("6. Exit");

    }

    public static void loadMenu(){
        Scanner sc = new Scanner(System.in);
        menu();
        System.out.print("Type option: ");
        String op=sc.nextLine();
        switch(op){
            case "1":
                System.out.println(couponRedemption(sales));
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
            default:
                System.out.println("Invalid input. Try again.");
        }

    }

    public static Map<Boolean, List<Sale>> couponRedemption (List<Sale> sales){
        List<Sale> inputSales = new ArrayList<>(sales);
        //couponUsed

        inputSales.forEach(a -> System.out.println(a.getLocation().getClass()));
        return inputSales.stream()
                .filter(sale -> sale.getLocation() == "New York")
                .collect(Collectors.groupingBy(Sale::getCouponUsed));
    }
}
