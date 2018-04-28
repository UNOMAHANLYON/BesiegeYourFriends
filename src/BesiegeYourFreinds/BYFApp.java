package BesiegeYourFreinds;

import object.Background;
import object.PlayerSprite;
import util.SimpleFramework;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BYFApp extends SimpleFramework {

    public boolean show;
    public Background bg;
    public PlayerSprite player;

    public BYFApp() {
        appTitle = "Besiege Your Friends";
        appWidth = 1280;
        appHeight = 720;
        appMaintainRatio = true;
        appBorderScale = 1.0f;
        appWorldWidth = 16.0f;
        appWorldHeight = 9.0f;
    }

    @Override
    protected void initialize() {
        super.initialize();

        bg = new Background();
        player = new PlayerSprite(bg);
    }

    @Override
    protected void processInput(float delta) {

        if ( keyboard.keyDown( KeyEvent.VK_D ) ) {

            player.moveRight( 0.25f );

        }

        if ( keyboard.keyDown( KeyEvent.VK_A ) ) {

            player.moveLeft( 0.25f );

        }

    }

    @Override
    protected void updateObjects(float delta) {

        bg.update(delta, getViewportTransform());
        player.update(delta, getViewportTransform());
    }

    @Override
    protected void render(Graphics g) {

        bg.render(g);
        player.render(g);

        super.render(g);
    }

}
