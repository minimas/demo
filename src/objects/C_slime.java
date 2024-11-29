package objects;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.util.Objects;

public class C_slime extends Opponents {
    GamePanel gp;

    public C_slime(GamePanel gp) {
        super("slime"); // Pass the name to the Opponent constructor
        this.gp = gp;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/small slime.png")));
            utl.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
