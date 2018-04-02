package bounding;

import util.Vector2f;

public interface BoundingShapes {

    public boolean intersectRectangle( BoundingBox test );

    public boolean intersectCircle( BoundingCircle test );

    public boolean pointInShape( Vector2f test );

}
