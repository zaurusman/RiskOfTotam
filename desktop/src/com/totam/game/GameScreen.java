package com.totam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class GameScreen implements Screen {


    final RiskOfTotam game;

    OrthographicCamera camera;

    Texture bg;

    Texture tile_image;

    Array<Rectangle> tiles;

    public GameScreen(final RiskOfTotam game){

        this.game = game;
        bg = new Texture("dark_bg.jpg");
        tile_image = new Texture("tile.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        tiles = new Array<>();
        SpawnFloor();
    }

    public void SpawnFloor(){
        for(int i=0;i< game.SCREEN_WIDTH/ game.TILE_SIZE+1;++i) {
            Rectangle floor =new Rectangle();
            floor.x = i* game.TILE_SIZE;
            floor.y = 0;
            tiles.add(floor);
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(bg,0,0);
        for (Rectangle floor : tiles){
            game.batch.draw(tile_image,floor.x,floor.y);
        }

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
