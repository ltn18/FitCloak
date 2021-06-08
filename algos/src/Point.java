// author: Lam Nguyen
public class Point {
    private double x;
    private double y;
    private String name;
    public double getX() { return x; }
    public double getY() { return y; }
    public String getName() { return name; }

    public Point(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
