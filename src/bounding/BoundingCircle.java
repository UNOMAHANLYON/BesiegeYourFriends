package bounding;

import util.Vector2f;

import java.awt.*;

public class BoundingCircle implements BoundingShapes {

    private Vector2f focus = new Vector2f();
    private float radius;

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
        Vector2f min = test.getMin();
        Vector2f max = test.getMax();

        if (focus.x < min.x) d += (focus.x - min.x) * (focus.x - min.x);
        if (focus.x > max.x) d += (focus.x - max.x) * (focus.x - max.x);
        if (focus.y < min.y) d += (focus.y - min.y) * (focus.y - min.y);
        if (focus.y > max.y) d += (focus.y - max.y) * (focus.y - max.y);

        return d < radius * radius;

    }

    @Override
    public boolean intersectCircle( BoundingCircle test) {

        Vector2f c = test.focus.subtract(this.focus); //Get the vector between centers by subtracting
        float r = test.radius + this.radius; //Add the radii
        return c.lenSqr() < r * r; //Avoid the sqrt by squaring r

    }

    @Override
    public boolean pointInShape( Vector2f test ) {

        Vector2f dist = test.subtract(focus); //Find distance between point and circle
        return dist.lenSqr() < radius * radius; //Once again avoid using sqrt

    }

    @Override
    public void renderBounding( Graphics G ) {

        G.drawOval( (int)focus.x, (int)focus.y, (int)radius, (int)radius );

    }

}