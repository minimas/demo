package objects;
import Main.GamePanel;
import Main.UtilityTool;
import java.awt.*;
import java.awt.image.BufferedImage;
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldx, worldy;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultx = 0;
    public int solidAreaDefaulty = 0;
    UtilityTool utl = new UtilityTool();
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldx - gp.player.worldx + gp.player.ScreenX;
        int screenY = worldy - gp.player.worldy + gp.player.ScreenY;
        if(worldx + gp.tileSize> gp.player.worldx - gp.player.ScreenX &&
                worldx - gp.tileSize< gp.player.worldx + gp.player.ScreenX &&
                worldy + gp.tileSize> gp.player.worldy - gp.player.ScreenY &&
                worldy - gp.tileSize< gp.player.worldy + gp.player.ScreenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}