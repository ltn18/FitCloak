// author: Lam Nguyen
import java.util.*;
import java.io.*;

public class IO {
    public String dir = System.getProperty("user.dir") + "\\src\\";
    public String inputFileName = "small_in.txt"; // inputJava.txt for normal
    public String outputFileName = "outputJava.txt";
    private Locations lcs = new Locations();

    public Point[] io() throws Exception {
        File filein = new File(dir + inputFileName);
        Scanner in = new Scanner(filein);

//        File fileout = new File(dir + outputFileName);
//        PrintWriter out = new PrintWriter(fileout);

        Point[] points = this.lcs.getPoints();

        // input process
        String name = "", latlng = "";
        boolean isName = true;

        while (in.hasNextLine()) {
            if (isName) {
                name = in.nextLine();
                isName = false;
            }
            else {
                latlng = in.nextLine();
                double x =  Double.parseDouble(latlng.split(" ")[0]);
                double y = Double.parseDouble(latlng.split(" ")[1]);
                Point newPoint = new Point(name, x, y);
                newPoint.setID(lcs.getPointCount());
                points[lcs.getPointCount()] = newPoint;
                lcs.setPointCount(lcs.getPointCount()+1);
                isName = true;
            }
        }

        // optimize size of result
        Point[] res = new Point[this.lcs.getPointCount()];
        for (int i = 0; i < this.lcs.getPointCount(); i++) res[i] = points[i];

//        out.println("");
//        out.close();

        return res;
    }

    public double[][] genMatrix() throws Exception {
        Point[] points = io();
        double[][] matrix = new double[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) matrix[i][j] = 0;
                else matrix[i][j] = lcs.distance(points[i], points[j]);
            }
        }
        return matrix;
    }
}

