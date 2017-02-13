package main.otherClass;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Pair;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by kills on 26.01.2017.
 */
public class DialogLib {
    public static class Information {
        public Information(String title, String header, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);

            alert.showAndWait();
        }
    }

    public static class OnlyHeaderInformation {
        public OnlyHeaderInformation(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);

            alert.showAndWait();
        }

    }

    public static class Warning {
        public Warning(String title, String header, String content) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);

            alert.showAndWait();
        }
    }

    public static class Error {
        public Error(String title, String header, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);

            alert.showAndWait();
        }
    }

    public static class Exception {
        public Exception(String title, String header, String content, java.lang.Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("Стэк во время ошибки:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }

    public static class Confirm {
        private Alert alert;

        public Confirm(String title, String header, String content) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
        }

        public boolean checkConfirm() {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static class ConfirmCustom {
        private ArrayList<ButtonType> listButton = new ArrayList<>();
        private Alert alert;

        public ConfirmCustom(String title, String header, String content, String[] namesButton, String nameButtonExit) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);

            for (String nameButton : namesButton) {
                listButton.add(new ButtonType(nameButton));
            }
            if (nameButtonExit != null)
                listButton.add(new ButtonType(nameButtonExit, ButtonBar.ButtonData.CANCEL_CLOSE));

            alert.getButtonTypes().setAll(listButton);
        }

        public int checkIndexConfirm() {
            int index = 0;
            Optional<ButtonType> result = alert.showAndWait();
            for (ButtonType button : listButton) {
                index++;
                if (result.get() == button)
                    return index;
            }
            return -1;
        }
    }

    public static class InputDialog {
        private TextInputDialog dialog;

        public InputDialog(String promptTextForTextField, String title, String header, String content) {
            dialog = new TextInputDialog(promptTextForTextField);
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(content);
        }

        public String returnLine() {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                return result.get();
            }
            return null;
        }
    }

    public static class Choise {
        private ChoiceDialog<String> dialog;

        public Choise(String title, String header, String content, List<String> choices) {
            dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(content);

        }

        public Object returnChoice() {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                return result.get();
            }
            return null;
        }
    }

    public static class CustomLogin {

        private javafx.scene.control.Dialog<Pair<String, String>> dialog = new javafx.scene.control.Dialog<>();

        public CustomLogin(String title, String header) {
            // Create the custom dialog.

            dialog.setTitle(title);
            dialog.setHeaderText(header);

            dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

            ButtonType loginButtonType = new ButtonType("Вход", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField username = new TextField();
            username.setPromptText("Логин");
            PasswordField password = new PasswordField();
            password.setPromptText("Пароль");

            grid.add(new Label("Логин:"), 0, 0);
            grid.add(username, 1, 0);
            grid.add(new Label("Пароль:"), 0, 1);
            grid.add(password, 1, 1);

            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            username.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

            Platform.runLater(() -> username.requestFocus());

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(username.getText(), password.getText());
                }
                return null;
            });
        }

        private HashMap map;

        public HashMap returnLoginAndPassword() {
            Optional<Pair<String, String>> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                map = new HashMap();
                map.put("Логин", usernamePassword.getKey());
                map.put("Password", usernamePassword.getValue());
            });
            return map;
        }
    }

}
