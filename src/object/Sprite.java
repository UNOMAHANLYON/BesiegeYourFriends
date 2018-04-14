package object;

import bounding.BoundingShapes;
import util.Matrix3x3f;
import util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

    private BoundingShapes outterBounds;
    private ArrayList<BoundingShapes> innerBounds;
    private BufferedImage spriteImage;
    private String path;

    private Matrix3x3f world;
    private float rotateRadian;
    private Vector2f translate;
    private float scale;

    public Sprite( String path ){

        this.path = path;
        try {

            spriteImage = ImageIO.read(getClass().getResource(path));

        } catch ( Exception e ) {

            e.printStackTrace();

        }

    }

    public void render( Graphics G, Boolean show ) {

        if ( show ) {

            showBounds();

        }

    }

    public void showBounds() {



    }

    public void update( float rotate, float scale, float tx, float ty, float deltaTime ){

        world =  Matrix3x3f.identity();
        this.scale = scale;
        translate.translate( tx, ty );
        this.rotateRadian += rotate;




    }

}
