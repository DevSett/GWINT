package app.classes.view.optionGame.classes;

import app.classes.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static java.lang.System.exit;


public class OptionController {

    @FXML
    private Pane panePicture;

    @FXML
    private CheckBox checkFullScreen;

    @FXML
    private ChoiceBox<String> boxSize;

    @FXML
    void handleCancel() {
        exit(0);
    }

    @FXML
    void handlePlay() {
        MainApp.getSingleton().playGame(getDel(),checkFullScreen.isSelected());
        Stage stage = (Stage) boxSize.getScene().getWindow();
        stage.close();
    }

    private double getDel() {
        for (int index = 0; index < ScreenResolution.WIDTH.length; index++) {
            if (index != boxSize.getSelectionModel().getSelectedIndex())
                continue;
            return ScreenResolution.MULTIPLE[index];
        }
        return 1;
    }

    @FXML
    void initialize() {
        boxSize.getItems().addAll(
               ScreenResolution.WIDTH[0] + "x" + ScreenResolution.HEIGHT[0],
                ScreenResolution.WIDTH[1] + "x" + ScreenResolution.HEIGHT[1],
                ScreenResolution.WIDTH[2] + "x" + ScreenResolution.HEIGHT[2],
                ScreenResolution.WIDTH[3] + "x" + ScreenResolution.HEIGHT[3],
                ScreenResolution.WIDTH[4] + "x" + ScreenResolution.HEIGHT[4],
                ScreenResolution.WIDTH[5] + "x" + ScreenResolution.HEIGHT[5],
                ScreenResolution.WIDTH[6] + "x" + ScreenResolution.HEIGHT[6],
                ScreenResolution.WIDTH[7] + "x" + ScreenResolution.HEIGHT[7],
                ScreenResolution.WIDTH[8] + "x" + ScreenResolution.HEIGHT[8]
        );
        boxSize.getSelectionModel().select(3);
        checkFullScreen.setSelected(false);
    }

    public void setImage(Image image) {
        panePicture.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(panePicture.getPrefWidth(), panePicture.getPrefHeight(), true, true, true, false))));
        panePicture.setEffect(new DropShadow(10, Color.BLACK));
    }
}
