package object;

import bounding.BoundingCircle;
import util.Matrix3x3f;
import util.Vector2f;

public class Ammo extends Sprite {


    private float horizontal;
    private float vertical;
    private int type;
    private int direction;
    public int damage;

    public Ammo( Vector2f location, float power, Float angle, int player ) {

        super( "ammospritesheet.png", new Vector2f(-0.175f, 0.175f), new Vector2f(0.175f, -0.175f));

    //    this.type = type;
        this.showBounds = true;
        this.gravityApplies = true;
        super.tag = player;

        switch(player) {

            case 1:
                direction = 1;
                break;
            case 2:
                direction = -1;
                break;

        }

        damage = 100;

        if ( angle.compareTo(90.0f) > 0 ) {

            angle = 180 - angle;
            direction *= -1;

        }

        if ( angle.compareTo(90.0f)!= 0 ) {

            this.horizontal = (float) Math.cos(Math.toRadians(angle)) * power;
            this.vertical = (float) Math.sin(Math.toRadians(angle)) * power;

        } else {

            this.horizontal = 0;
            this.vertical = power;

        }

        outterBounds = new BoundingCircle( new Vector2f(0,  0), 0.35f );
        innerBounds.add( new BoundingCircle( new Vector2f(0,  0), 0.15f ) );

        setSubImage(1, 1, 64, 64);

        setLocation(location);
    }

    @Override
    public void update( float deltaTime, Matrix3x3f viewport) {


//        horizontal = (horizontal + gravity) * deltaTime;

        super.translate.y += vertical * deltaTime;
        super.translate.x += horizontal * direction * deltaTime;
        //vertical -= (gravity * deltaTime);

        super.update(deltaTime, viewport);

//        world =  viewport;
//        this.viewport = viewport;
//        updateBoundWorld();

//        outterBounds.updateWorld(boundMatrix);
//        for (int i=0; i < this.innerBounds.size(); i++) {
//            innerBounds.get(i).updateWorld(boundMatrix);
//        }

    }

    public boolean checkCollisions ( Sprite test ){

        for(int i=0; i < this.innerBounds.size(); i++) {
            if (this.innerBounds.get(i).intersects( test.outterBounds )) {

                for( int j =0; j < test.innerBounds.size(); j++ ){

                    if ( this.innerBounds.get(i).intersects( test.innerBounds.get(j) ) ){

                        return true;

                    }

                }

            }
        }

        return false;

    }

}
