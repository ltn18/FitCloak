// author: Lam Nguyen

import java.util.*;
import java.io.*;

public class PathFind {
    private int oo = 1000000;
    private Point[] points = new Point[oo];
    private int pointCount = 0;

    public Point[] getPoints() { return points; }
    public void setPoints(Point[] ps) { points = ps; }
    public Point getOnePoint(int i) { return points[i]; }
    public void setOnePoint(int i, Point p) { points[i] = p; }
    public int getPointCount() { return pointCount; }
    public void setPointCount(int i) { this.pointCount = i; }


}
