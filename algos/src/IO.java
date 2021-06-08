import java.util.*;
import java.io.*;

public class IO {
    public static String dir = System.getProperty("user.dir") + "\\src\\";
    public static String inputFileName = "inputJava.txt";
    public static String outputFileName = "outputJava.txt";

    public static Point[] io(PathFind ts) throws Exception {
        File filein = new File(dir + inputFileName);
        Scanner in = new Scanner(filein);

//        File fileout = new File(dir + outputFileName);
//        PrintWriter out = new PrintWriter(fileout);

        Point[] points = ts.getPoints();

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
                points[ts.getPointCount()] = newPoint;
                ts.setPointCount(ts.getPointCount()+1);
                isName = true;
            }
        }

        // optimize size of result
        Point[] res = new Point[ts.getPointCount()];
        for (int i = 0; i < ts.getPointCount(); i++) res[i] = points[i];

//        out.println("");
//        out.close();

        return res;
    }

    public static void main(String[] args) throws Exception {
        PathFind ts = new PathFind();
        Point[] points = io(ts);
        for (Point p : points) {
            System.out.println(p.getName());
            System.out.println(p.getX() + " " + p.getY());
        }
    }
}

