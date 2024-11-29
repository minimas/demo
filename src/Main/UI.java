package Main;
import objects.Coin_obj;
import objects.MKey_obj;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
public class UI {
    GamePanel gp;
    Font arial_30,arial_40 ,arial_80B;
    BufferedImage key_image;
    BufferedImage MKey_image;
    public boolean messageOn = false;
    public boolean onMap = false;
    public boolean onMap2 = false;
    public String message = "";
    int messageCounter = 0;
    int mapC = 0;
    public boolean Gamefinnished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Coin_obj key = new Coin_obj(gp);
        MKey_obj Mkey = new MKey_obj(gp);
        key_image = key.image;
        MKey_image = Mkey.image;
    }
    public void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }

    public void mapDraw(int i) {
        if (i == 1) {
            onMap = true;
        }
        if (i == 2) {
            onMap2 = true;
        }
    }
    public void Draw(Graphics2D g2) {
        if (Gamefinnished) {
            String text ;
            int textLength ;
            int x;
            int y;

            g2.setFont(arial_30);
            g2.setColor(Color.yellow);
            text = "You found the ultimate Card!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize*2);
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.orange);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize*2);
            g2.drawString(text,x,y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(key_image, gp.tileSize / 2, 0, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.haskeys, 95, 40);
            g2.drawImage(MKey_image,( gp.tileSize* 5)/2, 0, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasMkey, 225, 40);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, gp.player.ScreenX - 60, gp.player.ScreenY + 65);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
             if (onMap) {
                 UtilityTool ut = new UtilityTool();
                 BufferedImage imagscale = null;
                 int ScreenX,ScreenY;
                 ScreenX=gp.screenWidth/2 -(gp.tileSize*4);
                 ScreenY=gp.screenHeight/2-(gp.tileSize*4);
                 try {
                     imagscale = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/map.png")));
                     imagscale = ut.scaledImage(imagscale, 500, 500);
                 }catch (IOException e){
                     e.printStackTrace();
                 }
                 g2.drawImage(imagscale, ScreenX, ScreenY,null);
                 mapC++;
                 if (mapC > 120) {
                     mapC = 0;
                     onMap = false;
                 }
             }
             if (onMap2) {
                UtilityTool ut = new UtilityTool();
                BufferedImage imagscale = null;
                int ScreenX,ScreenY;
                ScreenX=gp.screenWidth/2 -(gp.tileSize*4);
                ScreenY=gp.screenHeight/2-(gp.tileSize*4);
                try {
                    imagscale = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/uimap.png")));
                    imagscale = ut.scaledImage(imagscale, 500, 500);
                }catch (IOException e){
                    e.printStackTrace();
                }
                g2.drawImage(imagscale, ScreenX, ScreenY,null);
                mapC++;
                if (mapC > 120) {
                    mapC = 0;
                    onMap2 = false;
                }
            }
        }
    }
}