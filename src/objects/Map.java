package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class Map extends SuperObject {
    GamePanel gp;
    public Map(GamePanel gp) {
        this.gp = gp;
        name = "map";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/map.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}