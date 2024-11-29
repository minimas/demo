package entity;
import Main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Entity {
    GamePanel gp;
    public int worldx, worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter =0;
    public int spriteNum =1;
    public Rectangle SolidArea = new Rectangle(0,0,48,48);
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public Entity(GamePanel gp) {
        this.gp =gp;
    }
}