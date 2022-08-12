package com.totam.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		int screenWidth = 800;
		int screenHeight = 400;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(screenWidth,screenHeight);
		config.useVsync(true);
		config.setTitle("RiskOfTotam");
		new Lwjgl3Application(new RiskOfTotam(), config);
	}
}
