import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver
{
    private final Picture pic;
    private double[][] mx;
    
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException();
        
        pic = new Picture(picture);
        compute();
    }
    
    public Picture picture() {
        return pic;
    }
    
    public int width(){
        return pic.width();
    }
    
    public int height() {
        return pic.height();
    }
    
    //energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > pic.width() - 1 || y > pic.height() -1 )
            throw new java.lang.IllegalArgumentException(); 
        
        if (x == 0 || y == 0 || x == pic.width() -1 || y == pic.height() -1)
            return 1000;
        
        // (x, y) refers to the pixel in column x and row y, with pixel (0, 0) at the upper left corner
        
        double gx = grad(pic.get(x-1, y), pic.get(x+1, y));
        double gy = grad(pic.get(x, y-1), pic.get(x, y+1));
        return Math.sqrt(gx + gy);
    }
    
    private int grad(Color x, Color y) {        
        int rx = x.getRed() - y.getRed();
        int gx = x.getGreen() - y.getGreen();
        int bx = x.getBlue() - y.getBlue();
        return rx * rx + gx * gx + bx * bx;
    }    
    
    private void compute() {        
        int width = pic.width();
        int height = pic.height();         
        mx = new double[width][height];      
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++)            
                mx[col][row] = energy(col, row);
        }
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return seam();
    }
    
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        // TODO
        return seam();
    }   
    
    private void traspose() {
        int m = mx.length;
        int n = mx[0].length;        
        double[][] trasposed = new double[n][m];        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++)
                trasposed[x][y] = mx[y][x];
        }
        mx = trasposed;
    }
    
    private int[] seam() {
        double min = 1000;
        
        // find smallest energy in the first row
        int col = -1;
        for (int i = 1; i <  mx.length - 1; i++) {
            if (min > mx[i][1]) {
                min = mx[i][1];
                col = i;
            }
        }
        
        if (col < 0)
            return null;
        
        int row = 0;
        int[] seam = new int[mx[0].length];
        seam[row++] = col; // 0 row
        seam[row++] = col; // 1 row
        while (row < mx[0].length - 1) {
            col = next(col, row);
            seam[row++] = col;
        }
        seam[row] = col; // last row
        return seam;
    }
    
    private int next(int col, int row) {
        double a = mx[col-1][row];
        double b = mx[col][row];
        double c = mx[col+1][row];
        
        if (a <= b && a <= c)
            return col-1;
        else if (b <= a && b <= c)
            return col;
        else // if (c <= a && c <= b)
            return col+1;        
    }    
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || pic.height() < 2 || seam.length != pic.width())
            throw new java.lang.IllegalArgumentException();
        
        // todo the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1)
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || pic.width() < 2 || seam.length != pic.height())
            throw new java.lang.IllegalArgumentException();
        
        // todo  the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1)
    }
}