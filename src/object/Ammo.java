package object;

import bounding.BoundingCircle;
import util.Matrix3x3f;
import util.Vector2f;

public class Ammo extends Sprite {


    private float horizontal;
    private float vertical;
    private int type;
    private int direction;

    public Ammo( Vector2f focus, float power, float angle, int player ) {

        super( "catapultspritesheet.png", focus );

    //    this.type = type;
        this.horizontal = (float) Math.cos( angle ) * power;
        this.vertical = (float) Math.sin( angle ) * power;
        switch(player) {

            case 1:
                direction = 1;
                break;
            case 2:
                direction = -1;
                break;

        }

        outterBounds = new BoundingCircle( focus, 0.5f );
        innerBounds.add( new BoundingCircle( focus, 0.25f ) );

        setSubImage(1, 8, 64, 64);

    }

    @Override
    public void update( float deltaTime, Matrix3x3f viewport) {

        horizontal = (horizontal + gravity) * deltaTime;
        super.translate.x += vertical * deltaTime;
        super.translate.y += horizontal;

        world =  viewport;
        this.viewport = viewport;
        updateBoundWorld();

        outterBounds.updateWorld(boundMatrix);
        for (int i=0; i < this.innerBounds.size(); i++) {
            innerBounds.get(i).updateWorld(boundMatrix);
        }

    }

}
