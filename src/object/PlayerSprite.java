package object;

import bounding.BoundingBox;
import util.Matrix3x3f;
import util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerSprite extends Sprite {

    private Background bg;
    private float moveDirection = 0f;
    public float power;
    public float angle;
    private final float maxPower = 100;
    private final float maxAngle = 180;
    private int player;
    private int shotDirection;

    public PlayerSprite(Background bg, int player ) {

        super("catapultspritesheet.png", new Vector2f(-0.375f, 0.375f), new Vector2f(0.375f, -0.375f));

        this.showBounds = true;
        this.gravityApplies = true;
        this.bg = bg;
        this.player = player;
        power = 50;
        angle = 0;

        switch ( player ) {

            case 1:
                shotDirection = 1;
                break;
            case 2:
                shotDirection = -1;
                break;

        }

        setSubImage(1, 1, 64, 64);

        outterBounds = new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f));
        innerBounds.add(new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f)));

    }

    public void updatePlayer(float deltaTime, Matrix3x3f viewport) {
        update(deltaTime, viewport);

        if(this.intersectsGround(bg)) {
//            do {
//                translate.y += 0.1f;
//            } while (this.intersectsGround(bg));
            gravityApplies = false;
        }

        if(this.intersects(bg)) {
            do {
                translate.x += moveDirection;
                update(deltaTime, viewport);
                System.out.println("backtrack");
            } while(this.intersects(bg));
        }
    }

    public Vector2f getLoc(){

        return new Vector2f( topLeft.subtract(bottomRight) );

    }

    public boolean intersectsGround(Sprite bg) {
        for(int i=0; i < this.innerBounds.size(); i++) {
            if (this.innerBounds.get(i).intersects(this.bg.groundBound))
                return true;
        }
        return false;
    }

    @Override
    public void moveLeft ( float value ) {
        super.moveLeft(value);

        moveDirection = 0.01f;
    }

    @Override
    public void moveRight ( float value ) {
        super.moveRight(value);

        moveDirection = -0.01f;
    }

    public void addPower ( boolean fast ){

        if ( fast ) {

            power += 5;

        } else {

            power ++;

        }

    }

    public void raiseAngle ( boolean fast ){

        if ( fast ) {

            angle += 5;

        } else {

            angle ++;

        }

    }

    public void lowerAngle ( boolean fast ){

        if ( fast ) {

            angle -= 5;

        } else {

            angle --;

        }

    }

    public void subPower ( boolean fast ){

        if ( fast ) {

            power -= 5;

        } else {

            power --;

        }

    }

}

