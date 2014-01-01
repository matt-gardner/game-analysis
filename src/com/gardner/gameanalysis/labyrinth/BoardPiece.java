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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exits == null) ? 0 : exits.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardPiece other = (BoardPiece) obj;
		if (exits == null) {
			if (other.exits != null)
				return false;
		} else if (!exits.equals(other.exits))
			return false;
		if (item != other.item)
			return false;
		return true;
	}
}
