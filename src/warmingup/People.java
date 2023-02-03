package warmingup;

public class People {
    private final String name;

    private final int age;

    private final String gender; //M, F

    public People(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "{Name: " + name + ", age: " + age + ", gender: " + gender + "}";
    }
}
