package assignment2;

public class Move {
    final Cell fromCell;
    final Cell toCell;

    /**
     * Construct a new Move
     * A Move represents moving a Piece in fromCell to toCell
     * @param fromCell the Cell the Piece is in
     * @param toCell the Cell the Piece will move to
     */
    public Move(final Cell fromCell, final Cell toCell) {
        this.fromCell = fromCell;
        this.toCell = toCell;
    }

    /**
     * Create a copy of a Move
     * @param move a Move to make a copy of
     */
    public Move(Move move) {
        this.fromCell = new Cell(move.fromCell);
        this.toCell = new Cell(move.toCell);
    }

    @Override
    public String toString() {
        return String.format("(%s) -> (%s)", fromCell.getCoordinate(), toCell.getCoordinate());
    }
}
