package app.classes.view.gamingTable;

import javafx.animation.AnimationTimer;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;

/**
 * Created by killsett on 08.05.17.
 */

public class CustomButton extends ProgressIndicator {
    public CustomButton() {

        setProgress(0);
        final AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long time) {
                if (this.lastUpdate > 100) {
                    System.out.println(lastUpdate);
                    setProgress(getProgress() + 0.008);
                    System.out.println(getProgress());
                }
                this.lastUpdate = time;
            }
        };

        addEventFilter(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED)
                timer.start();
            if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                timer.stop();
                setProgress(0);
            }

        });


    }
}
