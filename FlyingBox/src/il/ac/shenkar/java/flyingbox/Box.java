package il.ac.shenkar.java.flyingbox;

public class Box {
    private int xSize;
    private int ySize;
    private int zSize;

    public Box(int xSize, int ySize, int zSize) {
        setxSize(xSize);
        setySize(ySize);
        setzSize(zSize);
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        if(xSize > 0) {
            this.xSize = xSize;
        }
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        if(ySize > 0) {
            this.ySize = xSize;
        }
    }

    public int getzSize() {
        return zSize;
    }

    public void setzSize(int zSize) {
        if(zSize > 0) {
            this.zSize = xSize;
        }
    }

    @Override
    public String toString() {
        return "Box{" +
                "xSize=" + xSize +
                ", ySize=" + ySize +
                ", zSize=" + zSize +
                '}';
    }

    public class Fly {
        private int x;
        private int y;
        private int z;

        public Fly(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            if(x >= 0 && x <= xSize) {
                this.x = x;
            }
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            if(y >= 0 && y <= ySize) {
                this.y = y;
            }
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            if(z >= 0 && z <= zSize) {
                this.z = z;
            }
        }

        @Override
        public String toString() {
            return "Fly{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }
}
