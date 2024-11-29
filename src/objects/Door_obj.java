package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class Door_obj extends SuperObject{
    GamePanel gp;
    public Door_obj(GamePanel gp) {
        this.gp = gp;
        name = "door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}