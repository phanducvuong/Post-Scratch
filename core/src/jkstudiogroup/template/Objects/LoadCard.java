package jkstudiogroup.template.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;
import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;
import jkstudiogroup.template.PostScratch;
import jkstudiogroup.template.scenes.BaseScene;

public class LoadCard {

    private static TextureRegion[][] textureRegion;
    public static List<Tile> listCard;

    public static void InitAssets() {
        textureRegion = new TextureRegion(PostScratch.assetManager.get("Card.png", Texture.class)).split(140, 190);
        listCard = new ArrayList<Tile>();

        for (int i = 0; i < textureRegion.length; i++)
            switch (i) {
                case 0:
                    for (int j = 0; j < textureRegion[i].length; j++) {
                        Tile tile = new Tile(null, textureRegion[i][j], 0, 0, GetCard(j), Kind.Spade);
                        listCard.add(tile);
                    }
                    break;
                case 1:
                    for (int j = 0; j < textureRegion[i].length; j++) {
                        Tile tile = new Tile(null, textureRegion[i][j], 0, 0, GetCard(j), Kind.Club);
                        listCard.add(tile);
                    }
                    break;
                case 2:
                    for (int j = 0; j < textureRegion[i].length; j++) {
                        Tile tile = new Tile(null, textureRegion[i][j], 0, 0, GetCard(j), Kind.Diamond);
                        listCard.add(tile);
                    }
                    break;
                case 3:
                    for (int j = 0; j < textureRegion[i].length; j++) {
                        Tile tile = new Tile(null, textureRegion[i][j], 0, 0, GetCard(j), Kind.Heart);
                        listCard.add(tile);
                    }
                    break;
            }
    }

    //todo: lấy ra giá trị của thẻ bài
    private static Card GetCard(int i) {
        i += 1;
        Card card = null;
        switch (i) {
            case 1: card = Card.Ace; break;
            case 2: card = Card.Two; break;
            case 3: card = Card.Three; break;
            case 4: card = Card.Four; break;
            case 5: card = Card.Five; break;
            case 6: card = Card.Six; break;
            case 7: card = Card.Seven; break;
            case 8: card = Card.Eight; break;
            case 9: card = Card.Nine; break;
            case 10: card = Card.Ten; break;
            case 11: card = Card.Jack; break;
            case 12: card = Card.Queen; break;
            case 13: card = Card.King; break;
        }
        return card;
    }

    public static TextureRegion GetTextureRegion(Card card, Kind kind) {
        for (Tile tile : listCard)
            if (tile.getCard() == card && tile.getKind() == kind)
                return tile.getTexture();
        return listCard.get(51).getTexture();
    }
}
