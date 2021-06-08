// author: Lam Nguyen
public class Point {
    private double x;
    private double y;
    private String name;
    private int _id;

    public double getX() { return x; }
    public double getY() { return y; }
    public String getName() { return name; }
    public int getID() { return _id; }
    public void setID(int _id) { this._id = _id; }

    public Point() {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this._id = -1;
    }

    public Point(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
