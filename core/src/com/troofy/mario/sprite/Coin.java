package com.troofy.mario.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.troofy.mario.MarioBros;

public class Coin extends InteractiveTileObject {

    public Coin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        BodyDef bdef = new BodyDef();
        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth()/2)/ MarioBros.PPM , (bounds.getY() + bounds.getHeight()/2)/MarioBros.PPM);

        body = world.createBody(bdef);

        shape.setAsBox((bounds.getWidth()/2)/MarioBros.PPM, (bounds.getHeight()/2)/MarioBros.PPM);
        fDef.shape = shape;
        body.createFixture(fDef);

    }
}
