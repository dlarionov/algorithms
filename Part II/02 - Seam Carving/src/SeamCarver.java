import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
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
    
    private double[][] distTo;
    private int[][] edgeTo;
    
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {        
        int width = mx.length;
        int height = mx[0].length;
                
        distTo = new double[width][height];
        edgeTo = new int[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0) distTo[i][j] = mx[i][j];
                else        distTo[i][j] = Double.POSITIVE_INFINITY;                
                edgeTo[i][j] = -1;
            }
        }
        
        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height; j++) {
                if (j > 0)
                    relax(i, j, i+1, j-1);
                relax(i, j, i+1, j);
                if (j < height - 1)
                    relax(i, j, i+1, j+1);
            }
        }
        
        StdOut.println("distTo:");
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++){
                StdOut.print(distTo[i][j] + "  ");
            }
            StdOut.println("");
        }
        
        StdOut.println("edgeTo:");
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++){
                StdOut.print(edgeTo[i][j] + "  ");
            }
            StdOut.println("");
        }
        
        int idx = 0;
        double[] last = distTo[width-1];
        double min = last[idx];        
        for (int i = 1; i <  height; i++) {
            if (min > last[i]) {
                min = last[i];
                idx = i;
                
                
            }
        }
        
        int cnt = width - 1;
        int[] seam = new int[width];
        while(cnt >= 0) {
            seam[cnt] = idx;
            idx = edgeTo[cnt--][idx];
        }
        
        // todo use another class
        distTo = null;
        edgeTo = null;
        
        return seam;        
    }
    
    private void relax(int vX, int vY, int wX, int wY)
    {
        //StdOut.println("(" + vX + "," + vY + ") -> (" + wX + "," + wY + ")");
        if (distTo[wX][wY] > distTo[vX][vY] + mx[wX][wY])
        {
            distTo[wX][wY] = distTo[vX][vY] + mx[wX][wY];
            edgeTo[wX][wY] = vY;
        }
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
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