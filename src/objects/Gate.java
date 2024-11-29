package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class Gate extends SuperObject{
    GamePanel gp;
    public Gate(GamePanel gp) {
        this.gp = gp;
        name = "gate";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/gate.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}