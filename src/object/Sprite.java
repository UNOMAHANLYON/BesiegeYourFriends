package object;

import bounding.BoundingShapes;
import util.Matrix3x3f;
import util.ResourceLoader;
import util.Vector2f;
import util.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Sprite {

    protected final float gravity = -9.8f;

    public BoundingShapes outterBounds;
    public ArrayList<BoundingShapes> innerBounds;
    protected BufferedImage scaledImage;
    protected Vector2f topLeft;
    protected Vector2f bottomRight;
    protected Vector2f focus;
    protected String path;
    protected BufferedImage spriteSheet;
    protected BufferedImage spriteImage;

    protected Matrix3x3f world, boundMatrix, viewport;
    protected float rotateRadian;
    protected Vector2f translate;
    protected Vector2f scale;
    protected Vector2f velocity;
    protected boolean showBounds;
    public boolean gravityApplies;
    public int tag;
    public int health;

    public Sprite( String path, Vector2f topLeft, Vector2f bottomRight ){

        this.path = path;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.innerBounds = new ArrayList<BoundingShapes>();
        health = 100;
        try {
            //spriteSheet = ImageIO.read(ResourceLoader.load(Sprite.class, path, "images"));
            spriteSheet = ImageIO.read(Sprite.class.getResource(path));
            //spriteImage = ImageIO.read(getClass().getResource(path));
            //spriteSheet = ImageIO.read(new File(path));

        } catch ( Exception e ) {

            e.printStackTrace();
            spriteSheet = null;

        }

        translate = new Vector2f(0, 0);
        scale = new Vector2f(1, 1);
        rotateRadian = 0;
        velocity = new Vector2f(0, 0);
        gravityApplies = false;

    }

    public Sprite( String path, Vector2f focus ){

        this.path = path;
        this.focus = focus;
        this.innerBounds = new ArrayList<BoundingShapes>();
        try {

            //spriteImage = ImageIO.read(getClass().getResource(path));
            spriteSheet = ImageIO.read(new File(path));

        } catch ( Exception e ) {

            e.printStackTrace();
            spriteSheet = null;

        }

        translate = new Vector2f(0, 0);
        scale = new Vector2f(1, 1);
        rotateRadian = 0;
        gravityApplies = false;

    }

    public void setSubImage(int col, int row, int width, int height) {
        spriteImage = spriteSheet.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
    }

    public BufferedImage setArraySubImage(int col, int row, int width, int height) {
        return spriteSheet.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
    }

    public void render( Graphics G ) {

        if (spriteImage != null) {
            //G.drawImage(spriteImage, (int) translate.x, (int) translate.y, null);
            scaleImage(world);
            Graphics2D g2d = (Graphics2D) G;
            g2d.drawImage(scaledImage, createTransform(), null);
        }

        if ( showBounds ) {
            renderBounds(G);
        }

    }

    public void renderBounds(Graphics G) {
        //System.out.println("rendering bounds...");
        if (outterBounds != null)
            outterBounds.render(G, Color.BLACK, viewport);

        for (int i=0; i<innerBounds.size(); i++) {
            innerBounds.get(i).render(G, Color.RED, viewport);
        }

    }

    protected AffineTransform createTransform() {
        Vector2f screen = world.mul(translate);
        AffineTransform transform = AffineTransform.getTranslateInstance(screen.x, screen.y);
        transform.scale(scale.x, scale.y);
        transform.rotate(rotateRadian);
        transform.translate(-scaledImage.getWidth() / 2, -scaledImage.getHeight() / 2);
        return transform;
    }

    public void scaleImage(Matrix3x3f view) {
        Vector2f screenTopLeft = view.mul(topLeft);
        Vector2f screenBottomRight = view.mul(bottomRight);
        int scaledWidth = (int) Math.abs(screenBottomRight.x - screenTopLeft.x);
        int scaledHeight = (int) Math.abs(screenBottomRight.y - screenTopLeft.y);
        if (scaledImage == null || scaledWidth != scaledImage.getWidth() || scaledHeight != scaledImage.getHeight()) {
            scaledImage = Utility.scaleImage(spriteImage, scaledWidth, scaledHeight);
        }
    }

    public void update( float deltaTime, Matrix3x3f viewport){

        if (gravityApplies)
            this.applyGravity(deltaTime);

        world =  viewport;
        this.viewport = viewport;
        updateBoundWorld();

        outterBounds.updateWorld(boundMatrix);
        for (int i=0; i < this.innerBounds.size(); i++) {
            innerBounds.get(i).updateWorld(boundMatrix);
        }


//        this.scale = scale;
//        translate.translate( tx, ty );
//        this.rotateRadian += rotate;

    }

    public void updateBoundWorld() {
        boundMatrix = Matrix3x3f.identity();

        boundMatrix = boundMatrix.mul(Matrix3x3f.scale(scale));
        //worldMatrix = worldMatrix.mul(Matrix3x3f.shear(shear));
        boundMatrix = boundMatrix.mul(Matrix3x3f.rotate(rotateRadian));
        boundMatrix = boundMatrix.mul(Matrix3x3f.translate(translate));
    }

    public void updateBoundWorld(Matrix3x3f viewport) {
        updateBoundWorld();

        boundMatrix = boundMatrix.mul(viewport);
    }

    public void applyGravity(float delta) {
        velocity.y += gravity * delta;
        translate.y += velocity.y * delta;
    }

    public boolean intersects(Sprite sprite) {
        if (this.outterBounds.intersects(sprite.outterBounds)) {
            for(int i=0; i < this.innerBounds.size(); i++) {
                for (int j=0; j < sprite.innerBounds.size(); j++) {
                    if (this.innerBounds.get(i).intersects(sprite.innerBounds.get(j)))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean intersectsGround(Background bg) {
        for(int i=0; i < this.innerBounds.size(); i++) {
            if (this.innerBounds.get(i).intersects(bg.groundBound))
                return true;
        }
        return false;
    }

    public void setLocation(Vector2f location) {
        translate = location;
    }

    public void setLocationY(float value) {
        translate.y = value;
    }


    public Vector2f getLoc(){

        return translate;

    }

    public void moveLeft ( float value ) {

        //translate.subtract(new Vector2f(value, 0 ));
        translate.x -= value;

    }

    public void moveRight ( float value  ) {

        //translate.add(new Vector2f( value, 0 ));
        translate.x += value;

    }



}
