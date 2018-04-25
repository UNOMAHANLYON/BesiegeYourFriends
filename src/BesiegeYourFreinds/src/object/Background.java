package object;

import util.Vector2f;

import java.awt.image.BufferedImage;
import java.io.IOException;

import bounding.*;
import gfx.*;


public class Background extends Sprite {
	    public Background(Textures tex) {
        super(tex.snow, new Vector2f(-16.0f / 2.0f, 9.0f / 2.0f), new Vector2f(16.0f / 2.0f, -9.0f / 2.0f));

        innerBounds.add(new BoundingBox (new Vector2f(-8f, -4.5f), new Vector2f(8f, -5.5f))); //bottom
        innerBounds.add(new BoundingBox (new Vector2f(-9f, 4.5f), new Vector2f(-8f, -4.5f))); //left
        innerBounds.add(new BoundingBox (new Vector2f(8f, 4.5f), new Vector2f(9f, -4.5f))); //right
    }
	    
}