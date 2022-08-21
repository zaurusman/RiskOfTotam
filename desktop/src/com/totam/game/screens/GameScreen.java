package com.totam.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.totam.game.Hero;
import com.totam.game.RiskOfTotam;
import com.totam.game.scenes.Hud;

import java.awt.*;

public class GameScreen implements Screen {


    final RiskOfTotam game;
    private OrthographicCamera camera;
    private Viewport port;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    Texture bg;
    Texture tile_image;
    Array<Rectangle> tiles;
    Hero hero;
    float elapsedTime;

    public GameScreen(final RiskOfTotam game) {

        this.game = game;

        bg = new Texture("dark_bg.jpg");
        tile_image = new Texture("tile.png");

        hero = new Hero(game);

        camera = new OrthographicCamera();

        port = new StretchViewport(RiskOfTotam.V_WIDTH, RiskOfTotam.V_HEIGHT, camera);

        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("default_level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
        /*
        camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        tiles = new Array<>();
        SpawnFloor();*/
    }

    public void SpawnFloor() {
        for (int i = 0; i < game.SCREEN_WIDTH / game.TILE_SIZE + 1; ++i) {
            Rectangle floor = new Rectangle();
            floor.x = i * game.TILE_SIZE;
            floor.y = 0;
            tiles.add(floor);
        }
    }


    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if(Gdx.input.isTouched()) {
            camera.position.x += 100 * dt;
        }
    }

    public void update(float dt) {
        handleInput(dt);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0, 0, 0.2f, 1);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.setProjectionMatrix(camera.combined);
        /*
        game.batch.begin();

        game.batch.draw(bg, 0, 0);
        for (Rectangle floor : tiles) {
            game.batch.draw(tile_image, floor.x, floor.y);
        }

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
        hero.dispose();
    }
}
