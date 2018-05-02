package object;

import bounding.BoundingBox;
import util.Vector2f;

public class Menu extends Sprite {

    public Menu(String path) {
        super(path, new Vector2f(-16.0f / 2.0f, 9.0f / 2.0f), new Vector2f(16.0f / 2.0f, -9.0f / 2.0f));

        setSubImage(1, 1, 1280, 720);

        outterBounds = new BoundingBox(new Vector2f(-9f, -5.5f), new Vector2f(9f, 4.5f));
        innerBounds.add(new BoundingBox (new Vector2f(-8f, -5.5f), new Vector2f(8f, -3.5f))); //bottom
        innerBounds.add(new BoundingBox (new Vector2f(-9f, -4.5f), new Vector2f(-8f, 4.5f))); //left
        innerBounds.add(new BoundingBox (new Vector2f(8f, -4.5f), new Vector2f(9f, 4.5f))); //right
    }

}
