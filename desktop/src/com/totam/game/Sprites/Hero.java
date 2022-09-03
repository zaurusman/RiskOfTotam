package com.totam.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.totam.game.RiskOfTotam;

public class Hero extends Sprite {

    public World world;
    public Body b2body;

    /*final RiskOfTotam game;
    final int heroWidth = 100;
    final int heroHeight = 74;
    final private Texture heroFrames;
    final private Animation<TextureRegion> idleHero;
    final private Animation<TextureRegion> attackHero;
    final private Animation<TextureRegion> dashAttackHero;
    final private Animation<TextureRegion> runHero;
    final private Animation<TextureRegion> jumpHero;
    public int xPosition;
    public int yPosition;
    final private int movementSpeed = 300;
    boolean faceLeft;
    boolean isairborne;

     */

    public Hero(World world) {
        this.world = world;
        defineHero();
        /*
        faceLeft = false;
        isairborne = false;
        //set the Hero animation
        heroFrames = new Texture(Gdx.files.internal("hero.png"));
        TextureRegion[][] tempFrames = TextureRegion.split(heroFrames, heroWidth, heroHeight);

        TextureRegion[] idleFrames = new TextureRegion[4];
        System.arraycopy(tempFrames[0], 0, idleFrames, 0, 4);

        TextureRegion[] attackFrames = new TextureRegion[4];
        attackFrames[0] = tempFrames[6][6];
        attackFrames[1] = tempFrames[7][0];
        attackFrames[2] = tempFrames[7][1];
        attackFrames[3] = tempFrames[7][2];

        TextureRegion[] dashAttackFrames = new TextureRegion[6];
        dashAttackFrames[0] = tempFrames[13][3];
        dashAttackFrames[1] = tempFrames[13][4];
        dashAttackFrames[2] = tempFrames[13][5];
        dashAttackFrames[3] = tempFrames[13][6];
        dashAttackFrames[4] = tempFrames[14][0];
        dashAttackFrames[5] = tempFrames[14][1];

        TextureRegion[] jumpFrames = new TextureRegion[10];
        jumpFrames[0] = tempFrames[2][0];
        jumpFrames[1] = tempFrames[2][1];
        jumpFrames[2] = tempFrames[2][2];
        jumpFrames[3] = tempFrames[2][3];
        jumpFrames[4] = tempFrames[2][4];
        jumpFrames[5] = tempFrames[2][5];
        jumpFrames[6] = tempFrames[2][6];
        jumpFrames[7] = tempFrames[3][0];
        jumpFrames[8] = tempFrames[3][1];
        jumpFrames[9] = tempFrames[3][2];



        TextureRegion[] runFrames = new TextureRegion[6];
        System.arraycopy(tempFrames[1], 1, runFrames, 0, 6);


        idleHero = new Animation<>(1f / 4f, idleFrames);
        attackHero = new Animation<>(1f / 8f, attackFrames);
        dashAttackHero = new Animation<>(1f / 8f, dashAttackFrames);
        runHero = new Animation<>(1f / 4f, runFrames);
        jumpHero = new Animation<>(1f/8f,jumpFrames);*/
    }

    public void defineHero() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RiskOfTotam.PPM ,32 / RiskOfTotam.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / RiskOfTotam.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
    /*
    public void idle(final float elapsedTime) {
        if(faceLeft) {
            idleHero.getKeyFrame(elapsedTime,true).flip(true,false);
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
            idleHero.getKeyFrame(elapsedTime,true).flip(true,false);
        }
        else {
            game.batch.draw(idleHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void moveRight(final float elapsedTime) {
        xPosition += movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition > game.SCREEN_WIDTH - heroWidth) {
            xPosition = game.SCREEN_WIDTH - heroWidth;
            idle(elapsedTime);
        } else {
            game.batch.draw(runHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
        faceLeft = false;
    }

    public void moveLeft(final float elapsedTime) {
        xPosition -= movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition < 0) {
            xPosition = 0;
           idle(elapsedTime);
        } else {
            runHero.getKeyFrame(elapsedTime,true).flip(true,false);
            game.batch.draw(runHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
            runHero.getKeyFrame(elapsedTime,true).flip(true,false);
        }
        faceLeft = true;

    }

    public void jump(final float elapsedTime) {
        isairborne= true;
        for (int i = 0; i < 10; ++i) {
            if(faceLeft) {
                jumpHero.getKeyFrame(elapsedTime, true).flip(true, false);
                game.batch.draw(jumpHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
                jumpHero.getKeyFrame(elapsedTime, true).flip(true, false);
            }
            game.batch.draw(jumpHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void attack(final float elapsedTime) {
        if(faceLeft) {
            attackHero.getKeyFrame(elapsedTime, true).flip(true, false);
            game.batch.draw(attackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
            attackHero.getKeyFrame(elapsedTime, true).flip(true, false);
        }
        else {
            game.batch.draw(attackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void dashAttackRight(final float elapsedTime) {
        xPosition += movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition > game.SCREEN_WIDTH - heroWidth) {
            xPosition = game.SCREEN_WIDTH - heroWidth;
            idle(elapsedTime);
        } else {
            game.batch.draw(dashAttackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
        }
    }

    public void dashAttackLeft(final float elapsedTime) {
        xPosition -= movementSpeed * Gdx.graphics.getDeltaTime();
        if (xPosition < 0) {
            xPosition = 0;
            idle(elapsedTime);
        } else {
            dashAttackHero.getKeyFrame(elapsedTime,true).flip(true,false);
            game.batch.draw(dashAttackHero.getKeyFrame(elapsedTime, true), xPosition, yPosition);
            dashAttackHero.getKeyFrame(elapsedTime,true).flip(true,false);
        }
    }

    public void dispose() {
        heroFrames.dispose();
    }
    */
}
