package assignment2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModeInputPanel extends GridPane {
    private final View view;

    /**
     * Constructs a new GridPane with the main menu of the game, to select a game mode and load saved boards
     * @param view
     */
    public ModeInputPanel(View view) {
        this.view = view;
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);

        view.setMessageLabel("Main Menu");

        createModeButtons();
        createListView();
    }

    /**
     * Creates the Game mode buttons
     */
    private void createModeButtons(){
        for (ThreeMusketeers.GameMode mode: ThreeMusketeers.GameMode.values()) {
            Button button = new Button(mode.getGameModeLabel());
            button.setId(mode.getGameModeLabel().replaceAll(" ", "")); // DO NOT MODIFY ID

            // Default styles which can be modified
            button.setPrefSize(500, 100);
            button.setFont(new Font(20));
            button.setStyle("-fx-background-color: #1de092; -fx-text-fill: white;");
            button.setOnAction(e -> this.view.setGameMode(mode));

            this.add(button, 0, this.getRowCount());
        }
    }

    /**
     * Creates the ListView to select a board to load
     */
    private void createListView(){
        Label selectBoardLabel = new Label(String.format("Current board: %s", view.model.getBoardFile().getName()));
        selectBoardLabel.setId("CurrentBoard"); // DO NOT MODIFY ID

        ListView<String> boardsList = new ListView<>();
        boardsList.setId("BoardsList");  // DO NOT MODIFY ID

        boardsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        int starterIndex = getFiles(boardsList);
        boardsList.getSelectionModel().select(starterIndex);

        Button selectBoardButton = new Button("Change board");
        selectBoardButton.setId("ChangeBoard"); // DO NOT MODIFY ID

        selectBoardButton.setOnAction(e -> selectBoard(selectBoardLabel, boardsList));

        VBox selectBoardBox = new VBox(10, selectBoardLabel, boardsList, selectBoardButton);

        // Default styles which can be modified

        boardsList.setPrefHeight(100);

        selectBoardLabel.setStyle("-fx-text-fill: #ea9931");
        selectBoardLabel.setFont(new Font(16));

        selectBoardButton.setStyle("-fx-background-color: #ea9931; -fx-text-fill: white;");
        selectBoardButton.setPrefSize(200, 50);
        selectBoardButton.setFont(new Font(16));

        selectBoardBox.setAlignment(Pos.CENTER);

        this.add(selectBoardBox, 0, this.getRowCount());
    }

    /**
     * Gets all the text files from the boards directory and puts them into the given listView
     * @param listView ListView to update
     * @return the index in the listView of Starter.txt
     */
    private int getFiles(ListView<String> listView) {// TODO //Implemented /tested1 /test2
    	
    	final File boardDirectory = new File("boards");
    	
    	int starterIndex = -1;
    	
    		for (final File boardFile: boardDirectory.listFiles()) {
    				
    				// add the board file path to listView
    				listView.getItems().add(boardFile.getName());
    				
    				//index of Starter.txt
    				if (boardFile.getName() == "Starter.txt") {
    					starterIndex = listView.getItems().indexOf(boardFile.getPath());
    				}				
    		}	
    	return starterIndex; //return the index of Starter.txt;
   }
    
    
    /**
     * Loads the board file selected in the boardsList and updates the selectBoardLabel with the name of the new Board file
     * @param selectBoardLabel a message Label to update which board is currently selected
     * @param boardsList a ListView populated with boards to load
     */
    private void selectBoard(Label selectBoardLabel, ListView<String> boardsList) { // TODO /implemented /tested1 /test2
    	
    	//selected board file path
    	String boardFileName = boardsList.getSelectionModel().getSelectedItem();
    	
    	final File boardDirectory = new File("boards");
    	
    	for (File file: boardDirectory.listFiles()) {
    		if (file.getName().endsWith(boardFileName)) {
    			
    	    	//loads the board file selected in the boardsList
    	    	view.model.setBoard(file);
    	    	//updates selectBoardLabel with the name of the new Board file
    	    	selectBoardLabel.setText(String.format("Current Board: %s", file.getName()));
    		}
    	}
    	
    }
    
   
   
    
    
    
    
}
