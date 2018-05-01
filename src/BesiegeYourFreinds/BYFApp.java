package BesiegeYourFreinds;

import object.Ammo;
import object.Background;
import object.PlayerSprite;
import util.SimpleFramework;
import util.Vector2f;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BYFApp extends SimpleFramework {

    public boolean show;
    public Background bg;
    public PlayerSprite player1;
    public PlayerSprite player2;
    public Ammo testAmmo;

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
        player1 = new PlayerSprite(bg, 1);
        player2 = new PlayerSprite(bg, 1);

        testAmmo = new Ammo(new Vector2f(-7f, -3.125f), 8f, 45f, 1);

        player1.setLocation(new Vector2f(-7f, -3.125f));
    }

    @Override
    protected void processInput(float delta) {
        super.processInput(delta);

        if ( keyboard.keyDown( KeyEvent.VK_D ) ) {

            player1.moveRight( 0.25f * delta );

        }

        if ( keyboard.keyDown( KeyEvent.VK_A ) ) {

            player1.moveLeft( 0.25f * delta);

        }

        if ( keyboard.keyDown( KeyEvent.VK_SHIFT ) && keyboard.keyDown( KeyEvent.VK_W ) ) {

            player1.raiseAngle( true );

        }

        if ( keyboard.keyDown( KeyEvent.VK_W ) ) {

            player1.raiseAngle( false );

        }

        if ( keyboard.keyDown( KeyEvent.VK_SHIFT ) && keyboard.keyDown( KeyEvent.VK_S ) ) {

            player1.lowerAngle( true );

        }

        if ( keyboard.keyDown( KeyEvent.VK_S ) ) {

            player1.lowerAngle( false );

        }

        if ( keyboard.keyDown( KeyEvent.VK_SHIFT ) && keyboard.keyDown( KeyEvent.VK_Q ) ) {

            player1.subPower( true );

        }

        if ( keyboard.keyDown( KeyEvent.VK_Q ) ) {

            player1.subPower( false );

        }

        if ( keyboard.keyDown( KeyEvent.VK_SHIFT ) && keyboard.keyDown( KeyEvent.VK_E ) ) {

            player1.addPower( true );

        }

        if ( keyboard.keyDown( KeyEvent.VK_E ) ) {

            player1.addPower( false );

        }



    }

    @Override
    protected void updateObjects(float delta) {

        bg.updateBG(delta, getViewportTransform());
        player1.updatePlayer(delta, getViewportTransform());
        testAmmo.update(delta, getViewportTransform());

    }

    @Override
    protected void render(Graphics g) {

        bg.renderBG(g);
        player1.render(g);
        testAmmo.render(g);

        super.render(g);
    }

}
