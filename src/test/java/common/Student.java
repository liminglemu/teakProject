package common;


/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/15
 */
public class Student extends Person {
    private String sno;
    private String className;

    public String getSno() {
        return sno;
    }

    public Student setSno(String sno) {
        this.sno = sno;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public Student setClassName(String className) {
        this.className = className;
        return this;
    }

    public void test(Integer integer) {
        System.out.println("integer = " + integer);
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", className='" + className + '\'' +
                "} " + super.toString();
    }
}
