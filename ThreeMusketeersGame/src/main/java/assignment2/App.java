package assignment2;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    ThreeMusketeers model;
    View view;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Three Musketeers");
        stage.setMinHeight(900);
        stage.setMinWidth(600);
        stage.getIcons().add(new Image("file:images/musketeer.png"));

        this.model = new ThreeMusketeers();
        this.view = new View(model, stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
