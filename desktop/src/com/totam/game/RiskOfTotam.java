package com.totam.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.totam.game.screens.MainMenuScreen;

public class RiskOfTotam extends Game {

    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public Batch batch;
    public BitmapFont font;

    public final int SCREEN_WIDTH = 1317;
    public final int SCREEN_HEIGHT = 741;
    public final int TILE_SIZE = 128;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}