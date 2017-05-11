package app.classes.view.gamingTable;

import app.classes.MainApp;
import app.classes.rulesGaming.Card;
import app.classes.rulesGaming.CardType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;


/**
 * Created by kills on 14.02.2017.
 */
public class GamingCard extends Region {
    Long idn;
    private double width;
    private double height;
    private Card card;
    private Timeline timeline;
    private boolean onCard = false;
    private boolean animation;
    private Label label;

    public GamingCard(Card card, double width, double height, boolean animation, Long id) {
        super();

        this.width = width / MainApp.getSingleton().getDel();
        this.height = height / MainApp.getSingleton().getDel();
        this.card = card;
        this.animation = animation;
        this.idn = id;

        draw();
        if (animation)
            animation();
    }

    public Long identificator() {
        return idn;
    }

    private void draw() {

        Image image = new Image(getClass().getResource("/images/gamingTable/cards/" + card.getId() + ".png").toExternalForm());

        setBackground(new Background
                (new BackgroundImage(
                        image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(width, height, true, true, true, false))));

        setPrefSize(
                width,
                height
        );

        Font font = null;

        if (!card.getType().equals(CardType.LIDER))
            font = Font.loadFont(
                    getClass().getResource("/fonts/Intro.otf").toExternalForm(),
                    18 / MainApp.getSingleton().getDel()
            );
        else font = Font.loadFont(getClass().getResource("/fonts/Intro.otf").toExternalForm(),
                35 / MainApp.getSingleton().getDel());


        String damage = card.getDamage().toString();

        if (!damage.equals("0")) {
            if (damage.toCharArray().length == 1) damage = " " + damage;
        } else damage = "";

        label = new Label(damage);
        label.setStyle("-fx-text-fill: whitesmoke");
        label.setLayoutX(12 / MainApp.getSingleton().getDel());
        label.setFont(font);

        getChildren().addAll(label);

    }

    public void setSizeLabel(double size) {
        Font font = Font.loadFont(getClass().getResource("/fonts/Intro.otf").toExternalForm(), size);
        label.setFont(font);
    }

    public void setLabelLayoutX(double value) {
        label.setLayoutX(value);
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

    private AnimationDescription timelineCard;


    private void animation() {

        // create parallel transition to do all 4 transitions at the same time
        timeline = new Timeline();
        timeline.getKeyFrames().addAll
                (new KeyFrame(Duration.ZERO,
                                new KeyValue(this.translateYProperty(), 0)),
                        new KeyFrame(new Duration(70),
                                new KeyValue(this.translateYProperty(), -10)));
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

        setOnMouseEntered(event -> {
            timeline.play();
            onCard = true;
        });

        timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toSeconds() == 0.07 && onCard) {
                timeline.pause();
            }
        });

        setOnMouseExited(event -> {
            onCard = false;
            if (timeline.getCurrentTime() != Duration.ZERO)
                timeline.play();
        });


        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (timelineCard != null)
                    timelineCard.setVisible(true);
                else {
                    timelineCard = new AnimationDescription(this, 0.7);
                    Parent parent = getParent();
                    while (!parent.getClass().equals(GamingTable.class)) {
                        parent = parent.getParent();
                    }
                    AnchorPane pane = ((AnchorPane) parent);
                    pane.getChildren().addAll(timelineCard);
                }

                timelineCard.animationStart();
            }

        });
    }

    public boolean isAnimation() {
        return animation;
    }

}
