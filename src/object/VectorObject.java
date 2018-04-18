package object;

import util.Matrix3x3f;
import util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class VectorObject implements Drawable {

    public ArrayList<Vector2f> vertices;
    public int numVerts;
    public Matrix3x3f world;
    public Color c;
    private int SCREEN_H, SCREEN_W;
    private Vector2f transVal, scaleVal, shearVal;
    private float rotRad = 0.0f;
    public Vector2f min, max;

    public VectorObject( int h, int w, Color c ) {

        SCREEN_H = h;
        SCREEN_W = w;
        vertices = new ArrayList<>();
        this.c = c;
        world = Matrix3x3f.identity();
        transVal = new Vector2f(w / 2, h / 2   );
        scaleVal = new Vector2f(1, 1);
        shearVal = new Vector2f();

    }

    public void setScreen( int h, int w ) {

        SCREEN_H = h;
        SCREEN_W = w;

    }

    @Override
    public void updateWorld(  ) {

        world = Matrix3x3f.identity();

        world = world.mul( Matrix3x3f.scale( scaleVal ) );

        world = world.mul( Matrix3x3f.shear( shearVal ) );

        world = world.mul( Matrix3x3f.rotate( rotRad ) );

        world = world.mul( Matrix3x3f.translate( transVal ) );


    }

    public void updateWorld (  Matrix3x3f viewport ) {

        this.updateWorld();
        world = world.mul( viewport );

    }

    @Override
    public void render(Graphics g) {

        Vector2f vertex1, vertex2;

        for ( int i = 0; i < numVerts; i++ ) {

            int j;
            if ( i + 1 == numVerts ){

                j = 0;

            } else {

                j = i + 1;

            }

            vertex1 = new Vector2f( world.mul( vertices.get( i ) ) );
            vertex2 = new Vector2f( world.mul( vertices.get( j ) ) );

            g.setColor( c );
            g.drawLine( (int)vertex1.x, (int)vertex1.y, (int)vertex2.x, (int)vertex2.y );

        }

    }

    public void setTransVal ( float x, float y) {

        transVal = new Vector2f( x, y );

    }

    public void translateObject( float x, float y ){

        transVal.translate( x, y );

    }

    public void scaleObject ( float x ){

        scaleVal.scale( x, x );

    }

    public void scaleObject ( float x, float y ){

        scaleVal.scale( x, y );

    }

    public void shearObject ( float x ){

        shearVal.shear( x, x );

    }

    public void rotateObject ( float rad ) {

        rotRad += rad;

    }

    public Vector2f getTransVal (){

        return transVal;

    }

    public int getSCREEN_H (){

        return SCREEN_H;

    }

    public int getSCREEN_W (){

        return SCREEN_W;

    }

}
