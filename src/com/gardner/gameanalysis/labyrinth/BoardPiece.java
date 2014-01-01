package com.gardner.gameanalysis.labyrinth;

import java.util.HashSet;
import java.util.Set;

/**
 * The only things that matter about a board piece are the sides that are open (defined by a set
 * of orientations called "exits") and the item that is found on the piece, if any.
 */
public class BoardPiece {
    private final Set<Orientation> exits;
    private final Item item;

    public BoardPiece(Item item, Orientation ... exits) {
        this.exits = new HashSet<Orientation>();
        for (Orientation exit : exits) {
            this.exits.add(exit);
        }
        this.item = item;
    }

    public Set<Orientation> getExits() {
        return exits;
    }

    public Item getItem() {
        return item;
    }
}
