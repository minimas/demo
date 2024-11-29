package Main;
import entity.Entity;
public class CheckCollision {
    GamePanel gp;
    public CheckCollision(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {

        int entityleftWorldX = entity.worldx + entity.SolidArea.x;
        int entityrightWorldX = entity.worldx + entity.SolidArea.x + entity.SolidArea.width;
        int entitytopWorldY = entity.worldy + entity.SolidArea.y;
        int entitybottomWorldY = entity.worldy + entity.SolidArea.y + entity.SolidArea.height;

        int EntityleftCol = entityleftWorldX / gp.tileSize;
        int EntityrightCol = entityrightWorldX / gp.tileSize;
        int EntitytopRow = entitytopWorldY / gp.tileSize;
        int EntitybottomRow = entitybottomWorldY / gp.tileSize;

        int TileNum1, TileNum2;
        switch (entity.direction) {
            case "up":
                EntitytopRow = (entitytopWorldY - entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[EntityleftCol][EntitytopRow];
                TileNum2 = gp.tileM.mapTileNum[EntityrightCol][EntitytopRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                EntitybottomRow = (entitybottomWorldY + entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[EntityleftCol][EntitybottomRow];
                TileNum2 = gp.tileM.mapTileNum[EntityrightCol][EntitybottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                EntityleftCol = (entityleftWorldX - entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[EntityleftCol][EntitytopRow];
                TileNum2 = gp.tileM.mapTileNum[EntityleftCol][EntitybottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                EntityrightCol = (entityrightWorldX + entity.speed) / gp.tileSize;
                TileNum1 = gp.tileM.mapTileNum[EntityrightCol][EntitytopRow];
                TileNum2 = gp.tileM.mapTileNum[EntityrightCol][EntitybottomRow];
                if (gp.tileM.tile[TileNum1].collision || gp.tileM.tile[TileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkItemCol(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.SolidArea.x = entity.worldx + entity.SolidArea.x;
                entity.SolidArea.y = entity.worldy + entity.SolidArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldx + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldy + gp.obj[i].solidArea.y;
                switch (entity.direction) {
                    case "up":
                        entity.SolidArea.y -= entity.speed;
                        if (entity.SolidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.SolidArea.y += entity.speed;
                        if (entity.SolidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.SolidArea.x -= entity.speed;
                        if (entity.SolidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.SolidArea.x += entity.speed;
                        if (entity.SolidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.SolidArea.x = entity.solidAreaDefaultX;
                entity.SolidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultx;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaulty;
            }
        }
        return index;
    }
}