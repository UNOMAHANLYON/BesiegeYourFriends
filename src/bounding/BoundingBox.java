package bounding;

import util.Matrix3x3f;
import util.Vector2f;

import java.awt.*;

public class BoundingBox implements BoundingShapes {

    private Vector2f min = new Vector2f();
    private Vector2f max = new Vector2f();
    
    private Vector2f minW = new Vector2f();
    private Vector2f maxW = new Vector2f();

    private Matrix3x3f world = new Matrix3x3f();

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

    public Vector2f getMinW(){

        return minW;

    }

    public Vector2f getMaxW(){

        return maxW;

    }

    @Override
    public boolean intersectRectangle( BoundingBox test) {
        Vector2f minWB = test.getMinW();
        Vector2f maxWB = test.getMaxW();

        if (minW.x > maxWB.x || minWB.x > maxW.x) {//Check if Box A is to the right of Box B and vice-versa

            return false; //If so, they do not overlap

        }

        if (minW.y > maxWB.y || minWB.y > maxW.y) {//Check if Box is above Box B and vice-versa

            return false; //If so, they do not overlap

        }

        return true; //Otherwise, they do overlap

    }

    @Override
    public boolean intersectCircle( BoundingCircle test) {

        float d = 0.0f;
        Vector2f c = test.getFocus();
        float r = test.getRadius();

        if (c.x < minW.x) d += (c.x - minW.x) * (c.x - minW.x);
        if (c.x > maxW.x) d += (c.x - maxW.x) * (c.x - maxW.x);
        if (c.y < minW.y) d += (c.y - minW.y) * (c.y - minW.y);
        if (c.y > maxW.y) d += (c.y - maxW.y) * (c.y - maxW.y);
        return d < r * r;

    }

    @Override
    public boolean pointInShape( Vector2f test ) {

        return test.x > minW.x && test.x < maxW.x && test.y > minW.y && test.y < maxW.y;

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

    public void render( Graphics G, Color color, Matrix3x3f viewport ) {
        Vector2f tl = viewport.mul(new Vector2f(minW.x, maxW.y));
        Vector2f br = viewport.mul(new Vector2f(maxW.x, minW.y));

        int width = (int) (br.x - tl.x);
        int height = (int) (br.y - tl.y);

        G.setColor(color);
        G.drawRect((int)tl.x, (int)tl.y, width, height);
    }

    public void updateWorld(Matrix3x3f world) {
        this.world = world;

        Vector2f tl = world.mul(new Vector2f(min.x, max.y));
        Vector2f br = world.mul(new Vector2f(max.x, min.y));

        minW = new Vector2f(Math.min(tl.x, br.x), Math.min(tl.y, br.y));
        maxW = new Vector2f(Math.max(tl.x, br.x), Math.max(tl.y, br.y));
    }

}
