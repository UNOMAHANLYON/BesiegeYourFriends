package bounding;

import util.Matrix3x3f;
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

    public boolean intersects(BoundingShapes bound) {
        if (bound instanceof BoundingBox) {
            return this.intersectRectangle((BoundingBox) bound);
        } else if (bound instanceof BoundingCircle) {
            return this.intersectCircle((BoundingCircle) bound);
        } else {
            return false;
        }
    }

    public void render( Graphics G, Color color, Matrix3x3f world ) {
        Vector2f tl = world.mul(new Vector2f(min.x, max.y));
        Vector2f br = world.mul(new Vector2f(max.x, min.y));

        int width = (int) (br.x - tl.x);
        int height = (int) (br.y - tl.y);

        //System.out.println("TopLeft: X" + tl.x + " Y" + tl.y + " Width: " + width + " Height: " + height);

        G.setColor(color);
        G.drawRect((int)tl.x, (int)tl.y, width, height);

    }

}
