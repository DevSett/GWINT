package app.view.optionGame.classes;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by kills on 01.03.2017.
 */
public class OptionController {
    MainApp mainApp;
    ScreenResolution screenResolution = new ScreenResolution();

    @FXML
    private Pane panePicture;

    @FXML
    private CheckBox checkFullScreen;

    @FXML
    private ChoiceBox<String> boxSize;

    @FXML
    void handleCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handlePlay(ActionEvent event) {
        mainApp.playGame(getDel(),checkFullScreen.isSelected());
        Stage stage = (Stage) boxSize.getScene().getWindow();
        stage.close();
    }

    private double getDel() {
        for (int index = 0; index < screenResolution.SIZEX.length; index++) {
            if (index != boxSize.getSelectionModel().getSelectedIndex())
                continue;
            return screenResolution.DEL[index];
        }
        return 1;
    }

    @FXML
    void initialize() {
        boxSize.getItems().addAll(
               screenResolution.SIZEX[0] + "x" + screenResolution.SIZEY[0],
                screenResolution.SIZEX[1] + "x" + screenResolution.SIZEY[1],
                screenResolution.SIZEX[2] + "x" + screenResolution.SIZEY[2],
                screenResolution.SIZEX[3] + "x" + screenResolution.SIZEY[3],
                screenResolution.SIZEX[4] + "x" + screenResolution.SIZEY[4],
                screenResolution.SIZEX[5] + "x" + screenResolution.SIZEY[5],
                screenResolution.SIZEX[6] + "x" + screenResolution.SIZEY[6],
                screenResolution.SIZEX[7] + "x" + screenResolution.SIZEY[7],
                screenResolution.SIZEX[8] + "x" + screenResolution.SIZEY[8]
        );
        boxSize.getSelectionModel().select(1);
        checkFullScreen.setSelected(true);

    }

    public void setMainApp(MainApp mainApp,Image image) {
        this.mainApp = mainApp;
        panePicture.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(panePicture.getPrefWidth(), panePicture.getPrefHeight(), true, true, true, true))));

    }
}
