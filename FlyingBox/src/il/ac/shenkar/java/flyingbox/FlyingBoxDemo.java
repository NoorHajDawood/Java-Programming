package il.ac.shenkar.java.flyingbox;

public class FlyingBoxDemo {
    public static void main(String[] args) {
        Box box = new Box(10, 10, 10);
        Box.Fly fly = box.new Fly(2, 4, 5);
        fly.setX(3);
        fly.setY(8);
        fly.setZ(15);
        System.out.println(box);
        System.out.println(fly);
    }
}
