package object;

import bounding.BoundingBox;
import util.Matrix3x3f;
import util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerSprite extends Sprite {

    private BufferedImage spriteSheet;
    private Background bg;

    public PlayerSprite(Background bg) {

        super("catapultspritesheet.png", new Vector2f(-0.375f, 0.375f), new Vector2f(0.375f, -0.375f));

        this.showBounds = true;
        this.gravityApplies = true;
        this.bg = bg;

        try {

            //spriteImage = ImageIO.read(getClass().getResource(path));
            spriteSheet = ImageIO.read(new File("catapultspritesheet.png"));

        } catch ( Exception e ) {

            e.printStackTrace();
            spriteSheet = null;

        }

        setSubImage(1, 1, 64, 64);

        outterBounds = new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f));
        innerBounds.add(new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f)));

    }

    public void setSubImage(int col, int row, int width, int height) {
        spriteImage = spriteSheet.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
    }

    public void updatePlayer(float deltaTime, Matrix3x3f viewport) {
        update(deltaTime, viewport);

        if(this.intersectsGround(bg)) {
//            do {
//                translate.y += 0.1f;
//            } while (this.intersectsGround(bg));
            gravityApplies = false;
        }
    }

    public boolean intersectsGround(Sprite bg) {
        for(int i=0; i < this.innerBounds.size(); i++) {
            if (this.innerBounds.get(i).intersects(this.bg.groundBound))
                return true;
        }
        return false;
    }

}
