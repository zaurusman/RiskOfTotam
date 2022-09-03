package com.totam.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.totam.game.Sprites.Hero;
import com.totam.game.RiskOfTotam;
import com.totam.game.Scenes.Hud;

public class GameScreen implements Screen {


    final RiskOfTotam game;
    private OrthographicCamera camera;
    private Viewport port;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2D Variables
    private World world;
    private Box2DDebugRenderer b2dr;


    Texture bg;
    Texture tile_image;
    Hero player;

    public GameScreen(final RiskOfTotam game) {

        this.game = game;

        bg = new Texture("dark_bg.jpg");
        tile_image = new Texture("tile.png");

        camera = new OrthographicCamera();

        port = new StretchViewport(RiskOfTotam.V_WIDTH / RiskOfTotam.PPM, RiskOfTotam.V_HEIGHT / RiskOfTotam.PPM, camera);

        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("default_level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / RiskOfTotam.PPM);
        camera.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        player = new Hero(world);

        BodyDef bdef = new BodyDef();
        PolygonShape shape= new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RiskOfTotam.PPM, (rect.getY() + rect.getHeight() / 2) / RiskOfTotam.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RiskOfTotam.PPM, rect.getHeight() / 2 / RiskOfTotam.PPM);

            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipe bodies/fixtures
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RiskOfTotam.PPM, (rect.getY() + rect.getHeight() / 2) / RiskOfTotam.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RiskOfTotam.PPM, rect.getHeight() / 2 / RiskOfTotam.PPM);

            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create brick bodies/fixtures

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RiskOfTotam.PPM, (rect.getY() + rect.getHeight() / 2) / RiskOfTotam.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RiskOfTotam.PPM, rect.getHeight() / 2 / RiskOfTotam.PPM);

            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RiskOfTotam.PPM, (rect.getY() + rect.getHeight() / 2) / RiskOfTotam.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RiskOfTotam.PPM, rect.getHeight() / 2 / RiskOfTotam.PPM);

            fdef.shape = shape;
            body.createFixture(fdef);
        }

    }


    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt) {
        //Handle user input
        handleInput(dt);

        world.step(1/60f, 6, 2);

        camera.position.x = player.b2body.getPosition().x;
        //Update our game camera with correct coordinates after changes
        camera.update();
        //tell our renderer to draw only what our camera can see in the world
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        //Separate our update logic from renderer
        update(delta);

        //Clear the game screen with blue
        Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Render our game map
        renderer.render();

        //Render our  Box2DDebugLines
        b2dr.render(world, camera.combined);

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        /*
        game.batch.begin();

        elapsedTime += Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                hero.dashAttackRight(elapsedTime);
            }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                hero.jump(elapsedTime);
            } else {
                hero.moveRight(elapsedTime);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                hero.dashAttackLeft(elapsedTime);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                hero.jump(elapsedTime);
            }else {
                hero.moveLeft(elapsedTime);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            hero.attack(elapsedTime);
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            hero.jump(elapsedTime);
        } else {
            hero.idle(elapsedTime);
        }

        game.batch.end();
        */
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
       port.update(width, height);
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

    }
}
