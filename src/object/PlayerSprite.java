package object;

import bounding.BoundingBox;
import util.Matrix3x3f;
import util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import util.Utility;

public class PlayerSprite extends Sprite {

    private Background bg;
    private float moveDirection = 0f;
    public float power;
    public float angle;
    private final float maxPower = 20;
    private final float maxAngle = 180;
    private int shotDirection;
    public int selectedAmmo;
    private final int numAmmoTypes = 2;
    public boolean dead;
    private BufferedImage[] catapult;
    public boolean shooting;
    private final int speed = 400;
    private float timer;
    private int index;
    private long lastTime;

    public PlayerSprite(Background bg, int player ) {

        super("catapultspritesheet.png", new Vector2f(-0.375f, 0.375f), new Vector2f(0.375f, -0.375f));

        this.showBounds = false;
        this.gravityApplies = true;
        this.bg = bg;
        this.tag = player;
        power = 15;
        angle = 45;
        selectedAmmo = 0;
        dead = false;
        timer = 0;
        shooting = false;
        lastTime = System.currentTimeMillis();

        switch ( player ) {

            case 1:
                shotDirection = 1;
                setSubImage(1, 1, 64, 64);
                catapult = new BufferedImage[9];
                for(int i = 0; i < catapult.length; i++){
                    catapult[i] = setArraySubImage(i + 1, 1, 64,64);
                }
               break;
            case 2:
                shotDirection = -1;
                setSubImage(1, 7, 64, 64);
                catapult = new BufferedImage[10];
                for(int i = 0; i < catapult.length; i++) {
                    catapult[i] = setArraySubImage(i + 1, 7, 64, 64);
                }
                break;

        }

        scale = new Vector2f( shotDirection, 1 );
        outterBounds = new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f));
        innerBounds.add(new BoundingBox(new Vector2f(-0.375f, -0.375f), new Vector2f(0.375f, 0.375f)));

        System.out.println("test");
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

        //timer += deltaTime;

        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
                index++;
                timer = 0; //timer -= speed;

                if (index >= catapult.length) {
                    index = 0;
                    shooting = false;
                }
            }
        }



    public BufferedImage getCurrentFrame() {
        return catapult[index];
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (shooting) {
            return getCurrentFrame();
        }
        return catapult[0];
    }

    public void render(Graphics g) {
        scaleImage(world);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(scaledImage, createTransform(),  null);
    }

    public void scaleImage(Matrix3x3f view) {
        Vector2f screenTopLeft = view.mul(topLeft);
        Vector2f screenBottomRight = view.mul(bottomRight);
        int scaledWidth = (int) Math.abs(screenBottomRight.x - screenTopLeft.x);
        int scaledHeight = (int) Math.abs(screenBottomRight.y - screenTopLeft.y);
        scaledImage = Utility.scaleImage(getCurrentAnimationFrame(), scaledWidth, scaledHeight);
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

    public void addPower ( float delta ){

        if ( power < maxPower ) {
            power = power + 2 * delta;
        } else {
            power = 0;
        }

    }

    public void raiseAngle ( float delta ){

        if ( angle < maxAngle ) {
            angle = angle + 5* delta;
        } else {
            angle = 0;
        }

    }

    public void lowerAngle ( float delta ){

        if (angle > 0) {
            angle =  angle - 5*delta;
        } else {
            angle = maxAngle;
        }

    }

    public void subPower ( float delta ){
        if ( power > 0 ) {
            power = power - 2*delta;
        } else {
            power = maxPower;
        }

    }

    public void cycleAmmoLeft(){

        if ( selectedAmmo == 0 ) {

            selectedAmmo = numAmmoTypes - 1;

        }else {

            selectedAmmo --;

        }

    }

    public void cycleAmmoRight(){

        if ( selectedAmmo == numAmmoTypes - 1 ) {

            selectedAmmo = 0;

        }else {

            selectedAmmo ++;

        }

    }

    public void dealDamage( int damage ) {

        health -= damage;

    }

}

