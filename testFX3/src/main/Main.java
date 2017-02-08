package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.dataBase.DataBase;
import main.objects.*;
import main.view.Join.ControllerJoin;
import main.view.listFakult.ControllerListFakult;
import main.view.listKaf.ControllerListKaf;
import main.view.listSpec.ControllerSpec;
import main.view.listStudents.ControllerListStudents;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private Stage mainPrimaryStage;
    private GatewayDataBase gatewayDataBase = new GatewayDataBase();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        this.mainPrimaryStage = primaryStage;
        this.mainPrimaryStage.setTitle("БГПУ");
        this.mainPrimaryStage.getIcons().add(
                new Image("file:src/main/resourceses/images/Address_Book.png"));
        showJoinWindows();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //факультеты

    public ObservableList<Fakultet> getListFakults() {
        ObservableList<Fakultet> personData = FXCollections
                .observableArrayList();
        Fakultet[] fakultets = gatewayDataBase.getListFakults();
        for (Fakultet fakultet : fakultets) {
            personData.add(fakultet);
        }
        return personData;
    }

    public Fakultet getFakultetData(int id) {
        return gatewayDataBase.getFakultet(id);
    }

    public void createNewFakultet(String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        gatewayDataBase.addFakultet(name, addresses, phoneNumber, mail, link, mainMan);
    }

    public void updateFakultet(String id, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        gatewayDataBase.updateFakultet(Integer.parseInt(id), name, addresses, phoneNumber, mail, link, mainMan);
    }

    public String getFakultetNameFromIdSpecialnost(int idSpecialnost) {
        String name = gatewayDataBase.getNameFakultet(idSpecialnost);
        return name;

    }

    //Специальности
    public ObservableList<Specialnost> getListSpecialnost() {
        ObservableList<Specialnost> personData = FXCollections
                .observableArrayList();
        Specialnost[] specialnosts = gatewayDataBase.getListSpecialnost();
        for (Specialnost specialnost : specialnosts) {
            personData.add(specialnost);
        }
        return personData;
    }

    public ObservableList<Specialnost> getListSpecialnost(int idKafedr) {
        ObservableList<Specialnost> personData = FXCollections
                .observableArrayList();
        Specialnost[] specialnosts = gatewayDataBase.getListSpecialnostFromKafedrs(idKafedr);
        if (specialnosts == null) return null;
        for (Specialnost specialnost : specialnosts) {
            personData.add(specialnost);
        }
        return personData;
    }

    public String getSpecialnostName(int idSpecialnost) {
        String name = gatewayDataBase.getNameSpecialnost(idSpecialnost);
        return name;
    }

    public int findSpecial(String nameSpecial) {
        return gatewayDataBase.getIdSpecianostByName(nameSpecial);
    }

    public Specialnost getSpecialnostData(int id) {
        return gatewayDataBase.getSpecialnost(id);
    }

    public void createNewSpecialnost(int selectedItem, String text, String text1) {
        gatewayDataBase.addSpecialnost(selectedItem, text, text1);
    }

    public void updateSpecialnost(int i, int selectedItem, String text, String text1) {
        gatewayDataBase.updateSpecialnost(i, selectedItem, text, text1);
    }

    public ObservableList<Specialnost> getListSpecialnostFromFakultet(int id) {
        ObservableList<Specialnost> personData = FXCollections
                .observableArrayList();
        Specialnost[] specialnosts = gatewayDataBase.getListSpecialnostFromFakultet(id);
        if (specialnosts == null) return null;
        for (Specialnost specialnost : specialnosts) {
            personData.add(specialnost);
        }
        return personData;
    }

    //Кафедры
    public ObservableList<Kafedra> getListKafedra() {
        ObservableList<Kafedra> personData = FXCollections
                .observableArrayList();
        Kafedra[] kafedras = gatewayDataBase.getListKafedrs();
        for (Kafedra kafedra : kafedras) {
            personData.add(kafedra);
        }
        return personData;
    }

    public ObservableList<Kafedra> getListKafedra(int idFakult) {
        ObservableList<Kafedra> personData = FXCollections
                .observableArrayList();
        Kafedra[] kafedras = gatewayDataBase.getListKafedrsFromFakult(idFakult);
        if (kafedras == null) return null;
        for (Kafedra kafedra : kafedras) {
            personData.add(kafedra);
        }
        return personData;
    }

    public Kafedra getKafedraData(int id) {
        return gatewayDataBase.getKafedra(id);
    }


    public void createNewKafedra(int iSpec, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        gatewayDataBase.addKafedra(iSpec, name, addresses, phoneNumber, mail, link, mainMan);
    }

    public void updateKafedra(int id, int iSpec, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        gatewayDataBase.updateKafedra(id, iSpec, name, addresses, phoneNumber, mail, link, mainMan);
    }

    public String getKafedraNameFromIdSpecialnost(int idSpecialnost) {
        String name = gatewayDataBase.getNameKafedra(idSpecialnost);
        return name;
    }

    //Обучения

    public ObservableList<Learning> getLearningsData(int id) {
        ObservableList<Learning> learningsData = FXCollections
                .observableArrayList();
        Learning[] learnings = gatewayDataBase.getListLearning(id);
        for (Learning learning : learnings) {
            learningsData.add(learning);
        }
        return learningsData;
    }

    public void updateLearning(int id, String kurs, String formReal) {
        gatewayDataBase.updateLearning(id, kurs, formReal);
    }

    public void deleteLearning(int id) {
        gatewayDataBase.deleteLearning(id);
    }

    public void createNewLearning(int nameSpecial, int idStudent, String kurs, String form) {
        gatewayDataBase.addLearning(nameSpecial, idStudent, kurs, form);
    }

    //студенты

    public ObservableList getStudentsData() {
        ObservableList<Student> personData = FXCollections
                .observableArrayList();
        Student[] students = gatewayDataBase.getListStudents();
        for (Student student : students) {
            personData.add(student);
        }
        return personData;
    }

    public Student getStudentData(int id) {
        Student student = gatewayDataBase.getStudent(id);
        return student;
    }


    public void deleteStudent(String text) {
        gatewayDataBase.deleteStudent(Integer.parseInt(text));
    }


    public void createNewStudent(String name, String fName, String sName, String addresses, String phoneNumber, String sex) {
        boolean sexB;
        if (sex.equals("Мужской"))
            sexB = true;
        else
            sexB = false;
        gatewayDataBase.addStudent(name, fName, sName, addresses, phoneNumber, sexB);


    }

    public void updateStudent(String id, String name, String fName, String sName, String addresses, String phoneNumber, String sex) {
        boolean sexB;
        if (sex.equals("Мужской"))
            sexB = true;
        else
            sexB = false;
        gatewayDataBase.updateStudent(id, name, fName, sName, addresses, phoneNumber, sexB);
    }


    public ObservableList<Student> getFilterFakultsForStudents(int id) {
        ObservableList<Student> personData = FXCollections
                .observableArrayList();
        Student[] students = gatewayDataBase.getStudentFilterFakultet(id);
        if (students == null) return null;
        for (Student student : students) {
            personData.add(student);
        }
        return personData;
    }

    public ObservableList<Student> getFilterKafedrsForStudents(int id) {
        ObservableList<Student> personData = FXCollections
                .observableArrayList();
        Student[] students = gatewayDataBase.getStudentFilterKafedra(id);
        if (students == null) return null;
        for (Student student : students) {
            personData.add(student);
        }
        return personData;
    }

    public ObservableList<Student> getFilterSpecialnostForStudents(int id) {
        ObservableList<Student> personData = FXCollections
                .observableArrayList();
        Student[] students = gatewayDataBase.getStudentFilterSpecialnost(id);
        if (students == null) return null;
        for (Student student : students) {
            personData.add(student);
        }
        return personData;
    }

    public ObservableList<Student> getFilterKursForStudents(String kurs, int spec) {
        ObservableList<Student> personData = FXCollections
                .observableArrayList();
        Student[] students = gatewayDataBase.getStudentFilterKursAndSpec(Integer.valueOf(kurs), spec);
        if (students == null) return null;
        for (Student student : students) {
            personData.add(student);
        }
        return personData;
    }

    //курсы

    public ObservableList<String> getListKurs(int idSpec) {
        ObservableList<String> personData = FXCollections
                .observableArrayList();
        String[] kurses = gatewayDataBase.getKursFilterSpecialnost(idSpec);
        if (kurses == null) return null;
        for (String kurs : kurses) {
            personData.add(kurs);
        }
        return personData;

    }

    //удаления

    public void deleteLearningAndStudentAndSpecialnostAndKafersAndFakults(String id) {
        gatewayDataBase.deleteAllPack(Integer.valueOf(id));
    }

    public void deleteLearningAndStudentAndSpecialnostAndKafedra(String text) {
        gatewayDataBase.deletePackNotFakultet(Integer.parseInt(text));
    }

    public void deleteLearningAndStudentAndSpecialnost(String text) {
        gatewayDataBase.deleteSpecialnost(Integer.valueOf(text));
    }


    //окна
    public void showJoinWindows() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Join/join.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();

            ControllerJoin join = loader.getController();
            join.setMainApp(this);

            showTemplate(anchorPane, "БГПУ : Авторизация");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainWindowsAdmin(Window window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/listStudents/listStudents.fxml"));

            BorderPane borderPane = (BorderPane) loader.load();
            ControllerListStudents main = loader.getController();
            main.setMainApp(this);

            showTemplate(borderPane, "БГПУ : Студенты", window);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainWindowsGuest(Window window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/listStudents/listStudents.fxml"));
            BorderPane borderPane = (BorderPane) loader.load();

            ControllerListStudents main = loader.getController();
            main.setMainApp(this);
            main.guest();

            showTemplate(borderPane, "БГПУ : Студенты", window);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showListFakult(Window window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/listFakult/listFakult.fxml"));
            BorderPane borderPane = (BorderPane) loader.load();


            ControllerListFakult main = loader.getController();
            main.setMainApp(this);

            showTemplate(borderPane, "БГПУ : Факультеты", window);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showListSpecial(Window window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/listSpec/listSpec.fxml"));
            BorderPane borderPane = (BorderPane) loader.load();

            ControllerSpec main = loader.getController();
            main.setMainApp(this);

            showTemplate(borderPane, "БГПУ : Специальности", window);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showListKafedr(Window window) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/listKaf/listKaf.fxml"));
            BorderPane borderPane = (BorderPane) loader.load();

            ControllerListKaf main = loader.getController();
            main.setMainApp(this);

            showTemplate(borderPane, "БГПУ : Кафедры", window);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String jarPath() {
        String myJarPath = DataBase.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String dirPath = new File(myJarPath).getParent();
        return dirPath;
    }

    //шаблоны

    private void showTemplate(BorderPane borderPane, String s, Window window) {
        if (window == null) {
            showTemplate(borderPane, s);
            return;
        }
        primaryStage = new Stage();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        primaryStage.setMaxHeight(550);
        primaryStage.setHeight(window.getHeight());
        primaryStage.setWidth(window.getWidth());
        primaryStage.setX(window.getX());
        primaryStage.setY(window.getY());

        primaryStage.initOwner(mainPrimaryStage);
        primaryStage.setTitle(s);

        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream("resourceses/images/Address_Book.png")));

        primaryStage.show();
    }

    public void showTemplate(BorderPane borderPane, String title) {
        primaryStage = new Stage();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(550);
        primaryStage.initOwner(mainPrimaryStage);
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream("resourceses/images/Address_Book.png")));
        primaryStage.show();
    }

    private void showTemplate(AnchorPane anchorPane, String title) {
        primaryStage = new Stage();
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initOwner(mainPrimaryStage);
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream("resourceses/images/Address_Book.png")));
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //авторизация
    public UserRight getRightUser(String login, String password) {
        return gatewayDataBase.getRightUser(login, password);
    }
}
