package il.ac.shenkar.java.samples.enums;

enum Month { JAN, FEB, MAR, APR, MAY, JUN, JUL,
    AUG, SEP, OCT, NOV, DEC};

enum Season {WINTER,SPRING,SUMMER,AUTOMN};

class SimpleEnumSample
{
    public static void main(String[] args)
    {
        System.out.println(Season.WINTER);
        System.out.println(Month.JAN);
        System.out.println(Month.FEB);
        System.out.println(Month.MAR);
    }
}