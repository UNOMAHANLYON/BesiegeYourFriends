package object;

import bounding.BoundingCircle;
import util.Vector2f;

public class Ammo extends Sprite {


    private float horizontal;
    private float vertical;
    private int type;
    private int direction;

    public Ammo(String path, Vector2f focus, int type, float power, float angle, int direction ) {

        super( path, focus );

        this.type = type;
        this.horizontal = (float) Math.cos( angle ) * power;
        this.vertical = (float) Math.sin( angle ) * power;
        this.direction = direction;
        super.gravityApplies = true;

        outterBounds = new BoundingCircle( focus, 0.5f );
        innerBounds.add( new BoundingCircle( focus, 0.25f ) );

    }



}
