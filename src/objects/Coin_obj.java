package objects;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;
public class Coin_obj extends SuperObject{
    GamePanel gp;
    public Coin_obj(GamePanel gp){
        this.gp = gp;
        name = "coin";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coin.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}