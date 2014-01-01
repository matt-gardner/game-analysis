package com.gardner.gameanalysis.labyrinth;

/**
 * A Card in Labyrinth doesn't really have anything other than an Item on it.  But for logical
 * consistency, and for ease of extendibility if I want to, say, add an image of the card, I'm
 * making this a separate class.
 */
public class Card {
    private final Item item;

    public Card(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
