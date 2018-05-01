package bounding;

import util.Vector2f;

import java.awt.*;

public class BoundingBox implements BoundingShapes {

    private Vector2f min = new Vector2f();
    private Vector2f max = new Vector2f();

    public BoundingBox() {

    }

    public BoundingBox ( Vector2f min, Vector2f max ) {

        this.min = min;
        this.max = max;

    }

    public void setMin ( Vector2f min ) {

        this.min = min;

    }

    public void setMax ( Vector2f max ) {

        this.max = max;

    }


    public Vector2f getMin(){

        return min;

    }

    public Vector2f getMax(){

        return max;

    }

    @Override
    public boolean intersectRectangle( BoundingBox test) {
        Vector2f minB = test.getMin();
        Vector2f maxB = test.getMax();

        if (min.x > maxB.x || minB.x > max.x) {//Check if Box A is to the right of Box B and vice-versa

            return false; //If so, they do not overlap

        }

        if (min.y > maxB.y || minB.y > max.y) {//Check if Box is above Box B and vice-versa

            return false; //If so, they do not overlap

        }

        return true; //Otherwise, they do overlap

    }

    @Override
    public boolean intersectCircle( BoundingCircle test) {

        float d = 0.0f;
        Vector2f c = test.getFocus();
        float r = test.getRadius();

        if (c.x < min.x) d += (c.x - min.x) * (c.x - min.x);
        if (c.x > max.x) d += (c.x - max.x) * (c.x - max.x);
        if (c.y < min.y) d += (c.y - min.y) * (c.y - min.y);
        if (c.y > max.y) d += (c.y - max.y) * (c.y - max.y);
        return d < r * r;

    }

    @Override
    public boolean pointInShape( Vector2f test ) {

        return test.x > min.x && test.x < max.x && test.y > min.y && test.y < max.y;

    }

    public void render( Graphics G ) {

        G.drawRect( (int)min.x, (int)min.y, (int)max.x, (int)max.y );

    }

}