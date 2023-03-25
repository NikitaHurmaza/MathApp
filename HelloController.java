package com.example.mathapp;

import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rootFinder.EquationChecker;

import java.sql.SQLException;

public class HelloController {

    @FXML
    private TextField equationField;
    @FXML
    private Button checkButton;
    @FXML
    private EquationDatabaseManager dbManager;

    public HelloController() {
    }
    public void setDatabaseManager(EquationDatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @FXML
    void initialize() {
        EquationChecker equationChecker = new EquationChecker();
        checkButton.setOnAction(event -> {
            String equation = equationField.getText();
            try {
                setDatabaseManager(new EquationDatabaseManager());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            boolean isEquationValid = equationChecker.checkParentheses(equation) && equationChecker.checkEquation(equation);
            if (isEquationValid) {
                try {
                    int count = equationChecker.countNumbers(equation);
                    dbManager.saveEquation(equation);
                    Label successLabel = new Label("Рівняння успішно збережено.Кількість чисел: " + count);
                    successLabel.setTextFill(Color.BLACK);
                    successLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    VBox vbox = new VBox(successLabel);
                    vbox.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(vbox, 400, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("Помилка");
                b.setHeaderText("Некоректно введено рівняння");
                b.show();
            }
        });

    }

}
