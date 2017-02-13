package main.view.Join;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.UserRight;
import main.otherClass.DialogLib;

/**
 * Created by kills on 17.01.2017.
 */
public class ControllerJoin {


    private Main mainApp;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    //Кнопка "Вход"
    @FXML
    private void handlerJOIN() {
        UserRight user = mainApp.getRightUser(loginField.getText(), passwordField.getText());
        if (!user.isAccess()) {
            new DialogLib.Error(
                    "Ошибка доступа!",
                    "Неправильно введен логин или пароль!",
                    "Для входа используйте аунтефикационные данные!");
            return;
        }

        if (user.getRight().equals("ADMIN"))
            mainApp.showMainWindowsAdmin(null);
        if (user.getRight().equals("Guest"))
            mainApp.showMainWindowsGuest(null);
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.close();


    }

    //сеттер
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
