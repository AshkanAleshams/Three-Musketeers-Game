package assignment2;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.ListViewMatchers;

import java.io.File;

@ExtendWith(ApplicationExtension.class)
class ModeInputPanelTest {

    App app;
    ThreeMusketeers model;
    View view;

    @Start
    private void start(Stage stage) {
        app = new App();
        app.start(stage);
        model = app.model;
        view = app.view;
    }

    @Test
    void testModeLabels() {
        for (ThreeMusketeers.GameMode gameMode : ThreeMusketeers.GameMode.values()) {
            FxAssert.verifyThat(
                    String.format("#%s", gameMode.getGameModeLabel().replaceAll(" ", "")),
                    LabeledMatchers.hasText(gameMode.getGameModeLabel()));
        }
    }
}