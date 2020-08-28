package com.troofy.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.troofy.mario.MarioBros;
import com.troofy.mario.scene.Hud;
import com.troofy.mario.sprite.Mario;
import com.troofy.mario.tools.B2WorldCreator;

public class PlayScreen implements Screen {

    private MarioBros game;
    private OrthographicCamera gameCam;
    private Viewport gameViewPort;
    private Hud hud;
    private Mario marioPlayer;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private World world;
    private Box2DDebugRenderer b2dr;



    public PlayScreen(final MarioBros game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gameViewPort = new FitViewport(MarioBros.V_WIDTH/MarioBros.PPM, MarioBros.V_HEIGHT/MarioBros.PPM, gameCam);
        hud = new Hud(game.getBatch());
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map,1/MarioBros.PPM);

        gameCam.position.set(gameViewPort.getWorldWidth()/2, gameViewPort.getWorldHeight()/2, 0);
        world = new World(new Vector2(0,-10 ), true);
        b2dr = new Box2DDebugRenderer();

        marioPlayer = new Mario(world);
        B2WorldCreator b2WorldCreator = new B2WorldCreator(world, map);
    }

    @Override
    public void show() {

    }

    private void handleInput(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            marioPlayer.getBox2body().applyLinearImpulse(new Vector2(0,4), marioPlayer.getBox2body().getWorldCenter(),true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && marioPlayer.getBox2body().getLinearVelocity().x  <= 2){
            marioPlayer.getBox2body().applyLinearImpulse(new Vector2(0.1f,0), marioPlayer.getBox2body().getWorldCenter(),true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && marioPlayer.getBox2body().getLinearVelocity().x  >= -2){
            marioPlayer.getBox2body().applyLinearImpulse(new Vector2(-0.1f,0), marioPlayer.getBox2body().getWorldCenter(),true);
        }

    }

    private void update(float deltaTime){
        //handle user input first
        handleInput(deltaTime);

        world.step(1/60f, 6,2);
        gameCam.update();
        gameCam.position.x = marioPlayer.getBox2body().getPosition().x;
        mapRenderer.setView(gameCam);

    }

    @Override
    public void render(float delta) {
        //separate our update logic from render method
        update(delta);

        //clear the game screen with black
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        mapRenderer.render();

        //render our Box2dDebugLines
        b2dr.render(world,gameCam.combined);

        //set our batch to now draw what the HUD camera sees
        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameViewPort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
