package jkstudiogroup.template.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import jkstudiogroup.template.PostScratch;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 970;
		config.height = 640;
		new LwjglApplication(new PostScratch(), config);
	}
}
