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
    public PlayerSprite currentPlayer;
    public Ammo testAmmo;
    public int turn;
    private boolean disableControls;

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
        player2 = new PlayerSprite(bg, 2);
        turn = 1;
        currentPlayer = player1;
        disableControls = false;

        //testAmmo = new Ammo(new Vector2f(-7f, -3.125f), 15f, 45f, 1);

        player1.setLocation(new Vector2f(-7f, -3.125f));
        player2.setLocation(new Vector2f ( 7f, -3.125f ));
    }

    @Override
    protected void processInput(float delta) {
        super.processInput(delta);

        if ( turn == 1 ) {

            currentPlayer = player1;

        } else {

            currentPlayer = player2;

        }

        if ( keyboard.keyDown( KeyEvent.VK_D ) ) {

            currentPlayer.moveRight( 0.25f * delta );

        }

        if ( keyboard.keyDown( KeyEvent.VK_A ) ) {

            currentPlayer.moveLeft( 0.25f * delta);

        }

        if ( keyboard.keyDown( KeyEvent.VK_W ) ) {

            currentPlayer.raiseAngle(  );

        }

        if ( keyboard.keyDown( KeyEvent.VK_S ) ) {

            currentPlayer.lowerAngle(  );

        }

        if ( keyboard.keyDown( KeyEvent.VK_Q ) ) {

            currentPlayer.subPower(  );

        }

        if ( keyboard.keyDown( KeyEvent.VK_E ) ) {

            currentPlayer.addPower(  );

        }

        if ( keyboard.keyDown( KeyEvent.VK_X ) ) {

            currentPlayer.cycleAmmoLeft();

        }

        if ( keyboard.keyDown( KeyEvent.VK_C ) ) {

            currentPlayer.cycleAmmoRight();

        }

        if ( keyboard.keyDownOnce( KeyEvent.VK_SPACE ) ) {

            if ( turn == 1 ) {

                testAmmo = new Ammo( new Vector2f(player1.getLoc()), player1.power, player1.angle, 1 );
                turn = 2;

            }else {

                testAmmo = new Ammo( new Vector2f(player2.getLoc()), player2.power, player2.angle, 2 );
                turn = 1;

            }

        }



    }

    @Override
    protected void updateObjects(float delta) {

        bg.updateBG(delta, getViewportTransform());
        player1.updatePlayer(delta, getViewportTransform());
        player2.updatePlayer(delta, getViewportTransform());
        if (testAmmo != null )
            testAmmo.update(delta, getViewportTransform());

    }

    @Override
    protected void render(Graphics g) {

        bg.renderBG(g);
        player1.render(g);
        player2.render(g);
        if (testAmmo != null)
            testAmmo.render(g);

        super.render(g);
    }

}
