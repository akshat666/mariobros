package com.troofy.mario.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.troofy.mario.MarioBros;

public class Mario extends Sprite {

    private World world;
    private Body box2body;

    public Mario(World world){
        this.world = world;
        defineMario();
    }

    private void defineMario() {
        BodyDef marioBodyDef = new BodyDef();
        marioBodyDef.position.set(32/ MarioBros.PPM,32/MarioBros.PPM);
        marioBodyDef.type = BodyDef.BodyType.DynamicBody;

        box2body = world.createBody(marioBodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5/MarioBros.PPM);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = circleShape;
        box2body.createFixture(fDef);

    }

    public World getWorld() {
        return world;
    }

    public Body getBox2body() {
        return box2body;
    }
}
