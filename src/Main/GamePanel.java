package Main;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;
import java.awt.*;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; //16x16 tile.
    final int Scale = 4;
    public final int tileSize = originalTileSize * Scale;//48x48 tile
    public final int maxScreenCol = 12;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels
    //world settings
    public final int maxWorldCol = 51;
    public final int maxWorldRow = 51;
    //FPS
    int FPS = 60;
    //class calls
    TileManager tileM =new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public CheckCollision cChecker = new CheckCollision(this);
    public ItemSetter iSet = new ItemSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[25];

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        iSet.setItems();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval;
        drawInterval = (double) 1000000000 / FPS;
        double Delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            Delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(Delta >= 1){
                update();
                repaint();
                Delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Tile image
        tileM.draw(g2);
        //Objects / item
        for(int i = 0 ; i < obj.length ; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }
        //Player image
        player.draw(g2);
        ui.Draw(g2);
        g2.dispose();
    }
}