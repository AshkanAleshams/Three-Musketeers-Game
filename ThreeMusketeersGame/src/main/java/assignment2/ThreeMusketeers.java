package assignment2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreeMusketeers {
    private File boardFile;
    private Board board;
    private Agent musketeerAgent;
    private Agent guardAgent;
    private List<Move> moves;

    /**
     * Default constructor to load Starter board
     */
    public ThreeMusketeers() {
        this(new File("Boards/Starter.txt"));
    }

    /**
     * Constructor to load custom board
     *
     * @param boardFile filepath of custom board
     */
    public ThreeMusketeers(File boardFile) {
        this.board = new Board(boardFile);
        this.boardFile = boardFile;
        this.moves = new ArrayList<>();
    }

    /**
     * Restart the game
     */
    public void restart() {
        board = new Board(this.boardFile);
        moves = new ArrayList<>();
    }

    /**
     * Mode selector sets the correct agents based on the given GameMode
     *
     * @param mode the selected GameMode
     */
    protected void selectMode(GameMode mode, Piece.Type sideType) {
        switch (mode) {
            case Human -> {
                musketeerAgent = new HumanAgent(board);
                guardAgent = new HumanAgent(board);
            }
            case HumanRandom -> {
                musketeerAgent = sideType.equals(Piece.Type.MUSKETEER) ? new HumanAgent(board) : new RandomAgent(board);
                guardAgent = sideType.equals(Piece.Type.GUARD) ? new HumanAgent(board) : new RandomAgent(board);
            }
            case HumanGreedy -> {
                musketeerAgent = sideType.equals(Piece.Type.MUSKETEER) ? new HumanAgent(board) : new GreedyAgent(board);
                guardAgent = sideType.equals(Piece.Type.GUARD) ? new HumanAgent(board) : new GreedyAgent(board);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getMovesSize() {
        return moves.size();
    }

    public File getBoardFile() {
        return boardFile;
    }

    /**
     * Get the current agent based on whose turn it is.
     *
     * @return Agent whose turn it is
     */
    public Agent getCurrentAgent() {
        if (board.getTurn() == Piece.Type.MUSKETEER)
            return musketeerAgent;
        return guardAgent;
    }

    public Agent getMusketeerAgent() {
        return musketeerAgent;
    }

    public Agent getGuardAgent() {
        return guardAgent;
    }

    /**
     * Gets a move from the given agent, adds a copy of the move using the copy constructor to the moves stack, and
     * does the move on the board.
     *
     * @param agent Agent to get the move from
     * @return Move that the agent is doing
     */
    protected Move move(final Agent agent) {
        final Move move = agent.getMove();
        this.move(move);
        return move;
    }

    /**
     * Do the given move on the board and add a copy to the moves list.
     */
    protected void move(Move move) {
        moves.add(new Move(move));
        board.move(move);
    }

    /**
     * Removes a move from the top of the moves stack and undoes the move on the board.
     */
    public void undoMove() {
        if (moves.size() == 0) {
            System.out.println("No moves to undo.");
            return;
        }
        if (moves.size() == 1 || isHumansPlaying() || gameOverUndoCondition()) {
            board.undoMove(moves.remove(moves.size() - 1));
        } else {
            board.undoMove(moves.remove(moves.size() - 1));
            board.undoMove(moves.remove(moves.size() - 1));
        }
        System.out.println("Undid the previous move.");
    }

    private boolean gameOverUndoCondition() {
        Piece.Type fromPieceType = moves.get(moves.size() - 1).fromCell.getPiece().getType();
        return board.isGameOver()
                && ((fromPieceType.equals(Piece.Type.MUSKETEER) && musketeerAgent instanceof HumanAgent)
                || (fromPieceType.equals(Piece.Type.GUARD) && guardAgent instanceof HumanAgent));
    }

    /**
     * Returns whether both sides are human players
     *
     * @return True if both sides are Human, False if one of the sides is a computer
     */
    public boolean isHumansPlaying() {
        return musketeerAgent instanceof HumanAgent && guardAgent instanceof HumanAgent;
    }

    public boolean isHumanTurn() {
        Piece.Type currentTurnType = board.getTurn();
        return (currentTurnType.equals(Piece.Type.MUSKETEER) && musketeerAgent instanceof HumanAgent)
                || (currentTurnType.equals(Piece.Type.GUARD) && guardAgent instanceof HumanAgent);
    }

    /**
     * Possible game modes
     */
    public enum GameMode {
        Human("Human vs Human"),
        HumanRandom("Human vs Computer (Random)"),
        HumanGreedy("Human vs Computer (Greedy)");

        public String getGameModeLabel() {
            return gameModeLabel;
        }

        private final String gameModeLabel;

        GameMode(final String gameModeLabel) {
            this.gameModeLabel = gameModeLabel;
        }
    }

    protected void setBoard(File boardFile) {
        this.board.loadBoard(boardFile);
        this.boardFile = boardFile;
    }
}
