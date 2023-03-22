/*
    Bader Daka - 208219212
    Noor Haj Dawood - 314997602
    Demo Video - https://drive.google.com/file/d/1fQb5Xr0RCc74TNzWT2eK8D3xVodKcAZm/view?usp=sharing
 */
package il.ac.shenkar.courses.java;

/**
 * Student class with private fields:
 * String id, String name, double average
 */
public class Student implements Comparable {
    /**
     * test.A String to save the id of a student
     */
    private String id;
    /**
     * test.A String to save the name of a student
     */
    private String name;
    /**
     * test.A double to save the average of a student
     */
    private double average;

    /**
     * Constructor which assigns all three values
     *
     * @param id    String value of student's id
     * @param name  String value of student's name
     * @param average   double value of student's average
     */
    public Student(String id, String name, double average) {
        setId(id);
        setName(name);
        setAverage(average);
    }

    /**
     * Student toString
     *
     * @return  String representing a student
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }

    /**
     * compare by average
     *
     * @param o the object to be compared.
     * @return  0 if equal, 1 if this is larger, -1 if this is smaller
     */
    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        double difference = this.average - other.average;
        if (difference > 0) {
            return 1;
        }
        if (difference < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * return ID value
     *
     * @return  String of this student's id
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID value if the value is positive
     *
     * @param id String of this student's new id
     */
    private void setId(String id) {
        if (id.length() == 9) {
            this.id = id;
        }
    }

    /**
     * return Name value
     *
     * @return String of this student's name
     */
    public String getName() {
        return name;
    }

    /**
     * set Name value
     *
     * @param name  String of this student's new name
     */
    public void setName(String name) {
        if(!name.equals("")) {
            this.name = name;
        }
    }

    /**
     * get Average value
     *
     * @return  double of this student's average
     */
    public double getAverage() {
        return average;
    }

    /**
     * set Average value if param value >= 0
     *
     * @param average   double of this student's new average
     */
    public void setAverage(double average) {
        if (average >= 0 && average <= 100) {
            this.average = average;
        }
    }
}
