package object;

import util.Vector2f;
import bounding.*;

public class Background extends Sprite {
    public Background() {
        super("snow_bg.jpg", new Vector2f(-16.0f / 2.0f, 9.0f / 2.0f), new Vector2f(16.0f / 2.0f, -9.0f / 2.0f));

        outterBounds = new BoundingBox(new Vector2f(-9f, -5.5f), new Vector2f(9f, 4.5f));
        innerBounds.add(new BoundingBox (new Vector2f(-8f, -4.5f), new Vector2f(8f, 5.5f))); //bottom
        innerBounds.add(new BoundingBox (new Vector2f(-9f, -4.5f), new Vector2f(-8f, 4.5f))); //left
        innerBounds.add(new BoundingBox (new Vector2f(8f, -4.5f), new Vector2f(9f, 4.5f))); //right
    }
}
