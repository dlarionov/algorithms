import edu.princeton.cs.algs4.Picture;
// import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver
{
    private int[][] img;
    private double[][] mx;
    
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException();
        
        int width = picture.width();
        int height = picture.height();
        
        // (x, y) refers to the pixel in column x and row y, with pixel (0, 0) at the upper left corner
        
        img = new int[width][height];         
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {          
                img[col][row] = picture.getRGB(col, row);
            }
        }
        
        mx = new double[width][height];  
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {         
                mx[col][row] = energy(col, row);
            }
        }
    }
        
    public Picture picture() {
        int width = img.length;
        int height = img[0].length;        
        Picture pic = new Picture(width, height);
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {          
                pic.setRGB(col, row, img[col][row]);
            }
        }
        return pic;
    }
    
    public int width() {
        return img.length;
    }
    
    public int height() {
        return img[0].length;
    }
    
    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > img.length - 1 || y > img[0].length - 1)
            throw new java.lang.IllegalArgumentException(); 
        
        if (x == 0 || y == 0 || x == img.length -1 || y == img[0].length - 1)
            return 1000;
        
        double gx = grad(img[x-1][y], img[x+1][y]);
        double gy = grad(img[x][y-1], img[x][y+1]);
        return Math.sqrt(gx + gy);
    }
    
    private int grad(int xRGB, int yRGB) {
        // todo use binary operations directly
        Color x = new Color(xRGB);
        Color y = new Color(yRGB);
        int rx = x.getRed() - y.getRed();
        int gx = x.getGreen() - y.getGreen();
        int bx = x.getBlue() - y.getBlue();
        return rx * rx + gx * gx + bx * bx;
    }
    
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double min = 1000;
        int width = mx.length;
        int height = mx[0].length;
        
        int row = -1;
        for (int i = 1; i <  height - 1; i++) {
            if (min > mx[1][i]) {
                min = mx[1][i];
                row = i;
            }
        }
        
        if (row < 0)
            return new int[0];
        
        int col = 0;
        int[] seam = new int[width];
        seam[col++] = row; // 0 col
        seam[col++] = row; // 1 col
        while (col < width - 1) {
            row = nextRow(col, row);
            seam[col++] = row;
        }
        seam[col] = row; // last col
        return seam;
    }   
    
    private int nextRow(int col, int row) {
        double a = mx[col][row-1];
        double b = mx[col][row];
        double c = mx[col][row+1];
        
        if (a <= b && a <= c)
            return row-1;
        else if (b <= a && b <= c)
            return row;
        else // if (c <= a && c <= b)
            return row+1;        
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double min = 1000;
        int width = mx.length;
        int height = mx[0].length;
        
        int col = -1;
        for (int i = 1; i <  width - 1; i++) {
            if (min > mx[i][1]) {
                min = mx[i][1];
                col = i;
            }
        }
        
        if (col < 0)
            return new int[0];
        
        int row = 0;
        int[] seam = new int[height];
        seam[row++] = col; // 0 row
        seam[row++] = col; // 1 row
        while (row < height - 1) {
            col = nextCol(col, row);
            seam[row++] = col;
        }
        seam[row] = col; // last row
        return seam;
    }
    
    private int nextCol(int col, int row) {
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
        if (seam == null || img[0].length < 3 || seam.length != img.length)
            throw new java.lang.IllegalArgumentException();
        
        // todo  the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1)
        
        for (int i = 0; i < img.length; i++) {
            img[i] = removeElement(img[i], seam[i]);
        }
        
        for (int i = 0; i < mx.length; i++) {
            mx[i] = removeElement(mx[i], seam[i]);
            if (seam[i] < mx[i].length)
                mx[i][seam[i]] = energy(i, seam[i]);
            if (seam[i] > 0)
                mx[i][seam[i]-1] = energy(i, seam[i]-1);
        }
    }
    // stackoverflow.com/a/644764
    private int[] removeElement(int[] source, int index) {
        int[] result = new int[source.length - 1];
        System.arraycopy(source, 0, result, 0, index);
        if (source.length != index) {
            System.arraycopy(source, index + 1, result, index, source.length - index - 1);
        }        
        return result;
    }
    
    private double[] removeElement(double[] source, int index) {
        double[] result = new double[source.length - 1];
        System.arraycopy(source, 0, result, 0, index);
        if (source.length != index) {
            System.arraycopy(source, index + 1, result, index, source.length - index - 1);
        }        
        return result;
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || img.length < 3 || seam.length != img[0].length)
            throw new java.lang.IllegalArgumentException();
        
        trasposeImg();
        trasposeMx();
        removeHorizontalSeam(seam);
        trasposeImg();
        trasposeMx();        
    }
    
    private void trasposeImg() {
        int m = img.length;
        int n = img[0].length;        
        int[][] trasposed = new int[n][m];        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++)
                trasposed[x][y] = img[y][x];
        }
        img = trasposed;
    }
    
    private void trasposeMx() {
        int m = mx.length;
        int n = mx[0].length;        
        double[][] trasposed = new double[n][m];        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++)
                trasposed[x][y] = mx[y][x];
        }
        mx = trasposed;
    }
}