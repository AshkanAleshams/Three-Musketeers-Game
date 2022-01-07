package assignment2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SideInputPanel extends GridPane implements EventHandler<ActionEvent> {
    private final View view;

    /**
     * Constructs a new GridPane with 2 buttons to select what side the human player wants to play as
     * @param view
     */
    public SideInputPanel(View view) {
        this.view = view;
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);

        view.setMessageLabel("Select your side");

        for (Piece.Type pieceType: Piece.Type.values()) {
            String label = pieceType.getType();

            Button button = new Button(label);
            button.setId(label); // DO NOT MODIFY ID

            // Default styles which can be modified
            button.setPrefSize(500, 100);
            button.setFont(new Font(20));
            button.setStyle("-fx-background-color: #1de092; -fx-text-fill: white;");

            button.setOnAction(this);

            this.add(button, 0, this.getRowCount());
        }
    }

    /**
     * Handles click of human player side options ('MUSKETEER' or 'GUARD')
     * Calls view.setSide with the appropriate Piece.Type selected
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) { // TODO //implemented /test1
    	//first we get the button that was clicked from actionEvent
    	Button clickedButton=(Button)actionEvent.getSource();
    	//we get the text of clickedButton which tells us the selected side
    	String side = clickedButton.getText();
    	//we set the side with the appropriate Piece.Type selected
    	if (side == "MUSKETEER") view.setSide(Piece.Type.MUSKETEER);
    	else view.setSide(Piece.Type.GUARD);
    }
}
