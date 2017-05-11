package app.classes.other;

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import app.classes.view.gamingTable.GamingCard;
import app.classes.view.gamingTable.GamingTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

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
}
