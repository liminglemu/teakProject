package common;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/15
 */
public class Person {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getAge() {
        return age;
    }

    public Person setAge(String age) {
        this.age = age;
        return this;
    }

    public void test1(Integer integer) {
        System.out.println("integer = " + integer);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
