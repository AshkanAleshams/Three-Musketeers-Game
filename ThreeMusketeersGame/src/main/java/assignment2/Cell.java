package assignment2;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Cell extends Button {

    private final Coordinate coordinate;
    private Piece piece;
    private static final int size = 100;

    /**
     * Creates a cell with the given coordinate.
     * Piece is null if there is no piece on the cell.
     *
     * @param coordinate Coordinate of the cell on the board.
     */
    public Cell(Coordinate coordinate) {
        super("");
        this.coordinate = coordinate;
        this.setSize();
        this.setDefaultColor();
        this.setShape(new Circle(5));
        this.setId(this.coordinate.toString());  // DO NOT MODIFY ID
    }

    /**
     * Create a copy of a Cell
     * @param cell a Cell to make a copy of
     */
    public Cell(Cell cell) {
        super("");
        this.coordinate = cell.coordinate;
        this.piece = cell.piece;
        this.setSize();
    }

    protected Coordinate getCoordinate() {
        return coordinate;
    }

    protected boolean hasPiece() {
        return piece != null;
    }

    protected Piece getPiece() {
        return piece;
    }

    protected void setPiece(Piece piece) {
        this.piece = piece;
        this.setGraphic(hasPiece() ? new ImageView(piece.getImage()) : null);
    }

    protected void removePiece() {
        this.piece = null;
        this.setGraphic(null);
    }

    @Override
    public String toString() {
        return hasPiece() ? piece.getSymbol() : " ";
    }

    private void setSize() {
        this.setMinSize(size, size);
        this.setPrefSize(size, size);
    }

    protected void setWinColor() {
        this.setStyle("-fx-background-color: #61FF00B7;");
    }

    protected void setLossColor() {
        this.setStyle("-fx-background-color: #FF0000B7;");
    }

    // top, right, bottom, left
    protected void setOptionsColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #17871b;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    protected void setAgentFromColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #6c0404;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    protected void setAgentToColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #ff0700;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    protected void setDefaultColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #00307b;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }
}
