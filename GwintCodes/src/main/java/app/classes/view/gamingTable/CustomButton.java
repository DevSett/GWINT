package app.classes.view.gamingTable;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;


/**
 * Created by killsett on 08.05.17.
 */

public class CustomButton extends ProgressIndicator {
    private boolean check;

    public CustomButton(ChangeListener<Number> numberChangeListener) {

        setProgress(0);
        final AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long time) {
                if (this.lastUpdate > 100) {
                    setProgress(getProgress() + 0.008);
                }
                this.lastUpdate = time;
            }
        };

        if (numberChangeListener != null) progressProperty().addListener(numberChangeListener);
        addEventFilter(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                timer.start();
            }
            if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                timer.stop();
                setProgress(0);
                check = false;
            }
        });

    }
}
