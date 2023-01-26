package warmingup;

import java.util.*;
import java.util.stream.Collectors;

public class WarmingUp {
    public static void main(String[] args) {
        System.out.println(lengthMap(List.of("apple","melon","pinneapple","mango","lemon","blueberry","banana","strawberry","kiwi","maracuya","pitahaya")));

        System.out.println(filterEven(List.of(1,3,4,5,20,80,17,64,85,12,14)));
        //System.out.println(filterEven(List.of(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)));
        //System.out.println(filterEven(List.of(2,2,4,66,8,4,86,76,24)));

        System.out.println(filterString(List.of("soup", "principle", "xbox", "exit", "abandon", "above",
                "academic", "accident", "accompany", "according", "accomplish")));

        System.out.println(gruppingByLetter(List.of("Antoni","Anita", "Veronica", "Alberto", "Ana",
                "Maria", "Magali", "Zulma", "Diego", "Daisy", "Sandra", "Samanta")));

        People p1=new People("Diego", 24, "M");
        People p2=new People("Camila", 23, "F");
        People p3=new People("Caro", 30, "F");
        People p4=new People("Camilo", 24, "M");
        People p5=new People("Viviana", 41, "F");
        People p6=new People("Antonio", 25, "M");
        People p7=new People("Zulma", 23, "F");
        People p8=new People("Carmen", 85, "F");
        People p9=new People("juan", 25, "M");
        People p10=new People("Freddy", 125, "M");
        System.out.println(gruppingPeopleByAge(List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)));

        System.out.println(filterMorePeople(List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)));


    }

    // First Excercise
    public static Integer filterEven (List<Integer> list){
        return list.stream().filter(i->i%2!=0).map(x->x*x).
                collect(Collectors.summingInt(Integer::intValue));
        //return null;
    }

    // Second excercise
    public static List<String> filterString(List<String> list){
        return list.stream().
                filter(i->i.length()>5).
                collect(Collectors.toList()).
                stream().map(i->i.toUpperCase()).
                collect(Collectors.toList());
    }

    //3rd excercise
    public static Map<Integer,List<String>> lengthMap(List<String> words){
        //First I need to know the length of the words
        return words.stream().collect(Collectors.groupingBy(String::length));
    }

    //4th excercise
    public static Map<Character, List<String>> gruppingByLetter(List<String> list){

        //list.stream().collect(Collectors.groupingBy(s->s.charAt(0)));

        return list.stream().collect(Collectors.groupingBy(s->s.charAt(0)));
    }

    //5th excercise
    public static Map<Integer, List<People>> gruppingPeopleByAge (List<People> list){
        return list.stream().collect(Collectors.groupingBy(s->s.getAge()));
    }

    //6th excercise
    public static List<String> reverseString (List<String> list){
        List<String> p = list.stream().
                filter(i->i.length()<4).collect(Collectors.toList());

        return null;
    }

    //7th excercise
    public static List<People> filterMorePeople (List<People> list){

        //.stream().sorted(Comparator.comparingInt(People::getAge)).collect(Collectors.toList())
        return list.stream().
                filter(i->i.getAge()>40).
                collect(Collectors.toList())
                .stream().
                sorted(Comparator.comparingInt(People::getAge).reversed()).
                collect(Collectors.toList());

    }
}
