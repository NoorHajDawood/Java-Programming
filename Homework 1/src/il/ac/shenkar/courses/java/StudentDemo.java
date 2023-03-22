/*
    Bader Daka - 208219212
    Noor Haj Dawood - 314997602
    Demo Video - https://drive.google.com/file/d/1fQb5Xr0RCc74TNzWT2eK8D3xVodKcAZm/view?usp=sharing
 */
package il.ac.shenkar.courses.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * StudentDemo is the container class of the main function
 */
public class StudentDemo {
    /**
     * main function, sorts a Student array of 10 Students and prints them to default output
     * @param args  executable supplied args
     */
    public static void main(String[] args) {
        // il.ac.shenkar.courses.java.Student array of 10 Students
        Student students[] = {
                new Student("123456789", "stu1", 21),
                new Student("223456789", "stu2", 11),
                new Student("323456789", "stu3", 51),
                new Student("423456789", "stu4", 18),
                new Student("523456789", "stu5", 91),
                new Student("623456789", "stu6", 90),
                new Student("723456789", "stu7", 10),
                new Student("823456789", "stu8", 11),
                new Student("923456789", "stu9", 12),
                new Student("023456789", "stu10", 31),
        };

        // Sort the students array and print them
        Arrays.sort(students);

        for (Student stu : students){
            System.out.println(stu);
        }
    }
}
