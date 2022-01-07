package assignment2;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardPanel extends GridPane implements EventHandler<ActionEvent> {

    private final View view;
    private final Board board;
    
    //instance variables created by Ashkan Aleshams to keep track of the selected cell and user picking status
    private Cell selectedCell = null;
    private Boolean isPickingSelectCell = true;
    

    /**
     * Constructs a new GridPane that contains a Cell for each position in the board
     *
     * Contains default alignment and styles which can be modified
     * @param view
     * @param board
     */
    public BoardPanel(View view, Board board) {
        this.view = view;
        this.board = board;

        // Can modify styling
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #181a1b;");
        int size = 550;
        this.setPrefSize(size, size);
        this.setMinSize(size, size);
        this.setMaxSize(size, size);

        setupBoard();
        updateCells();
    }


    /**
     * Setup the BoardPanel with Cells
     */
    private void setupBoard(){ // TODO //implemented /test1 
    	//set a 10px boarder around the cells
    	this.setPadding(new Insets(10, 10, 10, 10));
    	//set a horizontal and vertical gap of 10 between cells
    	this.setVgap(10);
    	this.setHgap(10);
    	
    	for (int row = 0; row <5; row ++) {
    		for (int col=0; col<5; col ++) {
    			//get the cell from the board
    			Cell boardCell = this.board.getCell(new Coordinate(row, col));
    			
    			//add it to the BoardPanel
    			
    			this.setConstraints(boardCell, col, row); //boardCell's row and col
  
    			this.getChildren().add(boardCell);
    			
    			//since Cell extends button, we can the set the onAction for it to be handeld when clicked
    			boardCell.setOnAction(this);
    			
    			
    		}
    	}
    	
    }

    /**
     * Updates the BoardPanel to represent the board with the latest information
     *
     * If it's a computer move: disable all cells and disable all game controls in view
     *
     * If it's a human player turn and they are picking a piece to move:
     *      - disable all cells
     *      - enable cells containing valid pieces that the player can move
     * If it's a human player turn and they have picked a piece to move:
     *      - disable all cells
     *      - enable cells containing other valid pieces the player can move
     *      - enable cells containing the possible destinations for the currently selected piece
     *
     * If the game is over:
     *      - update view.messageLabel with the winner ('MUSKETEER' or 'GUARD')
     *      - disable all cells
     */
    protected void updateCells(){ // TODO /implemented /test1 /test2
    	
    	//getting the board cells
    	ObservableList<Node> boardCells = this.getChildren();
    	
    	//if its computer's turn
    	if (view.model.isHumanTurn() == false) {
    		//disable all cells
    		disableAllCells(true);
    		//disable all game controls
    		view.disableBoardControls(true);
    		
    		System.out.println("im here in computer branch");
    		//run the move for the computer
    		if (this.board.isGameOver() == false) view.runMove();
    	}
    	
    	
    	//if its human's turn and they are selecting a cell to move
    	if (view.model.isHumanTurn() && isPickingSelectCell==true) {
    		//disable all cells
    		disableAllCells(true);
    		//enable cells containing valid pieces that the player can move
    		List<Cell> possibleCells = board.getPossibleCells();
    		//if the Board Cell is in the possibleCells, we enable it
    		for (var cell: boardCells) {
    			if (possibleCells.contains(cell)) cell.setDisable(false);
    		}
    	}
    	
    	//if its human's turn and they are picking a destination for their selected move
    	if (view.model.isHumanTurn() && isPickingSelectCell==false) {
    		//disable all cells
    		disableAllCells(true);
    		//enable cells containing valid pieces that the player can move
    		List<Cell> possibleCells = board.getPossibleCells();
    		for (var cell: boardCells) {
    			if (possibleCells.contains(cell)) cell.setDisable(false);
    		}
    		//enable cells containing the possible destinations for the currently selected 
    		List<Cell> destinatationCells = board.getPossibleDestinations(this.selectedCell);
    		for (var cell: destinatationCells) {
    			if (destinatationCells.contains(cell)) cell.setDisable(false);
    		}
    	}
    	
    	
    	//check if the game is over
    	if(board.isGameOver()) {
    		//update view.messageLabel with the winner ('MUSKETEER' or 'GUARD')
    		view.messageLabel.setText(String.format("winner: %s", board.getWinner().getType()));
    		//disable all cells
    		disableAllCells(true);
    		//enable controls
    		view.disableBoardControls(false);
    		
    	}
    }
    
    
    /** Created by Ashkan aleshams
     * A method that changes the isPickingSelectCell status
     * @param bool, if true, then isPickingSelectCell is set to true, false otherwise
     */
    protected void setIsPicking(Boolean bool) {
    	this.isPickingSelectCell = bool;
    	updateCells();
    }
    
    
    /**created by Ashkan Aleshams
     * a method to disable/enalbe all cells in BorderPanel
     * @param bool, if bool is true, it disables all cells, if false, it enables all cells
     */
    protected void disableAllCells(Boolean bool) {
    	for (var boardCell: this.getChildren()) {
    		boardCell.setDisable(bool);
    	}
    }

    /**
     * Handles Cell clicks and updates the board accordingly
     * When a Cell gets clicked the following must be handled:
     *  - If it's a valid piece that the player can move, select the piece and update the board
     *  - If it's a destination for a selected piece to move, perform the move and update the board
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) { // TODO //fully functional
    	//first we get the button that was clicked from actionEvent
    	Cell clickedCell =(Cell)actionEvent.getSource();
    	
    	//List of possible cells that can be moved
    	List<Cell> possibleCells = board.getPossibleCells();
    	
    	
    	//if the clickedCell is a cell selected to be moved
    	if (possibleCells.contains(clickedCell)){
    			
    		this.isPickingSelectCell = false;
    		//make the clickedCell the selected cell to move
    		this.selectedCell = clickedCell;
    		
    		updateCells();
    	}
    	
    	//if the clickedCell is a destination for a selected cell
    	if (isPickingSelectCell == false ) {
    		//List of possible destination cells for the current selecedCell
        	List<Cell> destinationCells = board.getPossibleDestinations(this.selectedCell);
        	if (destinationCells.contains(clickedCell)) {
        		//make the clikcedCell the destination cell for the move
        	
        		//then make the move on the board
        		this.view.model.move(new Move(this.selectedCell, clickedCell)); 
        		
        		//Change the messageLabel to show the turn MUSKETEER or GAURD
        		this.view.setMessageLabel(String.format("[%s turn] Select a piece", this.board.getTurn().getType()));
        		
        		//then set the selected cell and destination cell to null
        		this.selectedCell = null;
        		//set the picking status to true
        		this.isPickingSelectCell = true;
        		
        		//update the board
        		updateCells();	
        		
        		
        	}
    		
    	}
    }
    
    
  
}
