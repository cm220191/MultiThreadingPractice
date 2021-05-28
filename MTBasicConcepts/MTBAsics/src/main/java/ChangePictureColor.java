import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangePictureColor {

    public static final String SOURCE_IMAGE= "./resources/Home.jpg";
    public static final String RESULT_IMAGE="./resources/Result.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage sourceImg = ImageIO.read(new File(SOURCE_IMAGE));
        BufferedImage result = new BufferedImage(sourceImg.getHeight(), sourceImg.getWidth(), BufferedImage.TYPE_INT_RGB);

        recolorSingleThread(sourceImg, result);

        File outPutFile = new File(RESULT_IMAGE);
        ImageIO.write(result,"jpeg", outPutFile);
    }

    public static void recolorSingleThread(BufferedImage input, BufferedImage output){
        reColorImage(input, output, input.getHeight(), input.getWidth(), 0 , 0);
    }

    public static int getBlue(int rgb){
        return rgb & 0x00000FF;
    }

    public static int getGreen(int rgb){
        return rgb & 0x00000FF >> 8;
    }

    public static int getRed(int rgb){
        return rgb & 0x00000FF >> 16;
    }

    public static int getRGBFromColors(int red, int green, int blue){
        int rgb=0;
        rgb = rgb | blue;
        rgb = rgb | green << 8;
        rgb = rgb | red << 16;

        rgb |= 0xFF00000;
        return rgb;
    }

    public static boolean isShadeOfWhite(int red, int green, int blue){
        return Math.abs(red - green )<30 && Math.abs(red-blue) <30 && Math.abs(green -blue)<30;
    }

    public static void recolorPixels(BufferedImage input, BufferedImage output, int x, int y){
        int rgb= input.getRGB(x, y);
        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed;
        int newGreen;
        int newBlue;

        if(isShadeOfWhite(red, green, blue)){
            newRed = Math.max(255, red +50);
            newGreen = Math.min(0, green -80);
            newBlue = Math.min(0, blue -30);
        }else{
            newRed = red;
            newGreen =green;
            newBlue = blue;
        }

        int newRgb= getRGBFromColors(newRed, newGreen, newBlue);

    }

    public static void setRGBValue(BufferedImage image, int x, int y, int rgb){
        image.getRaster().setDataElements(x, y,image.getColorModel().getDataElements(rgb, null));
    }

    public static void reColorImage(BufferedImage orig, BufferedImage resultImage, int height, int width, int leftCorner, int rightCorner){
        for(int x= leftCorner; x< leftCorner+width && x<orig.getWidth(); x++){
            for(int y= rightCorner; y < rightCorner+height && y<orig.getHeight(); y++ ){
                recolorPixels(orig, resultImage,x,y);
            }
        }
    }
}
