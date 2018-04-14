package object;

import bounding.BoundingShapes;
import util.Matrix3x3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private BoundingShapes outterBounds;
    private BoundingShapes innerBounds;
    private BufferedImage spriteImage;
    private String path;

    private Matrix3x3f world;

    public Sprite( String path ){

        this.path = path;
        try {

            spriteImage = ImageIO.read(getClass().getResource(path));

        } catch ( Exception e ) {

            e.printStackTrace();

        }

    }

    public void render( Graphics G ) {



    }

    public void showBounds() {



    }

    public void Update( float rotate, float scale, float tx, float ty, float deltaTime ){

        world =  Matrix3x3f.identity();



    }

}
