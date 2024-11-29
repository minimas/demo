package tile;
import Main.GamePanel;
import Main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }
    public void getTileImage() {
            setup(0,"grass",false);
            setup(1,"tree1",true);
            setup(2,"earth",false);
            setup(3,"water",true);
            setup(4,"sand",false);
            setup(5,"wall",true);
            setup(6,"Wood",true);
            setup(7,"sign",true);
    }
    public void setup(int Index,String imageName,boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try{
            tile[Index]=new Tile();
            tile[Index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[Index].image = utilityTool.scaledImage(tile[Index].image, gp.tileSize, gp.tileSize);
            tile[Index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] number = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int WorldX= worldCol * gp.tileSize;
            int WorldY= worldRow * gp.tileSize;
            int screenX = WorldX - gp.player.worldx + gp.player.ScreenX;
            int screenY = WorldY - gp.player.worldy + gp.player.ScreenY;

            if(WorldX + gp.tileSize> gp.player.worldx - gp.player.ScreenX &&
               WorldX - gp.tileSize< gp.player.worldx + gp.player.ScreenX &&
               WorldY + gp.tileSize> gp.player.worldy - gp.player.ScreenY &&
               WorldY - gp.tileSize< gp.player.worldy + gp.player.ScreenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}