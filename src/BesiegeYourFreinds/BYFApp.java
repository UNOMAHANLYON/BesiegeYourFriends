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

        if ( !disableControls ) {
            if (turn == 1) {

                currentPlayer = player1;

            } else {

                currentPlayer = player2;

            }

            if (keyboard.keyDown(KeyEvent.VK_D)) {

                currentPlayer.moveRight(0.25f * delta);

            }

            if (keyboard.keyDown(KeyEvent.VK_A)) {

                currentPlayer.moveLeft(0.25f * delta);

            }

            if (keyboard.keyDown(KeyEvent.VK_W)) {

                currentPlayer.raiseAngle(delta);

            }

            if (keyboard.keyDown(KeyEvent.VK_S)) {

                currentPlayer.lowerAngle(delta);

            }

            if (keyboard.keyDown(KeyEvent.VK_Q)) {

                currentPlayer.subPower(delta);

            }

            if (keyboard.keyDown(KeyEvent.VK_E)) {

                currentPlayer.addPower(delta);

            }

            if (keyboard.keyDownOnce(KeyEvent.VK_X)) {

                currentPlayer.cycleAmmoLeft();

            }

            if (keyboard.keyDownOnce(KeyEvent.VK_C)) {

                currentPlayer.cycleAmmoRight();

            }

            if (keyboard.keyDownOnce(KeyEvent.VK_SPACE)) {

                disableControls = true;
                if (turn == 1) {

                    testAmmo = new Ammo(new Vector2f(player1.getLoc()), player1.power, player1.angle, 1);
                    turn = 2;

                } else {

                    testAmmo = new Ammo(new Vector2f(player2.getLoc()), player2.power, player2.angle, 2);
                    turn = 1;

                }

            }
        }

    }

    @Override
    protected void updateObjects(float delta) {

        bg.updateBG(delta, getViewportTransform());
        player1.updatePlayer(delta, getViewportTransform());
        player2.updatePlayer(delta, getViewportTransform());
        if (testAmmo != null ) {

            testAmmo.update(delta, getViewportTransform());
            if ( testAmmo.checkCollisions( bg ) ){



            }

            if ( testAmmo.checkCollisions( player1 ) ){

                if ( testAmmo.tag != player1.tag ) {

                    player1.dealDamage( testAmmo.damage );
                    testAmmo = null;
                    disableControls = false;

                }

            }

            if ( testAmmo.checkCollisions( player2 ) ) {

                if ( testAmmo.tag != player2.tag ) {

                    player2.dealDamage( testAmmo.damage );
                    testAmmo = null;
                    disableControls = false;

                }

            }


        }

    }

    @Override
    protected void render(Graphics g) {

        bg.renderBG(g);
        g.setColor(Color.GREEN);
        g.fillRect(5, 5, player1.health * 2, 25);

        g.setColor(Color.WHITE);
        g.drawRect(5, 5, 200, 25);

        if(player1.health >= 0) {
            g.setColor(Color.BLACK);
            g.drawString("Player 1 Health: " + player1.health  +"%", 5, 45);
        }
        else {
            g.setColor(Color.BLACK);
            g.drawString("Player 1 Health: " + 0 +"%", 5, 45);
        }

        g.setColor(Color.BLACK);
        g.drawString("Angle: " + (int) player1.angle, 5, 60 );
        g.drawString("Power: " + (int) player1.power, 5, 75 );

        g.setColor(Color.GREEN);
        g.fillRect(945, 5, player2.health * 2, 25);

        g.setColor(Color.WHITE);
        g.drawRect(945, 5, 200, 25);

        if(player2.health >= 0) {
            g.setColor(Color.BLACK);
            g.drawString("Player 2 Health: " + player2.health  +"%", 945, 45);
        }
        else {
            g.setColor(Color.BLACK);
            g.drawString("Player 2 Health: " + 0 +"%", 945, 45);
        }

        g.setColor(Color.BLACK);
        g.drawString("Angle: " + (int) player2.angle , 945, 60 );
        g.drawString("Power: " + (int) player2.power , 945, 75 );

        player1.render(g);
        player2.render(g);
        if (testAmmo != null)
            testAmmo.render(g);

        super.render(g);
    }

}
