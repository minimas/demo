package entity;
import Main.Fight;
import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import objects.Opponents;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
public class Player extends Entity {
    KeyHandler keyH;
    public final int ScreenX;
    public final int ScreenY;
    public int haskeys;
    public int hasMkey;
    public int hascard;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
       ScreenX=gp.screenWidth/2 -(gp.tileSize/2);
       ScreenY=gp.screenHeight/2-(gp.tileSize/2);
       SolidArea = new Rectangle();
       SolidArea.x = 16;
       SolidArea.y = 28;
       solidAreaDefaultX = SolidArea.x;
       solidAreaDefaultY = SolidArea.y;
       SolidArea.width = 32;
       SolidArea.height = 32;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldx = gp.tileSize*30;
        worldy = gp.tileSize*27;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        up1 = setup("up1");
        up2 = setup("up2");
        down1 = setup("down1");
        down2 = setup("down2");
        left1 = setup("left1");
        left2 = setup("left2");
        right1 = setup("right1");
        right2 = setup("right2");
    }
    public BufferedImage setup(String imageName){
        UtilityTool ut = new UtilityTool();
        BufferedImage imgscale = null;
        try {
            imgscale = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            imgscale = ut.scaledImage(imgscale, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return imgscale;
    }
    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }
            // Check all collisions
            collisionOn = false;
            gp.cChecker.checkTile(this);
            // Check obj col
            int objIndex = gp.cChecker.checkItemCol(this,true);
            pickUpObj(objIndex);
            // if collisoin non
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldy -= speed;  break;
                    case "down":
                        worldy += speed;  break;
                    case "left":
                        worldx -= speed;  break;
                    case "right":
                        worldx += speed;  break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObj(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "coin":
                    haskeys++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("you picked up a Coin!");
                    break;
                case "Master key":
                    hasMkey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("you picked up The Master Key!!");
                    break;
                case "card":
                    hascard++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("you picked up a Card!");
                    break;
                case "door":
                    if (haskeys > 0) {
                        gp.obj[i] = null;
                        haskeys--;
                        gp.ui.showMessage("you open the door!");
                    }
                    else{
                        gp.ui.showMessage("a Coin is needed!");
                    }
                    break;
                case "mgate":
                    if (hasMkey > 0) {
                        gp.obj[i] = null;
                        hasMkey--;
                        gp.ui.showMessage("you open the door!");
                    }
                    else{
                        gp.ui.showMessage("You need the Master key!");
                    }
                    break;
                case "gate":
                    if (hascard == 3) {
                        gp.obj[i] = null;
                        gp.ui.showMessage("you open the path!");
                    }
                    else{
                        gp.ui.showMessage("You need to collect all 3 Cards!");
                    }
                    break;
                case "boot":
                    speed++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("speeeed up!");
                    break;
                case "chest":
                    gp.ui.Gamefinnished = true;
                    break;
                case "map":
                    gp.ui.mapDraw(1);
                    break;
                case "map2":
                    gp.ui.mapDraw(2);
                    break;
                case "slime":
                case "uglyface":
                case "Big slime":
                    if (gp.obj[i] instanceof Opponents) {
                        Opponents opponent = (Opponents) gp.obj[i]; // Safe casting
                        if (opponent.defeated) {
                            return;
                        }
                        if (opponent.inBattle) {
                            gp.ui.showMessage("You are fighting this opponent!");
                            return;
                        }
                        // Mark opponent as in battle
                        opponent.inBattle = true;

                        // Trigger fight
                        SwingUtilities.invokeLater(() -> {
                            Fight game = new Fight();
                            game.setFightListener(victory -> {
                                if (victory) {
                                    opponent.defeated = true; // Mark opponent as defeated
                                    gp.ui.showMessage("You won against " + objectName);
                                    gp.obj[i] = null; // Remove if needed
                                } else {
                                    gp.ui.showMessage("You lost against " + objectName + " try again.");
                                }
                                opponent.inBattle = false; // Unlock battle status
                            });
                            game.setVisible(true);
                        });
                    }
                    break;
            }
        }
    }
    public void draw(Graphics g2){
        BufferedImage image = null;
        //player animation
        switch(direction){
            case "up":
                if(spriteNum == 1){
                image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, ScreenX, ScreenY,null);
    }
}