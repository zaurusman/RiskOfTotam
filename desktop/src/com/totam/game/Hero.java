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
    final private Animation<TextureRegion> idleHero;
    final private Animation<TextureRegion> attackHero;
    final private Animation<TextureRegion> dashAttackHero;
    final private Animation<TextureRegion> runHero;
    public int xPosition;
    public int yPosition;
    final private int movementSpeed = 300;

    public Hero(final RiskOfTotam game) {
        this.game = game;
        xPosition = 800;
        yPosition = 128;
        //set the Hero animation
        heroFrames = new Texture(Gdx.files.internal("hero.png"));
        TextureRegion[][] tempFrames = TextureRegion.split(heroFrames, heroWidth, heroHeight);

        TextureRegion[] idleFrames = new TextureRegion[4];
        System.arraycopy(tempFrames[0], 0, idleFrames, 0, 4);

        TextureRegion[] attackFrames = new TextureRegion[4];
        attackFrames[0] = tempFrames[7][6];
        attackFrames[1] = tempFrames[8][0];
        attackFrames[2] = tempFrames[8][1];
        attackFrames[3] = tempFrames[8][2];

        TextureRegion[] dashAttackFrames = new TextureRegion[6];
        dashAttackFrames[0] = tempFrames[13][3];
        dashAttackFrames[1] = tempFrames[13][4];
        dashAttackFrames[2] = tempFrames[13][5];
        dashAttackFrames[3] = tempFrames[13][6];
        dashAttackFrames[4] = tempFrames[14][0];
        dashAttackFrames[5] = tempFrames[14][1];


        TextureRegion[] runFrames = new TextureRegion[6];
        System.arraycopy(tempFrames[1], 1, runFrames, 0, 6);


        idleHero = new Animation<>(1f / 4f, idleFrames);
        attackHero = new Animation<>(1f / 8f, attackFrames);
        dashAttackHero = new Animation<>(1f / 8f, dashAttackFrames);
        runHero = new Animation<>(1f / 4f, runFrames);
    }

    public void idle(final float elapsedTime) {
        game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
    }

    public void moveRight(final float elapsedTime) {
        xPosition += movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition > game.SCREEN_WIDTH - heroWidth) {
            xPosition = game.SCREEN_WIDTH - heroWidth;
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        } else {
            game.batch.draw(runHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void moveLeft(final float elapsedTime) {
        xPosition -= movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition < 0) {
            xPosition = 0;
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        } else {
            game.batch.draw(runHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void jump() {

    }

    public void attack(final float elapsedTime) {
        game.batch.draw(attackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
    }

    public void dashAttackRight(final float elapsedTime) {
        xPosition += movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition > game.SCREEN_WIDTH - heroWidth) {
            xPosition = game.SCREEN_WIDTH - heroWidth;
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        } else {
            game.batch.draw(dashAttackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void dashAttackLeft(final float elapsedTime) {
        xPosition -= movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition < 0) {
            xPosition = 0;
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        } else {
            game.batch.draw(dashAttackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void dispose() {
        heroFrames.dispose();
    }

}
