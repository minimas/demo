package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class MKey_obj extends SuperObject{
    GamePanel gp;
    public MKey_obj(GamePanel gp){
        this.gp = gp;
        name = "Master key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/Master_Key.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}