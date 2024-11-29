package Main;
import objects.*;
public class ItemSetter {
    GamePanel gp;
    public ItemSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setItems() {
        gp.obj[0] = new Chest_obj(gp);
        gp.obj[0].worldx = 7 * gp.tileSize;
        gp.obj[0].worldy = 6 * gp.tileSize;

        gp.obj[1] = new Boots_obj(gp);
        gp.obj[1].worldx = 49 * gp.tileSize;
        gp.obj[1].worldy = 31 * gp.tileSize;

        gp.obj[2] =new Coin_obj(gp);
        gp.obj[2].worldx = 22 * gp.tileSize;
        gp.obj[2].worldy = 30 * gp.tileSize;

        gp.obj[3] = new Coin_obj(gp);
        gp.obj[3].worldx = 48 * gp.tileSize;
        gp.obj[3].worldy = 2 * gp.tileSize;

        gp.obj[4] = new Coin_obj(gp);
        gp.obj[4].worldx = 47 * gp.tileSize;
        gp.obj[4].worldy = 35 * gp.tileSize;

        gp.obj[5] = new Coin_obj(gp);
        gp.obj[5].worldx = 49 * gp.tileSize;
        gp.obj[5].worldy = 49 * gp.tileSize;

        gp.obj[6] = new MKey_obj(gp);
        gp.obj[6].worldx = 28 * gp.tileSize;
        gp.obj[6].worldy = 3 * gp.tileSize;

        gp.obj[7] = new Mgate(gp);
        gp.obj[7].worldx = 7 * gp.tileSize;
        gp.obj[7].worldy = 9 * gp.tileSize;

        gp.obj[8] = new Door_obj(gp);
        gp.obj[8].worldx = 39 * gp.tileSize;
        gp.obj[8].worldy = 44 * gp.tileSize;

        gp.obj[9] = new Door_obj(gp);
        gp.obj[9].worldx = 48 * gp.tileSize;
        gp.obj[9].worldy = 4 * gp.tileSize;

        gp.obj[10] = new Door_obj(gp);
        gp.obj[10].worldx = 21 * gp.tileSize;
        gp.obj[10].worldy = 45 * gp.tileSize;

        gp.obj[11] = new C_uglyFace(gp);
        gp.obj[11].worldx = 17 * gp.tileSize;
        gp.obj[11].worldy = 4 * gp.tileSize;

        gp.obj[12] = new C_slime(gp);
        gp.obj[12].worldx = 20 * gp.tileSize;
        gp.obj[12].worldy = 2 * gp.tileSize;

        gp.obj[13] = new C_Bslime(gp);
        gp.obj[13].worldx = 23 * gp.tileSize;
        gp.obj[13].worldy = 8 * gp.tileSize;

        gp.obj[14] = new C_slime(gp);
        gp.obj[14].worldx = 19 * gp.tileSize;
        gp.obj[14].worldy = 9 * gp.tileSize;

        gp.obj[15] = new C_Bslime(gp);
        gp.obj[15].worldx = 17 * gp.tileSize;
        gp.obj[15].worldy = 7 * gp.tileSize;

        gp.obj[16] = new Map(gp);
        gp.obj[16].worldx = 29 * gp.tileSize;
        gp.obj[16].worldy = 26 * gp.tileSize;

        gp.obj[17] = new Map2(gp);
        gp.obj[17].worldx = 31 * gp.tileSize;
        gp.obj[17].worldy = 11 * gp.tileSize;

        gp.obj[18] = new Gate(gp);
        gp.obj[18].worldx = 30 * gp.tileSize;
        gp.obj[18].worldy = 13 * gp.tileSize;

        gp.obj[19] = new Gate(gp);
        gp.obj[19].worldx = 30 * gp.tileSize;
        gp.obj[19].worldy = 12 * gp.tileSize;
//
//        gp.obj[20] = new Gate(gp);
//        gp.obj[20].worldx = 29 * gp.tileSize;
//        gp.obj[20].worldy = 13 * gp.tileSize;

        gp.obj[22] = new Card(gp);
        gp.obj[22].worldx = 39 * gp.tileSize;
        gp.obj[22].worldy = 3 * gp.tileSize;

        gp.obj[23] = new Card(gp);
        gp.obj[23].worldx = 23 * gp.tileSize;
        gp.obj[23].worldy = 48 * gp.tileSize;

        gp.obj[24] = new Card(gp);
        gp.obj[24].worldx = 39 * gp.tileSize;
        gp.obj[24].worldy = 45 * gp.tileSize;
    }
}