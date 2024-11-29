package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
public class UtilityTool {
    public BufferedImage scaledImage(BufferedImage original, int Width, int Height) {
        BufferedImage scaledImg = new BufferedImage(Width, Height, original.getType());
        Graphics2D g2 = scaledImg.createGraphics();
        g2.drawImage(original, 0, 0, Width, Height, null);
        g2.dispose();
        return scaledImg;
    }
}