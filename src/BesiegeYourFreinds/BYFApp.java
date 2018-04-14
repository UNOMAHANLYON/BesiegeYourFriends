package BesiegeYourFreinds;

import object.Background;
import util.SimpleFramework;

import java.awt.*;

public class BYFApp extends SimpleFramework {

    public boolean show;
    public Background bg;

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
    }

    @Override
    protected void processInput(float delta) {

    }

    @Override
    protected void updateObjects(float delta) {

        bg.update(delta, getViewportTransform());
    }

    @Override
    protected void render(Graphics g) {

        bg.render(g);

        super.render(g);
    }

}
