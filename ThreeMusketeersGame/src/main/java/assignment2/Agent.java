package assignment2;

public abstract class Agent {

    protected Board board;

    /**
     * An Agent that can play ThreeMusketeers
     * @param board a Board the Agent can play on
     */
    public Agent(Board board){
        this.board = board;
    }

    /**
     * Gets a valid move that the Agent can perform on the Board
     * @return Move that the Agent can do
     */
    public abstract Move getMove();
}
