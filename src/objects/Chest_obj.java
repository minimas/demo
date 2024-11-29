package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class Chest_obj extends SuperObject{
    GamePanel gp;
    public Chest_obj(GamePanel gp) {
        this.gp = gp;
        name = "chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}