package assignment2;

import javafx.scene.image.Image;

public class Guard extends Piece {

    public Guard() {
        super("O", Type.GUARD, new Image("file:images/guard.png", 80, 80, true, true));
    }

    /**
     * Returns true if the Guard can move onto the given cell.
     * @param cell Cell to check if the Guard can move onto
     * @return True, if Guard can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) {
        return !cell.hasPiece();
    }
}
