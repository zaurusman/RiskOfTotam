package com.totam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero {
    final RiskOfTotam game;
    final int heroWidth = 100;
    final int heroHeight = 74;
    final private Texture heroFrames;
    public Animation<TextureRegion> aniHero;
    public int xPosition;
    public int yPosition;
    final private int movementSpeed = 300;
    public Hero(final RiskOfTotam game) {
        this.game = game;
        xPosition = 800;
        yPosition = 40;

        //set the Hero animation
        heroFrames = new Texture(Gdx.files.internal("hero.png"));
        TextureRegion [][] tmpframes = TextureRegion.split(heroFrames,heroWidth,heroHeight);

        TextureRegion[] aniFrames = new TextureRegion[4];
        int index = 0;
        //for(int i= 0; i<15;++i)
        {
            for(int j=0; j<4;++j)
            {
                aniFrames[index++] = tmpframes[0][j];
            }
        }
        aniHero = new Animation<>(1f/4f, aniFrames);

    }

    public void draw(final RiskOfTotam game,final float elapsedTime) {
        game.batch.draw( aniHero.getKeyFrame(elapsedTime,true),xPosition,yPosition);
    }

    public void idle() {

    }

    public void moveRight() {
        xPosition += movementSpeed  * Gdx.graphics.getDeltaTime();
        if(xPosition > game.SCREEN_WIDTH - heroWidth)
        {
            xPosition = game.SCREEN_WIDTH - heroWidth;
        }
    }

    public void moveLeft() {
        xPosition -= movementSpeed * Gdx.graphics.getDeltaTime();
        if(xPosition < 0)
        {
            xPosition = 0;
        }
    }

    public void jump(){

    }

    public void attack(){

    }

    public void dispose() {
        heroFrames.dispose();
    }

}
