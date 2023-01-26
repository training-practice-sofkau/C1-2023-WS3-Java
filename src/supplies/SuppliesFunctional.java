package supplies;


import supplies.sales.Customer;
import supplies.sales.Database;
import supplies.sales.Product;
import supplies.sales.Sale;
import warmingup.People;

import java.util.*;
import java.util.stream.Collectors;


public class SuppliesFunctional {
    static ArrayList<Sale> sales = Database.loadDatabase();
    public static void main(String[] args) {

        loadMenu();
        //sales.forEach(System.out::println);

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
        String op;
        do{
            System.out.print("Type option: ");
            op=sc.nextLine();
            switch(op){
                case "1":
                    System.out.println(salesNyAndCoupon(sales));
                    break;
                case "2":
                    System.out.println(customersLowestSatisfaction(sales));
                    break;
                case "3":
                    System.out.println(productsSold(sales));
                    break;
                case "4":
                    System.out.println(salesPerYear(sales));
                    break;
                case "5":
                    System.out.println(productsPerTag(sales));
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while(!op.equalsIgnoreCase("0"));


    }

    public static Map<String,List<Sale>> salesNyAndCoupon (ArrayList<Sale> sales){

        return sales.stream().filter(s-> Objects.equals(s.getLocation(), "New York")).collect(Collectors.groupingBy(s-> {
            if(s.getCouponUsed()) return "\nUse\n";
            return "\nNo Use\n";
        },Collectors.toList()));
    }

    public static List<Customer> customersLowestSatisfaction (ArrayList<Sale> sales){

        int lowest = sales.stream().min(Comparator.comparing(s -> s.getCustomer().getSatisfaction())).get().getCustomer().getSatisfaction();

        return sales.stream().map(Sale::getCustomer).filter(customer -> customer.getSatisfaction()==lowest).collect(Collectors.toList());
    }

    public static Set<String> productsSold(ArrayList<Sale> sales){

        return sales.stream().flatMap(s -> s.getItems().stream()).map(Product::getName).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Map<String,List<Sale>> salesPerYear(ArrayList<Sale> sales){

        return sales.stream().collect(Collectors.groupingBy(s->"\n\n"+(s.getSaleDate().getYear()+1900)+"\n\n",TreeMap::new, Collectors.toList())).descendingMap();
    }

    public static Map<String,Long> productsPerTag(ArrayList<Sale> sales){

        return sales.stream().flatMap(s->s.getItems().stream()).collect(Collectors.toList()).stream().flatMap(p->p.getTags().stream()).collect(Collectors.groupingBy(String::valueOf,Collectors.counting())); // How many products were bought per tag
        //return sales.stream().flatMap(s->s.getItems().stream()).distinct().collect(Collectors.toList()).stream().flatMap(p->p.getTags().stream()).collect(Collectors.groupingBy(String::valueOf,Collectors.counting())); // How many products are part of a tag
    }


}
