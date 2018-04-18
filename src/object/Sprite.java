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

    private BoundingShapes outterBounds;
    protected ArrayList<BoundingShapes> innerBounds;
    protected BufferedImage spriteImage;
    private BufferedImage scaledImage;
    private Vector2f topLeft;
    private Vector2f bottomRight;
    private String path;

    private Matrix3x3f world;
    private float rotateRadian;
    private Vector2f translate;
    private Vector2f scale;
    private boolean showBounds;

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
            showBounds(G);
        }

        if (spriteImage != null) {
            //G.drawImage(spriteImage, (int) translate.x, (int) translate.y, null);
            scaleImage(world);
            Graphics2D g2d = (Graphics2D) G;
            g2d.drawImage(scaledImage, createTransform(), null);
        }

    }

    public void showBounds(Graphics G) {

        for (int i=0; i<innerBounds.size(); i++) {
            innerBounds.get(i).render(G);
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
//        this.scale = scale;
//        translate.translate( tx, ty );
//        this.rotateRadian += rotate;



    }

    public void applyGravity(float delta) {
        translate.y -= gravity * delta;
    }

}
