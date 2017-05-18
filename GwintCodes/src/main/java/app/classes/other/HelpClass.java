package app.classes.other;

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import app.classes.view.gamingTable.Alert;
import app.classes.view.gamingTable.GamingCard;
import app.classes.view.gamingTable.GamingTable;
import app.classes.view.optionGame.classes.ScreenResolution;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelpClass {
    private HelpClass() {
        //do not use
    }

    public static int StringToInt(Object object) {
        return Integer.valueOf((String) object);
    }

    public static int LongToInt(Object object) {
        return Math.toIntExact((Long) object);
    }

    public static List<Integer> random(int size) {
        List<Integer> numbers = new ArrayList<>(size);
        int index = 0;
        while (index++ < size) {

            int in = new Random().nextInt(30);
            while (in < 2)
                in = new Random().nextInt(30);
            numbers.add(in);
        }
        return numbers;
    }

    public static Card copyCard(Card card) {
        return new Card(
                card.getId(),
                card.getDamage(),
                card.getDescription(),
                card.getRare(),
                card.getColor(),
                card.getType(),
                card.getForm()
        );
    }


    public static void alert(String alertT, EventHandler<ActionEvent> eventOnFinished, EventHandler<MouseEvent> eventOnClicked, Stage stage, Alerts alerts) {
        alert(alertT, eventOnFinished, eventOnClicked, (Pane) stage.getScene().getRoot(),alerts);
    }
    public static void alert(String alertT, EventHandler<ActionEvent> eventOnFinished, EventHandler<MouseEvent> eventOnClicked, Pane parent) {
        alert(alertT, eventOnFinished, eventOnClicked, parent,Alerts.INFO);

    }
        public static void alert(String alertT, EventHandler<ActionEvent> eventOnFinished, EventHandler<MouseEvent> eventOnClicked, Pane parent,Alerts alerts) {
        Alert alert = null;
        alert = new Alert(alertT, 0.7,alerts);
        Alert finalAlert = alert;
        Platform.runLater(() -> parent.getChildren().add(finalAlert));

        if (eventOnFinished != null) {
            Alert finalAlert1 = alert;
            alert.getTimeline().setOnFinished(event -> {
                finalAlert1.setVisible(false);
                eventOnFinished.handle(event);
            });
        }
        if (eventOnClicked != null) {
            Alert finalAlert2 = alert;
            alert.setOnMouseClicked(event -> {
                finalAlert2.getOnMouseClicked().handle(event);
                eventOnClicked.handle(event);
            });
        }
        alert.animationStart();
    }

    public static GamingCard copyGamingCard(GamingCard card) {
        return new GamingCard(
                card.getCard(),
                card.getSizeX() * MainApp.getSingleton().getDel(),
                card.getSizeY() * MainApp.getSingleton().getDel(),
                card.isAnimation(),
                GamingTable.countCard++
        );
    }

    public static GamingCard copyGamingCard(GamingCard card, boolean animation) {
        return new GamingCard(
                card.getCard(),
                card.getSizeX() * MainApp.getSingleton().getDel(),
                card.getSizeY() * MainApp.getSingleton().getDel(),
                animation,
                GamingTable.countCard++
        );
    }

    public static Font getFont(double size) {
        return Font.loadFont(
                HelpClass.class.getResource("/fonts/Intro.otf").toExternalForm(),
                size
        );
    }

    public static TextField customField(String promt, String id, double width, double height) {
        TextField textField = new TextField();
        //200 40
        textField.setPrefSize(width / MainApp.getSingleton().getDel(), height / MainApp.getSingleton().getDel());
        textField.setPromptText(promt);
        textField.setId(id);
        return textField;
    }

    public static Button customButton(Button button, double width, double height, String id, EventHandler<ActionEvent> event) {
        if (button == null) button = new Button();

        button.setPrefSize(width / MainApp.getSingleton().getDel(), height / MainApp.getSingleton().getDel());
        button.setOnAction(event);
        button.setId(id);
        return button;
    }

    public static Button customButton(double width, double height, String id, EventHandler<ActionEvent> event, String text) {
        Button button = new Button(text);
        return customButton(button, width, height, id, event);
    }

    public static GamingCard buildGamingCard(int id) {
        GamingCard gamingCard = new GamingCard(
                HelpClass.copyCard(MainApp.getSingleton().getLogic().getCard(id)),
                ScreenResolution.SIZE.CARD.IN_HEND.WIDTH,
                ScreenResolution.SIZE.CARD.IN_HEND.HEIGHT,
                true,
                GamingTable.countCard++
        );
        return gamingCard;
    }
}
