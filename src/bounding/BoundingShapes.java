package bounding;

import util.Matrix3x3f;
import util.Vector2f;

import java.awt.*;

public interface BoundingShapes {

    public boolean intersectRectangle( BoundingBox test );

    public boolean intersectCircle( BoundingCircle test );

    public boolean pointInShape( Vector2f test );

    public void render ( Graphics G, Color color, Matrix3x3f world );

    public boolean intersects (BoundingShapes bound);

}
