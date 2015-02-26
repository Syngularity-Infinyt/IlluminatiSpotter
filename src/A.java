package a;
/*
 @author Syngularity
 Illumoati spotter 2000
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class A {
    public static void main(final String[] args){
        final long sum = spot_illuminati(args[0]);
        write_into_file(args[1], sum);
    }    
        
    protected static int[][] px_get(final String fname){
        try {
            final BufferedImage image = ImageIO.read( new File( fname ) );
            final int[][] pixels = new int[image.getWidth()][image.getHeight()];
            for (int i = 0; i < image.getWidth(); ++i){
                for (int j = 0; j < image.getHeight(); ++j){pixels[i][j] = image.getRGB( i, j );}}
            return pixels;
        }catch(final Exception e){throw new RuntimeException( e );}
    }
	
    protected static int[][] rotator(final int[][] px){
        assert (px.length > 0);
        final int w = px[0].length;
        final int h = px.length;
        final int[][] ret = new int[w][h];
        for (int i = 0; i < w; ++i){
            for (int j = 0; j < h; ++j) {ret[i][j] = px[h - 1 - j][i];}
	}
        return ret;
    }

    public static long spot_illuminati(final String fname){
        int[][] px = px_get(fname);
        long sum_of_illuminati = 0;
        long sum_;
        for (int i = 0; i < 4; ++i){
            if (i > 0){px = rotator(px);}
            sum_ = 0;
            for(int j = 0; j < px[0].length; ++j){
                for (int z = 0; z < px.length; ++z){
 /* Is this the */if (px[z][j] != -1){
 /* triangle we */     sum_ += check_next( px, z, j );
 /*are looking for?*/  sum_ += komcsi( px, z, j );
                    }
                }
            }
            sum_of_illuminati += sum_;
	}
        return sum_of_illuminati;
    }
	
    private static int check_next(final int[][] px, final int i, final int j){
        int ret = 0;
        for (int k = 1; i + k < px.length && j + k < px[0].length; ++k){
            if ( px[i + k][j] == -1 || px[i + k][j + k] == -1 ){break;}
                boolean connected = true;
                for (int u = j; u < j + k; ++u){
                    if (px[i + k][u] == -1){connected = false;break;}
                }
                if (connected ){ret++;}
            }
        return ret;
    }
	
    private static int komcsi(final int[][] px, final int i, final int j){
        int return_val = 0;
        for(int k = 1; i - k >= 0 && j + 2 * k < px[0].length; ++k){
            if (px[i - k][j + k] == -1 || px[i][j + k] == -1){break;}
            boolean connected = true;
		for (int u = 1; u <= k; ++u) {
                    if (  px[i][j + k + u] == -1 ){connected = false;break;}
		}
                if (connected){
                    for (int u = 1; u <= k; ++u){
			if ( px[i - k + u][j + k + u] == -1){connected = false;break;}
                    }
		}
            if(connected ){return_val++;}
        }
	return return_val;
    }
        
    public static void write_into_file(final String out_file, final long num){
        try{
            File file = new File(out_file);
            if (!file.exists()){file.createNewFile();}
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Long.toString(num));bw.close();
	}catch (IOException e){e.printStackTrace();}
    }
}