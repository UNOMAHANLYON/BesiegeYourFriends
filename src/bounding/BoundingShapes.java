package bounding;

import util.Vector2f;

import java.awt.*;

public interface BoundingShapes {

    public boolean intersectRectangle( BoundingBox test );

    public boolean intersectCircle( BoundingCircle test );

    public boolean pointInShape( Vector2f test );

    public void render ( Graphics G, Color color );

    public boolean intersects (BoundingShapes bound);

}
