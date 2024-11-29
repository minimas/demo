package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class C_Bslime extends Opponents {
    GamePanel gp;
    public C_Bslime(GamePanel gp) {
        super("Big slime");
        this.gp = gp;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/big slime2.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}