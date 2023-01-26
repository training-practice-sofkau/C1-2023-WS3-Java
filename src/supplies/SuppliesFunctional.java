package supplies;


import supplies.sales.Customer;
import supplies.sales.Database;
import supplies.sales.Product;
import supplies.sales.Sale;

import java.util.*;
import java.util.stream.Collectors;


public class SuppliesFunctional {
    static ArrayList<Sale> sales = Database.loadDatabase();

    public static void main(String[] args) {
        //loadMenu();
        //sales.forEach(System.out::println);

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
                System.out.println(ex1SalesNY(sales));
                break;
            case "2":
                System.out.println(ex2CustomerSat(sales));
                break;
            case "3":
                System.out.println(ex3Products(sales));
                break;
            case "4":

                break;
            case "5":
                System.out.println(ex5SalesPerYear(sales));
                break;
            default:
                System.out.println("Invalid input. Try again.");
        }

    }

    public static Map<String, List<Sale>> ex1SalesNY (List<Sale> salesNY) {
        //issue renaming keys
        return salesNY.stream().collect(Collectors.groupingBy(sale -> sale.getCouponUsed() ? "Used": "Not used"));

    }

    public static List<Customer> ex2CustomerSat (List<Sale> salesNY) {
        OptionalInt lessSat = salesNY.stream().map(sale -> sale.getCustomer().getSatisfaction()).mapToInt(Integer::valueOf).min();
        return salesNY.stream()
                .map(sale -> sale.getCustomer())
                .filter(customer -> customer.getSatisfaction()==lessSat.getAsInt())
                .collect(Collectors.toList());
    }


    public static List<String> ex3Products (List<Sale> salesNY){
        return salesNY.stream()
                .flatMap(sale -> sale.getItems().stream().map(item->item.getName()))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }



    public static Map<List<String>, Long> ex4ProductsPerTag (List<Sale> salesNY) {
        //get a list of unique tags
        //with streams make a filter with each tag and then unique and then count operation of the items

        //List<String> tags = salesNY.stream().flatMap(sale -> sale.getItems().stream().map(items ->items.getTags())).distinct().collect(Collectors.toList());

         //collect(Collectors.toList())),
                             //   Collectors.counting()));
                                //Collectors.mapping(Sale::getSaleDate, Collectors.toList())));

        return null;
    };





    public static TreeMap<Integer, List<Date>> ex5SalesPerYear (List<Sale> salesNY) {
        Map<Integer, List<Date>> temp = salesNY.stream().collect(Collectors.groupingBy(sale -> sale.getSaleDate().getYear()+1900, TreeMap::new, Collectors.mapping(Sale::getSaleDate, Collectors.toList())));
        return (TreeMap)temp;

    }









}
