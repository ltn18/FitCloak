// author: Lam Nguyen
// solving travelling salesman problem using backtrack and branch & bound
// drawback: take a lot of time to run many locations <do time analysis>
import java.util.*;
import java.io.*;

public class TravellingSalesman {
    public static final double oo = 1e9D;
    public static final int maxN = 10000;
    public static double[][] c = new double[maxN][maxN]; // cost array
    public static Point[] points = new Point[maxN]; // points
    public static int[] x = new int[maxN]; // try possibilities
    public static int[] best = new int[maxN]; // best config
    public static double[] t = new double[maxN]; // t[i] = price to go from x[0] -> x[i]
    public static boolean[] avail = new boolean[maxN];
    public static double minCost; // minimum cost

    // constants
    public static String dir = System.getProperty("user.dir") + "\\src\\";
    public static String inputFileName = "small_in.txt"; // inputJava.txt for normal
    public static String outputFileName = "small_out.txt";

    public static void init(int len) {
        x[0] = 0; // start at point 0
        t[0] = 0; // total cost = 0
        for (int i = 0; i < len; i++) best[i] = 0;
        // all nodes available
        for (int i = 0; i < len; i++) avail[i] = true;
        avail[0] = false;
        minCost = oo;
    }

    // copy elements from x to copy, knowing that x and copy having same lengths
    public static void copy(int len) {
        for (int i = 0; i < len; i++) best[i] = x[i];
    }

    public static void attempt(int len, int i) {
        for (int j = 1; j < len; j++) {
            if (avail[j]) {
                x[i] = j;
                t[i] = t[i-1] + c[x[i-1]][j];
                if (t[i] < minCost) {
                    if (i < len-1) {
                        avail[j] = false;
                        attempt(len, i+1);
                        avail[j] = true;
                    }
                    else {
                        if (t[len-1] + c[x[len-1]][0] < minCost) {
                            copy(len); // copy x to best
                            minCost = t[len-1] + c[x[len-1]][0];
                        }
                    }
                }
            }
        }
    }

    public static void printCostArray(int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printBest(int len) throws Exception {
        System.out.println("/Route/");
        for (int i = 0; i < len; i++) System.out.println(points[best[i]].getName());
        System.out.println("Min Cost: " + minCost);
        writeBest(len);
    }

    public static void printPoints(int len) {
        for (Point p : points) System.out.println(p.getName());
    }

    public static void writeBest(int len) throws Exception {
        File fileout = new File(dir + outputFileName);
        PrintWriter out = new PrintWriter(fileout);
        out.append("/Route/\n");
        for (int i = 0; i < len; i++) out.append(points[best[i]].getName() + '\n');
        out.append("Min Cost: " + minCost + '\n');
        out.close();
    }

    public static void main(String[] args) throws Exception {
        IO io = new IO();
        points = io.io();
        c = io.genMatrix(); // cost array
        int len = points.length;
        init(len);
        attempt(len, 1);
        printBest(len);
//        printCostArray(len);
    }
}
