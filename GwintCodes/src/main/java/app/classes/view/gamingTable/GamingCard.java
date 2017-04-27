package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Created by kills on 14.02.2017.
 */
public class GamingCard extends Region {
    private double width;
    private double height;
    private Card card;
//    private Timeline animation;


    public GamingCard(Card card, double width, double height) {
        super();

        this.width = width;
        this.height = height;
        this.card = card;

        draw();
    }

    private void draw() {
        Image image = new Image(getClass().getResource("/images/gamingTable/cards/" + card.getId() + ".png").toExternalForm());

        setBackground(new Background
                (new BackgroundImage(
                        image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(width, height, true, true, true, false))));

        setPrefSize(
                width / MainApp.getSingleton().getDel(),
                height / MainApp.getSingleton().getDel()
        );
    }

    public double getSizeX() {
        return width;
    }

    public double getSizeY() {
        return height;
    }

    public Card getCard() {
        return card;
    }

    @Override
    protected void setWidth(double value) {
        this.width = value;
        super.setWidth(value);
    }

    @Override
    protected void setHeight(double value) {
        this.height = value;
        super.setHeight(value);
    }
}
