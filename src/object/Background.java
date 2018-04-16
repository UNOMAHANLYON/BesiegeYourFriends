package object;

import util.Vector2f;

public class Background extends Sprite {
    public Background() {
        super("snow_bg.jpg", new Vector2f(-16.0f / 2.0f, 9.0f / 2.0f), new Vector2f(16.0f / 2.0f, -9.0f / 2.0f));
    }
}
