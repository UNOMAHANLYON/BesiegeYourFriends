package object;

import util.Matrix3x3f;

import java.awt.*;

public class MenuManager {
    private Menu titleScreen;
    private Menu player1Wins;
    private Menu player2Wins;
    public int winner;

    public MenuManager() {
        titleScreen = new Menu("/images/TitleScreen.png");
        player1Wins = new Menu("/images/Player1Wins.png");
        player2Wins = new Menu("/images/Player2Wins.png");

        winner = 0;
    }

    public void update(float deltaTime, Matrix3x3f viewport) {
        titleScreen.update(deltaTime, viewport);
        player1Wins.update(deltaTime, viewport);
        player2Wins.update(deltaTime, viewport);
    }

    public void render(Graphics g) {
        if (winner == 0) {
            titleScreen.render(g);
        } else if (winner == 1) {
            player1Wins.render(g);
        } else if (winner == 2) {
            player2Wins.render(g);
        }
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
