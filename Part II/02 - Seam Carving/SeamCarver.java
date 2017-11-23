import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver
{
    private int[][] img;
    private boolean transposed;
    
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException();
        
        int w = picture.width();
        int h = picture.height();
        
        img = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++)
                img[x][y] = picture.getRGB(x, y);
        }
    }
    
    public Picture picture() {
        int w = width();
        int h = height(); 
        Picture pic = new Picture(w, h);
        for (int x = 0; x < w; x++) { 
            for (int y = 0; y < h; y++)        
                pic.setRGB(x, y, transposed ? img[y][x] : img[x][y]);
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
        
        // todo dry energyInternal
        if (x == 0 || y == 0 || x == width() -1 || y == height() - 1)
            return 1000;
        
        double gx = transposed ? grad(img[y][x-1], img[y][x+1]) : grad(img[x-1][y], img[x+1][y]);
        double gy = transposed ? grad(img[y-1][x], img[y+1][x]) : grad(img[x][y-1], img[x][y+1]);
        return Math.sqrt(gx + gy);
    }
    
    private int grad(int xRGB, int yRGB) {
        Color x = new Color(xRGB);
        Color y = new Color(yRGB);
        int rx = x.getRed() - y.getRed();
        int gx = x.getGreen() - y.getGreen();
        int bx = x.getBlue() - y.getBlue();
        return rx * rx + gx * gx + bx * bx;
    }
    
    private class HorizontalSP
    {
        private final double[][] matrix;
        private final double[][] distTo;
        private final int[][] edgeTo;
        private final int[] path;
        
        public HorizontalSP() {
            int w = img.length;
            int h = img[0].length; 
            
            matrix = new double[w][h];  
            for (int x = 0; x < w; x++) { 
                for (int y = 0; y < h; y++)
                    matrix[x][y] = energyInternal(x, y);
            }
            
            distTo = new double[w][h];
            edgeTo = new int[w][h];
            
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (i == 0) distTo[i][j] = matrix[i][j];
                    else        distTo[i][j] = Double.POSITIVE_INFINITY;
                }
            }
            
            for (int i = 0; i < w - 1; i++) {
                for (int j = 0; j < h; j++) {
                    if (j > 0) relax(i, j, i+1, j-1);
                    relax(i, j, i+1, j);
                    if (j < h - 1) relax(i, j, i+1, j+1);
                }
            }
            
            int idx = 0;
            double min = distTo[w-1][idx];
            for (int i = 1; i <  h; i++) {
                if (min > distTo[w-1][i]) {
                    min = distTo[w-1][i];
                    idx = i;
                }
            }
            
            int cnt = w - 1;
            path = new int[w];
            while (cnt > -1) {
                path[cnt] = idx;
                idx = edgeTo[cnt--][idx];
            }
        }
        
        private void relax(int vX, int vY, int wX, int wY) {
            if (distTo[wX][wY] > distTo[vX][vY] + matrix[wX][wY]) {
                distTo[wX][wY] = distTo[vX][vY] + matrix[wX][wY];
                edgeTo[wX][wY] = vY;
            }
        }
        
        public int[] seam() {
            return path.clone();
        }
    }    
    
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        if (transposed)
            traspose();        
        return new HorizontalSP().seam();
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        if (!transposed)
            traspose();
        return new HorizontalSP().seam();
    }
    
    private double energyInternal(int x, int y) {
        if (x == 0 || y == 0 || x == img.length -1 || y == img[0].length - 1)
            return 1000;
        
        double gx = grad(img[x-1][y], img[x+1][y]);
        double gy = grad(img[x][y-1], img[x][y+1]);
        return Math.sqrt(gx + gy);
    }
    
    private void traspose() {
        int m = img.length;
        int n = img[0].length;        
        int[][] copy = new int[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                copy[x][y] = img[y][x];
            }
        }
        img = copy;
        transposed = !transposed;
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException();
        
       if (transposed)
            traspose();
        
        validateSeamInternal(seam);
        removeSeamInternal(seam);
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.IllegalArgumentException();
        
        if (!transposed)
            traspose();
        
        validateSeamInternal(seam);
        removeSeamInternal(seam);
    }
    
    private void validateSeamInternal(int[] seam) {
        if (seam.length != img.length)
            throw new java.lang.IllegalArgumentException();
        
        int tmp = -1;
        for (int i = 0; i < seam.length; i++) {
            int x = seam[i];
            if (x < 0 || x > img[0].length - 1)
                throw new java.lang.IllegalArgumentException();            
            if (i > 0 && Math.abs(x - tmp) > 1)
                throw new java.lang.IllegalArgumentException();            
            tmp = x;
        }
    }
    
    private void removeSeamInternal(int[] seam) {
        for (int i = 0; i < img.length; i++) {
            img[i] = removeElement(img[i], seam[i]);
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
}