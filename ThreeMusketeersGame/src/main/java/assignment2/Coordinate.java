package assignment2;

public class Coordinate {
    int row;
    int col;

    /**
     * A Coordinate contains a row and a column
     * @param row contains the row value of the Coordinate
     * @param col contains the column value of the Coordinate
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        char letter = Utils.convertIntToLetter(col + 1);
        return String.format("%c%d", letter, row + 1);
    }
}
