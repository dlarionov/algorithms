import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver
{
    private final Picture picture;
    
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.IllegalArgumentException();
        
        this.picture = new Picture(picture);
    }
    
    public Picture picture() {
        return picture;
    }
    
    public int width(){
        return picture.width();
    }
    
    public int height() {
        return picture.height();
    }
    
    //energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > picture.width() - 1 || y > picture.height() -1 )
            throw new java.lang.IllegalArgumentException(); 
        
        if (x == 0 || y == 0 || x == picture.width() -1 || y == picture.height() -1)
            return 1000;
        
        // (x, y) refers to the pixel in column x and row y, with pixel (0, 0) at the upper left corner
        
        double gx = grad(picture.get(x-1, y), picture.get(x+1, y));
        double gy = grad(picture.get(x, y-1), picture.get(x, y+1));
        return Math.sqrt(gx + gy);
    }
    
    private int grad(Color x, Color y) {        
        int rx = x.getRed() - y.getRed();
        int gx = x.getGreen() - y.getGreen();
        int bx = x.getBlue() - y.getBlue();
        return rx * rx + gx * gx + bx * bx;
    }
    
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || picture.height() < 1 || seam.length != picture.width())
            throw new java.lang.IllegalArgumentException();
        
        // todo the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1)
    }
    
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || picture.width() < 1 || seam.length != picture.height())
            throw new java.lang.IllegalArgumentException();
        
        // todo  the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1)
    }
}