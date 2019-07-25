package jkstudiogroup.template.Objects;

import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;

public class Tile {

    private Card card;
    private Kind kind;

    public Tile(Card card, Kind kind) {
        this.card = card;
        this.kind = kind;
    }

    public Card getCard() {
        return card;
    }

    public Kind getKind() {
        return kind;
    }
}
