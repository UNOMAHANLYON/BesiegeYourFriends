package object;

import bounding.BoundingShapes;
import util.Matrix3x3f;
import util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Sprite {

    private BoundingShapes outterBounds;
    private ArrayList<BoundingShapes> innerBounds;
    private BufferedImage spriteImage;
    private String path;

    private Matrix3x3f world;
    private float rotateRadian;
    private Vector2f translate;
    private Vector2f scale;
    private boolean showBounds;

    public Sprite( String path ){

        this.path = path;
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
            Graphics2D g2d = (Graphics2D) G;
            g2d.drawImage(spriteImage, createTransform(), null);
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
        transform.scale(scale.x, scale.y);
        transform.rotate(rotateRadian);
        transform.translate(-spriteImage.getWidth() / 2, -spriteImage.getHeight() / 2);
        return transform;
    }

    public void update( float deltaTime, Matrix3x3f viewport){

        world =  viewport;
//        this.scale = scale;
//        translate.translate( tx, ty );
//        this.rotateRadian += rotate;



    }

}
