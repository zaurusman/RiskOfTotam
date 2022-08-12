package com.totam.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen {
    final RiskOfTotam game;



    final private Texture hero_frames;
    public Animation<TextureRegion> aniHero;
    float elapsedTime;
    OrthographicCamera camera;



    public MainMenuScreen(final RiskOfTotam game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);

        //set the Hero animation
        hero_frames = new Texture(Gdx.files.internal("hero.png"));
        TextureRegion [][] tmpframes = TextureRegion.split(hero_frames,100,74);

        TextureRegion[] aniFrames = new TextureRegion[4];
        int index=0;
        //for(int i= 0; i<15;++i)
        {
            for(int j=0; j<4;++j)
            {
                aniFrames[index++] = tmpframes[0][j];
            }
        }
        aniHero = new Animation<>(1f/4f, aniFrames);

    }
    //public MainMenuScreen(final Drop game)....

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "Welcome to Risk of Totam", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        game.batch.begin();
        elapsedTime +=Gdx.graphics.getDeltaTime();
        game.batch.draw(aniHero.getKeyFrame(elapsedTime,true),800,40);
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
        hero_frames.dispose();
    }
}
