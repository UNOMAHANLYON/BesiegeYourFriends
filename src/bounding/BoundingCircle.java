package bounding;

import util.Matrix3x3f;
import util.Vector2f;

import java.awt.*;

public class BoundingCircle implements BoundingShapes {

    private Vector2f focus = new Vector2f();
    private float radius;

    private Vector2f focusW = new Vector2f();
    private float radiusW;

    private Matrix3x3f world;

    public BoundingCircle() {

        this.focus.x = 0.0f;
        this.focus.y = 0.0f;
        this.focus.w = 1.0f;
        this.radius = 1;

    }

    public BoundingCircle(Vector2f v, float r) {

        this.focus.x = v.x;
        this.focus.y = v.y;
        this.focus.w = v.w;
        this.radius = r;

    }

    public BoundingCircle(float x, float y, float r) {

        this.focus.x = x;
        this.focus.y = y;
        this.focus.w = 1.0f;
        this.radius = r;

    }

    public Vector2f getFocus (){

        return new Vector2f( focus );

    }

    public float getRadius () {

        return radius;

    }

    @Override
    public boolean intersectRectangle( BoundingBox test) {

        float d = 0.0f;
        Vector2f min = test.getMinW();
        Vector2f max = test.getMaxW();

        if (focusW.x < min.x) d += (focusW.x - min.x) * (focusW.x - min.x);
        if (focusW.x > max.x) d += (focusW.x - max.x) * (focusW.x - max.x);
        if (focusW.y < min.y) d += (focusW.y - min.y) * (focusW.y - min.y);
        if (focusW.y > max.y) d += (focusW.y - max.y) * (focusW.y - max.y);

        return d < radius * radius;

    }

    @Override
    public boolean intersectCircle( BoundingCircle test) {

        Vector2f c = test.focusW.subtract(this.focusW); //Get the vector between centers by subtracting
        float r = test.radius + this.radius; //Add the radii
        return c.lenSqr() < r * r; //Avoid the sqrt by squaring r

    }

    @Override
    public boolean pointInShape( Vector2f test ) {

        Vector2f dist = test.subtract(focusW); //Find distance between point and circle
        return dist.lenSqr() < radius * radius; //Once again avoid using sqrt

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

        Vector2f topLeft = new Vector2f(focus.x - radius, focus.y
                + radius);
        topLeft = world.mul(topLeft);
        topLeft = viewport.mul(topLeft);
        Vector2f bottomRight = new Vector2f(focus.x + radius, focus.y
                - radius);
        bottomRight = world.mul(bottomRight);
        bottomRight = viewport.mul(bottomRight);
        int circleX = (int) topLeft.x;
        int circleY = (int) topLeft.y;
        int circleWidth = (int) (bottomRight.x - topLeft.x);
        int circleHeight = (int) (bottomRight.y - topLeft.y);
        
        Vector2f focusV = viewport.mul(focusW);

        G.setColor(color);
        //G.drawOval( (int)focusV.x, (int)focusV.y, (int)radius, (int)radius );
        G.drawOval(circleX, circleY, circleWidth, circleHeight);
    }

    public void updateWorld(Matrix3x3f world) {
        this.world = world;

        this.focusW = world.mul(focus);
    }

}