package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class C_uglyFace extends Opponents{
    GamePanel gp;
    public C_uglyFace(GamePanel gp) {
        super("uglyface");
        this.gp = gp;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ugly face.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}