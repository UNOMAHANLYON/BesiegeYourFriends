package object;

import util.Matrix3x3f;
import util.Vector2f;
import bounding.*;

import java.awt.*;

public class Background extends Sprite {
    public BoundingShapes groundBound;

    public Background() {
        super("snow_bg.jpg", new Vector2f(-16.0f / 2.0f, 9.0f / 2.0f), new Vector2f(16.0f / 2.0f, -9.0f / 2.0f));

        this.showBounds = true;

        outterBounds = new BoundingBox(new Vector2f(-9f, -5.5f), new Vector2f(9f, 4.5f));
        groundBound = new BoundingBox (new Vector2f(-8f, -5.5f), new Vector2f(8f, -3.5f)); //bottom
        innerBounds.add(new BoundingBox (new Vector2f(-9f, -4.5f), new Vector2f(-8f, 4.5f))); //left
        innerBounds.add(new BoundingBox (new Vector2f(8f, -4.5f), new Vector2f(9f, 4.5f))); //right
    }

    public void updateBG(float deltaTime, Matrix3x3f viewport) {
        update(deltaTime, viewport);
        groundBound.updateWorld(boundMatrix);
    }

    public void renderBG(Graphics G) {
        render(G);

        if ( showBounds ) {
            groundBound.render(G, Color.GREEN, viewport);
        }
    }
}
