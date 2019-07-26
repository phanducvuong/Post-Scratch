package jkstudiogroup.template.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;

public class Tile extends Image {

    private Card card;
    private Kind kind;
    private TextureRegion textureRegion;
    private Stage stage;

    public Tile(Stage stage, TextureRegion texture, float x, float y, Card card, Kind kind) {
        super(texture);
        this.card = card;
        this.kind = kind;
        this.stage = stage;
        this.textureRegion = texture;
        textureRegion.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        setPosition(x, y, Align.bottomLeft);
        setOrigin(Align.bottomLeft);

        if (this.stage != null) this.stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX(),getY());
    }

    public Card getCard() {
        return card;
    }

    public Kind getKind() {
        return kind;
    }

    public TextureRegion getTexture() {
        return textureRegion;
    }
}
