// author: Lam Nguyen
import java.util.*;
import java.io.*;

public class Locations {
    private final int oo = 10000; // need to fix out of memory error later
    private Point[] points = new Point[oo];
    private int pointCount = 0;
    // distance matrix
    private double[][] matrix = new double[oo][oo];

    public Point[] getPoints() { return points; }
    public void setPoints(Point[] ps) { points = ps; }
    public Point getOnePoint(int i) { return points[i]; }
    public void setOnePoint(int i, Point p) { points[i] = p; }
    public int getPointCount() { return pointCount; }
    public void setPointCount(int i) { this.pointCount = i; }
    public double[][] getMatrix() { return matrix; }
    public void setMatrix(double[][] m) { this.matrix = m; }

    public double distance(Point p1, Point p2) {
        double lat = (p1.getX() - p2.getX()) * (p1.getX() - p2.getX());
        double lng = (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
        return Math.sqrt(lat + lng);
    }
}
