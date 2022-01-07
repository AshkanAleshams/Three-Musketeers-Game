package assignment2;

import java.util.List;

public class BoardEvaluatorImpl implements BoardEvaluator {

    /**
     * Calculates a score for the given Board
     * A higher score means the Musketeer is winning
     * A lower score means the Guard is winning
     * Add heuristics to generate a score given a Board
     * @param board Board to calculate the score of
     * @return double Score of the given Board
     */
    @Override
    public double evaluateBoard(Board board) {
        double score = 0;
        score += getRowColScore(board);
        score += getNumMusketeersPossibleMovesScore(board);
        score += getMusketeerDistanceScore(board);
        score += getGuardDistanceFromMusketeers(board);
        return score;
    }

    private double getRowColScore(Board board) {
        List<Cell> musketeerCells = board.getMusketeerCells();
        long numRows = musketeerCells.stream().map(cell -> cell.getCoordinate().row).distinct().count();
        long numCols = musketeerCells.stream().map(cell -> cell.getCoordinate().col).distinct().count();

        if (numRows == 1 || numCols == 1) return Integer.MIN_VALUE; // Game over. Guard wins.
        if (numRows == 2 || numCols == 2) return -15; // 2 Musketeers in same row or col.
        return 15; // All musketeers in different cols/rows.
    }

    private double getNumMusketeersPossibleMovesScore(Board board) {
        List<Cell> musketeerCells = board.getMusketeerCells();
        int numMusketeersCanMove = 0;
        for (Cell musketeerCell: musketeerCells) {
            if (board.getPossibleDestinations(musketeerCell).size() > 0) {
                numMusketeersCanMove += 1;
            }
        }
        return numMusketeersCanMove * -3;
    }

    private double getMusketeerDistanceScore(Board board) {
        List<Cell> musketeerCells = board.getMusketeerCells();
        Cell c1 = musketeerCells.get(0);
        Cell c2 = musketeerCells.get(1);
        Cell c3 = musketeerCells.get(2);

        int score = Math.abs(c1.getCoordinate().row - c2.getCoordinate().row)
                + Math.abs(c2.getCoordinate().row - c3.getCoordinate().row);
        score += Math.abs(c1.getCoordinate().col - c2.getCoordinate().col)
                + Math.abs(c2.getCoordinate().col - c3.getCoordinate().col);

        return score * 2;
    }

    private double getGuardDistanceFromMusketeers(Board board) {
        int score = 0;
        for (Cell musketeerCell: board.getMusketeerCells()) {
            int musketeerRow = musketeerCell.getCoordinate().row;
            int musketeerCol = musketeerCell.getCoordinate().col;
            for (Cell guardCell : board.getGuardCells()) {
                int guardRow = guardCell.getCoordinate().row;
                int guardCol = guardCell.getCoordinate().col;

                score += Math.abs(musketeerRow - guardRow);
                score += Math.abs(musketeerCol - guardCol);
            }
        }
        return score * 0.1;
    }
}
