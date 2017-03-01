package app.view.optionGame;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by kills on 01.03.2017.
 */
public class OptionController {
    MainApp mainApp;
    int[] sizesX = {1920, 1600, 1366, 1280, 1024, 960, 864, 720, 640};
    int[] sizeY = {1080, 900, 768, 720, 576, 540, 486, 405, 360};
    double[] del = {
            sizesX[0] / sizesX[0],
            sizesX[0] / sizesX[1],
            sizesX[0] / sizesX[2],
            sizesX[0] / sizesX[3],
            sizesX[0] / sizesX[4],
            sizesX[0] / sizesX[5],
            sizesX[0] / sizesX[6],
            sizesX[0] / sizesX[7],
            sizesX[0] / sizesX[8],
    };

    @FXML
    private Pane panePicture;

    @FXML
    private ChoiceBox<String> boxSize;

    @FXML
    void handleCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handlePlay(ActionEvent event) {
        mainApp.playGame(getDel());
        Stage stage = (Stage) boxSize.getScene().getWindow();
        stage.close();
    }

    private double getDel() {
        for (int index = 0; index < sizesX.length; index++) {
            if (index != boxSize.getSelectionModel().getSelectedIndex())
                continue;
            return this.del[index];
        }
        return 1;
    }

    @FXML
    void initialize() {
        boxSize.getItems().addAll(
                sizesX[0] + "x" + sizeY[0],
                sizesX[1] + "x" + sizeY[1],
                sizesX[2] + "x" + sizeY[2],
                sizesX[3] + "x" + sizeY[3],
                sizesX[4] + "x" + sizeY[4],
                sizesX[5] + "x" + sizeY[5],
                sizesX[6] + "x" + sizeY[6],
                sizesX[7] + "x" + sizeY[7],
                sizesX[8] + "x" + sizeY[8]
        );

    }

    public void setMainApp(MainApp mainApp,Image image) {
        this.mainApp = mainApp;
        panePicture.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(panePicture.getPrefWidth(), panePicture.getPrefHeight(), true, true, true, true))));

    }
}
