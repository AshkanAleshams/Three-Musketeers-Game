package assignment2;

import javafx.scene.image.Image;

import java.util.Objects;

public abstract class Piece {

    private final String symbol;
    private final Type type;
    private final Image image;

    /**
     * Construct a new Piece
     *
     * @param symbol to represent the Piece
     * @param type   a Type of Piece (Musketeer or Guard)
     */
    public Piece(String symbol, Type type, Image image) {
        this.symbol = symbol;
        this.type = type;
        this.image = image;
    }

    /**
     * All possible Piece types
     */
    public enum Type {
        MUSKETEER("MUSKETEER"),
        GUARD("GUARD");

        private final String type;

        Type(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * @return symbol representation of Piece
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return Image representation of Piece
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the Type of piece (Musketeer or Guard)
     */
    public Type getType() {
        return type;
    }

    /**
     * @param cell a Cell to check conditions of
     * @return whether this Piece can move onto a given cell
     */
    public abstract boolean canMoveOnto(Cell cell);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return Objects.equals(getSymbol(), piece.getSymbol()) && getType() == piece.getType() && Objects.equals(getImage(), piece.getImage());
    }
}
