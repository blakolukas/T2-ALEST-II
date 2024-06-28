package src;

public class Vertex implements Comparable<Vertex>{
    private int x;
    private int z;
    private int y;

    public Vertex(int x,int y, int z){
        this.x= x;
        this.y= y;
        this.z= z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "["+x + ", " + y + ", " + z + "]";
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.x < o.getX() && this.y < o.getY() && this.z < o.getZ())
            return -1;
        else if (this.x > o.getX() && this.y > o.getY() && this.z > o.getZ())
            return 1;
        else
            return 0;
    }
}