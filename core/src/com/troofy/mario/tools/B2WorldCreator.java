package com.troofy.mario.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.troofy.mario.MarioBros;
import com.troofy.mario.sprite.Brick;
import com.troofy.mario.sprite.Coin;

public class B2WorldCreator {

    private World world;
    private TiledMap tiledMap;

    public B2WorldCreator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for(MapObject mapObj : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ MarioBros.PPM , (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MarioBros.PPM, (rect.getHeight()/2)/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create ground bodies and fixtures
        for(MapObject mapObj : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/MarioBros.PPM , (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MarioBros.PPM, (rect.getHeight()/2)/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipe bodies and fixtures
        for(MapObject mapObj : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/MarioBros.PPM , (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MarioBros.PPM, (rect.getHeight()/2)/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create brick bodies and fixtures
        for(MapObject mapObj : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObj).getRectangle();
           new Brick(world,map,rect);
        }
        //create coin bodies and fixtures
        for(MapObject mapObj : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObj).getRectangle();
            new Coin(world,map,rect);
        }
    }
}
