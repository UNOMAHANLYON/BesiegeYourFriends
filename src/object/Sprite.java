package object;

import bounding.BoundingShapes;
import util.Matrix3x3f;
import util.Vector2f;
import util.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Sprite {

    private final float gravity = 4.0f;

    public BoundingShapes outterBounds;
    public ArrayList<BoundingShapes> innerBounds;
    protected BufferedImage spriteImage;
    private BufferedImage scaledImage;
    private Vector2f topLeft;
    private Vector2f bottomRight;
    private String path;

    protected Matrix3x3f world, boundMatrix;
    private float rotateRadian;
    private Vector2f translate;
    private Vector2f scale;
    protected boolean showBounds;

    public Sprite( String path, Vector2f topLeft, Vector2f bottomRight ){

        this.path = path;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.innerBounds = new ArrayList<BoundingShapes>();
        try {

            //spriteImage = ImageIO.read(getClass().getResource(path));
            spriteImage = ImageIO.read(new File(path));

        } catch ( Exception e ) {

            e.printStackTrace();
            spriteImage = null;

        }

        translate = new Vector2f(0, 0);
        scale = new Vector2f(1, 1);
        rotateRadian = 0;

    }

    public void render( Graphics G ) {
        if ( showBounds ) {
            renderBounds(G);
        }

        if (spriteImage != null) {
            //G.drawImage(spriteImage, (int) translate.x, (int) translate.y, null);
            scaleImage(world);
            Graphics2D g2d = (Graphics2D) G;
            g2d.drawImage(scaledImage, createTransform(), null);
        }



    }

    public void renderBounds(Graphics G) {
        //System.out.println("rendering bounds...");
        outterBounds.render(G, Color.BLACK, boundMatrix);

        for (int i=0; i<innerBounds.size(); i++) {
            innerBounds.get(i).render(G, Color.GREEN, boundMatrix);
        }

    }

    private AffineTransform createTransform() {
        Vector2f screen = world.mul(translate);
        AffineTransform transform = AffineTransform.getTranslateInstance(screen.x, screen.y);
        //transform.scale(scale.x, scale.y);
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

        world =  viewport;
        updateWorld(viewport);
//        this.scale = scale;
//        translate.translate( tx, ty );
//        this.rotateRadian += rotate;

    }

    public void updateWorld() {
        boundMatrix = Matrix3x3f.identity();

        boundMatrix = boundMatrix.mul(Matrix3x3f.scale(scale));
        //worldMatrix = worldMatrix.mul(Matrix3x3f.shear(shear));
        boundMatrix = boundMatrix.mul(Matrix3x3f.rotate(rotateRadian));
        boundMatrix = boundMatrix.mul(Matrix3x3f.translate(translate));
    }

    public void updateWorld(Matrix3x3f viewport) {
        updateWorld();

        boundMatrix = boundMatrix.mul(viewport);
    }

    public void applyGravity(float delta) {
        translate.y -= gravity * delta;
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

    public void moveLeft ( float value ) {

        translate.subtract(new Vector2f(value, 0 ));

    }

    public void moveRight ( float value  ) {

        translate.add(new Vector2f( value, 0 ));

    }

}
