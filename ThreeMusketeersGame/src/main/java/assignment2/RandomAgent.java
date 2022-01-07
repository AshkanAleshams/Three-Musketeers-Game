package assignment2;

import java.util.List;
import java.util.Random;

public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() {
        List<Cell> possibleCells = board.getPossibleCells();
        Cell fromCell = possibleCells.get(new Random().nextInt(possibleCells.size()));

        List<Cell> possibleDestinations = board.getPossibleDestinations(fromCell);
        Cell toCell = possibleDestinations.get(new Random().nextInt(possibleDestinations.size()));

        System.out.printf("[%s (Random Agent)] Moving piece %s to %s.\n",
                board.getTurn().getType(), fromCell.getCoordinate(), toCell.getCoordinate());
        return new Move(fromCell, toCell);
    }
}
