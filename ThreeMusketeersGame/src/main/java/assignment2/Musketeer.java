package assignment2;

import javafx.scene.image.Image;

public class Musketeer extends Piece {

    public Musketeer() {
        super("X", Type.MUSKETEER, new Image("file:images/musketeer.png", 80, 80, true, true));
    }

    /**
     * Returns true if the Musketeer can move onto the given cell.
     * @param cell Cell to check if the Musketeer can move onto
     * @return True, if Musketeer can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) {
        return cell.hasPiece() && cell.getPiece().getType() == Type.GUARD;
    }
}
