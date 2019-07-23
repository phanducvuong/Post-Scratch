package jkstudiogroup.template.commons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import jkstudiogroup.template.PostScratch;

public class _Image extends Image {

    TextureRegionDrawable texture;
    Stage stage;

    public _Image(Stage stage, float x, float y, String key, int align) {
        super(new TextureRegionDrawable(PostScratch.assetManager.get(key, Texture.class)));
        this.stage = stage;
        texture = new TextureRegionDrawable(PostScratch.assetManager.get(key, Texture.class));
        setDrawable(texture);

        setPosition(x, y);
        setOrigin(align);
        stage.addActor(this);
    }

    public void dispose() {
        stage.getActors().removeValue(this, true);
    }
}
