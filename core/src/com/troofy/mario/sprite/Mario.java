package com.troofy.mario.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.troofy.mario.MarioBros;
import com.troofy.mario.screen.PlayScreen;

public class Mario extends Sprite {

    private World world;
    private Body box2body;
    private TextureRegion marioStand;


    public Mario(World world, PlayScreen playScreen){
        //super(playScreen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(playScreen.getAtlas().findRegion("little_mario"), 0,0,16,16);
        setBounds(0,0,16/MarioBros.PPM, 16/MarioBros.PPM);
        setRegion(marioStand);
    }

    private void defineMario() {
        BodyDef marioBodyDef = new BodyDef();
        marioBodyDef.position.set(32/ MarioBros.PPM,32/MarioBros.PPM);
        marioBodyDef.type = BodyDef.BodyType.DynamicBody;

        box2body = world.createBody(marioBodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(6/MarioBros.PPM);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = circleShape;
        box2body.createFixture(fDef);

    }

    public void update(float time){
        setPosition(box2body.getPosition().x - getWidth(), box2body.getPosition().y = getHeight() );
    }

    public World getWorld() {
        return world;
    }

    public Body getBox2body() {
        return box2body;
    }
}
