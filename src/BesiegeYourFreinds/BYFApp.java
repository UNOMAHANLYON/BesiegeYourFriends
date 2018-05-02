package BesiegeYourFreinds;

import object.Ammo;
import object.Background;
import object.PlayerSprite;
import object.PlayerStats;
import util.SimpleFramework;
import util.SoundPlayer;
import util.Vector2f;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BYFApp extends SimpleFramework {

    public boolean isMenu;
    public boolean show;
    public Background bg;
    public PlayerStats playerStats;
    public PlayerSprite player1;
    public PlayerSprite player2;
    public PlayerSprite currentPlayer;
    public Ammo testAmmo;
    public int turn;
    public int winner;

    private boolean disableControls;
    private SoundPlayer soundPlayer;

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
        isMenu = true;
        soundPlayer = new SoundPlayer();
        playerStats = new PlayerStats("PlayerStats.png");


        soundPlayer.PlayBG();

        //testAmmo = new Ammo(new Vector2f(-7f, -3.125f), 15f, 45f, 1);
    }

    @Override
    protected void processInput(float delta) {
        super.processInput(delta);

        if(isMenu){
            String path;
            if (keyboard.keyDown(KeyEvent.VK_D)) {
                path = "west_bg.jpg";
                bg = new Background(path);
                player1 = new PlayerSprite(bg, 1);
                player2 = new PlayerSprite(bg, 2);
                turn = 1;
                currentPlayer = player1;
                winner = 0;

                player1.setLocation(new Vector2f(-7f, -3.125f));
                player2.setLocation(new Vector2f ( 7f, -3.125f ));
                isMenu = false;
                disableControls = false;
            }

            if (keyboard.keyDown(KeyEvent.VK_A)) {
                path = "snow_bg.jpg";
                bg = new Background(path);
                player1 = new PlayerSprite(bg, 1);
                player2 = new PlayerSprite(bg, 2);
                turn = 1;
                currentPlayer = player1;
                winner = 0;

                player1.setLocation(new Vector2f(-7f, -3.125f));
                player2.setLocation(new Vector2f ( 7f, -3.125f ));
                isMenu = false;
                disableControls = false;
            }
        }

        if ( !disableControls && !isMenu) {
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
                soundPlayer.StopSoundLoop();
                soundPlayer.PlayCatapultLaunch();
                if (turn == 1) {

                    testAmmo = new Ammo(new Vector2f(player1.getLoc()), player1.power, player1.angle, 1);
                    turn = 2;

                } else {

                    testAmmo = new Ammo(new Vector2f(player2.getLoc()), player2.power, player2.angle, 2);
                    turn = 1;

                }

            }

            // Sound Management
            if (keyboard.keyDownOnce( KeyEvent.VK_D) || keyboard.keyDownOnce( KeyEvent.VK_A)) {
                soundPlayer.StopSoundLoop();
                soundPlayer.PlayRollingCart();
            }

            if ( !keyboard.keyDown( KeyEvent.VK_D ) && !keyboard.keyDown( KeyEvent.VK_A ) ) {
                if(!disableControls){
                    soundPlayer.StopSoundLoop();
                }

            }
        }

    }

    @Override
    protected void updateObjects(float delta) {
        if(!isMenu) {
            bg.updateBG(delta, getViewportTransform());
            playerStats.update(delta, getViewportTransform());
            player1.updatePlayer(delta, getViewportTransform());
            player2.updatePlayer(delta, getViewportTransform());
            if (testAmmo != null) {

                testAmmo.update(delta, getViewportTransform());
                if (testAmmo.intersects(player1)) {

                    if (testAmmo.tag != player1.tag) {

                        player1.dealDamage(testAmmo.damage);
                        testAmmo = null;

                        if (player1.health <= 0) {

                            winner = 2;

                        } else {
                            disableControls = false;
                            soundPlayer.StopSoundLoop();
                            //soundPlayer.PlayDamage();

                        }

                    }

                } else if (testAmmo.intersects(player2)) {

                    if (testAmmo.tag != player2.tag) {

                        player2.dealDamage(testAmmo.damage);
                        testAmmo = null;
                        if (player2.health <= 0) {

                            winner = 1;

                        } else {
                            disableControls = false;
                            soundPlayer.StopSoundLoop();
                            //soundPlayer.PlayDamage();

                        }

                    }

                } else if (testAmmo.intersects(bg) || testAmmo.intersectsGround(bg)) {

                    testAmmo = null;
                    disableControls = false;
                    soundPlayer.StopSoundLoop();

                }


            }
        }

    }

    @Override
    protected void render(Graphics g) {
        if(!isMenu) {
            bg.renderBG(g);

            playerStats.render(g);

            renderPlayerStats(g);

            player1.render(g);
            player2.render(g);
            if (testAmmo != null)
                testAmmo.render(g);

        }
        if (winner != 0) {
            g.setColor(Color.WHITE);
            g.drawString("Player " + winner + " has won this round!", appWidth / 2, appHeight / 2);
            soundPlayer.StopSoundLoop();
            isMenu = true;
        }
        if (isMenu) {
            g.setColor(Color.WHITE);
            g.drawString("Press 'A' to start new game on Ice Castle or press 'D' to play in the Wild West!", appWidth / 2, appHeight / 2 + 20);
            soundPlayer.StopSoundLoop();
            isMenu = true;
        }
        //super.render(g);
    }

    public void renderPlayerStats(Graphics g) {
        // Use world coordinates for Player 1 HealthBar
        Vector2f p1HealthbarTL = getViewportTransform().mul(new Vector2f(-7.4f, 3.7f));
        Vector2f p1HealthbarBR = getViewportTransform().mul(new Vector2f(-3.5f, 3.45f));
        int p1HealthW = (int) ((p1HealthbarBR.x - p1HealthbarTL.x) * player1.health * 0.01f);
        int p1HealthbarW = (int) (p1HealthbarBR.x - p1HealthbarTL.x);
        int p1HealthbarH = (int) (p1HealthbarBR.y - p1HealthbarTL.y);

        // Use world coordinates for Player 2 HealthBar
        Vector2f p2HealthbarTL = getViewportTransform().mul(new Vector2f(3.5f, 3.7f));
        Vector2f p2HealthbarBR = getViewportTransform().mul(new Vector2f(7.4f, 3.45f));
        int p2HealthW = (int) ((p2HealthbarBR.x - p2HealthbarTL.x) * player2.health * 0.01f);
        int p2HealthbarW = (int) (p2HealthbarBR.x - p2HealthbarTL.x);
        int p2HealthbarH = (int) (p2HealthbarBR.y - p2HealthbarTL.y);

        g.setColor(Color.GREEN);
        g.fillRect((int) p1HealthbarTL.x, (int) p1HealthbarTL.y, p1HealthW, p1HealthbarH);
        g.fillRect((int) p2HealthbarTL.x, (int) p2HealthbarTL.y, p2HealthW, p2HealthbarH);

        g.setColor(Color.WHITE);
        g.drawRect((int) p1HealthbarTL.x, (int) p1HealthbarTL.y, p1HealthbarW, p1HealthbarH);
        g.drawRect((int) p2HealthbarTL.x, (int) p2HealthbarTL.y, p2HealthbarW, p2HealthbarH);

        // Player Text Info
        Vector2f p1HealthDisplay = getViewportTransform().mul(new Vector2f(-7.3f, 3.2f));
        Vector2f p1AngleDisplay = getViewportTransform().mul(new Vector2f(-7.3f, 2.9f));
        Vector2f p1PowerDisplay = getViewportTransform().mul(new Vector2f(-7.3f, 2.6f));

        Vector2f p2HealthDisplay = getViewportTransform().mul(new Vector2f(3.6f, 3.2f));
        Vector2f p2AngleDisplay = getViewportTransform().mul(new Vector2f(3.6f, 2.9f));
        Vector2f p2PowerDisplay = getViewportTransform().mul(new Vector2f(3.6f, 2.6f));

        g.setColor(Color.BLACK);

        if(player1.health >= 0) {
            g.drawString("Player 1 Health: " + player1.health  +"%", (int) p1HealthDisplay.x, (int) p1HealthDisplay.y);
        }
        else {
            g.drawString("Player 1 Health: " + 0 +"%", (int) p1HealthDisplay.x, (int) p1HealthDisplay.y);
        }

        g.drawString("Angle: " + (int) player1.angle, (int) p1AngleDisplay.x, (int) p1AngleDisplay.y);
        g.drawString("Power: " + player1.power, (int) p1PowerDisplay.x, (int) p1PowerDisplay.y);

        if(player2.health >= 0) {
            g.drawString("Player 2 Health: " + player2.health  +"%", (int) p2HealthDisplay.x, (int) p2HealthDisplay.y);
        }
        else {
            g.drawString("Player 2 Health: " + 0 +"%", (int) p2HealthDisplay.x, (int) p2HealthDisplay.y);
        }

        g.drawString("Angle: " + (int) player2.angle , (int) p2AngleDisplay.x, (int) p2AngleDisplay.y );
        g.drawString("Power: " + (int) player2.power , (int) p2PowerDisplay.x, (int) p2PowerDisplay.y );

    }

}
