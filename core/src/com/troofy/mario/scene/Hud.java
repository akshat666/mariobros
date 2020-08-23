package com.troofy.mario.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.troofy.mario.MarioBros;

public class Hud {

    public Stage stage;
    private Viewport viewport;

    private int worldTimer;
    private float timeCount;
    private int score;

    private Label countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private Label scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));;
    private Label timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private Label levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;
    private Label worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;
    private Label marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;

    public Hud(final SpriteBatch batch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();

        table.add(scoreLabel).expandX().padTop(0);
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);


    }

}
