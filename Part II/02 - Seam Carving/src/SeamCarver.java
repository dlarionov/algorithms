import edu.princeton.cs.algs4.Picture;
// import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;

public class SeamCarver
{
    private int[][] img;
    private double[][] mx;
    private boolean transposed;
    
    private double[][] distTo;
    private int[][] edgeTo;    
    
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException();
        
        int width = picture.width();
        int height = picture.height();
        
        img = new int[width][height];
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {
                img[col][row] = picture.getRGB(col, row);
            }
        }
        
        mx = new double[width][height];  
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {
                mx[col][row] = energyInternal(col, row);
            }
        }
    }
    
    public Picture picture() {
        int width = width();
        int height = height(); 
        Picture pic = new Picture(width, height);
        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {          
                pic.setRGB(col, row, transposed ? img[row][col] : img[col][row]);
            }
        }
        return pic;
    }
    
    public int width() {
        return transposed ? img[0].length : img.length;
    }
    
    public int height() {
        return transposed ? img.length : img[0].length;
    }
    
    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1)
            throw new java.lang.IllegalArgumentException(); 
        
        if (x == 0 || y == 0 || x == width() -1 || y == height() - 1)
            return 1000;
        
        double gx = transposed ? grad(img[y][x-1], img[y][x+1]) : grad(img[x-1][y], img[x+1][y]);
        double gy = transposed ? grad(img[y-1][x], img[y+1][x]) : grad(img[x][y-1], img[x][y+1]);
        return Math.sqrt(gx + gy);
    }
    
    private double energyInternal(int x, int y) {
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
        if (transposed)
            traspose();
        return findSeam();
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        if (!transposed)
            traspose();        
        return findSeam();
    }
    
    private void traspose() {
        trasposeImg();
        trasposeMx();                
        transposed = !transposed;
    }
    
    private void trasposeImg() {
        int m = img.length;
        int n = img[0].length; 
        int[][] copy = new int[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++)
                copy[x][y] = img[y][x];
        }
        img = copy;
    }
    
    private void trasposeMx() {
        int m = mx.length;
        int n = mx[0].length;        
        double[][] copy = new double[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++)
                copy[x][y] = mx[y][x];
        }
        mx = copy;
    }
    
    private int[] findSeam() {
        int width = mx.length;
        int height = mx[0].length;
        
        distTo = new double[width][height];
        edgeTo = new int[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0) distTo[i][j] = mx[i][j];
                else        distTo[i][j] = Double.POSITIVE_INFINITY;
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
        
        int idx = 0;
        double min = distTo[width-1][idx];
        for (int i = 1; i <  height; i++) {
            if (min > distTo[width-1][i]) {
                min = distTo[width-1][i];
                idx = i;
            }
        }
        
        int cnt = width - 1;
        int[] seam = new int[width];
        while (cnt > -1) {
            seam[cnt] = idx;
            idx = edgeTo[cnt--][idx];
        }
        
        // ugly code
        distTo = null;
        edgeTo = null;
        
        return seam;   
    }
    
    private void relax(int vX, int vY, int wX, int wY)
    {
        if (distTo[wX][wY] > distTo[vX][vY] + mx[wX][wY])
        {
            distTo[wX][wY] = distTo[vX][vY] + mx[wX][wY];
            edgeTo[wX][wY] = vY;
        }
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException();
        
       if (transposed)
            traspose();
        
        validateSeam(seam);
        removeSeam(seam);
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException();
        
        if (!transposed)
            traspose();
        
        validateSeam(seam);
        removeSeam(seam);
    }
    
    private void validateSeam(int[] seam) {
        if (seam.length != img.length)
            throw new java.lang.IllegalArgumentException();
        
        int temp = -1;
        for (int i = 0; i < seam.length; i++) {
            int x = seam[i];
            if (x < 0 || x > img[0].length - 1)
                throw new java.lang.IllegalArgumentException();
            
            if (i > 0 && Math.abs(x - temp) > 1)
                throw new java.lang.IllegalArgumentException();
            
            temp = x;
        }   
    }
    
    private void removeSeam(int[] seam) {
        for (int i = 0; i < img.length; i++) {
            img[i] = removeElement(img[i], seam[i]);
        }
        
        for (int i = 0; i < mx.length; i++) {
            mx[i] = removeElement(mx[i], seam[i]);
            if (seam[i] < mx[i].length)
                mx[i][seam[i]] = energyInternal(i, seam[i]);
            if (seam[i] > 0)
                mx[i][seam[i]-1] = energyInternal(i, seam[i]-1);
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
}