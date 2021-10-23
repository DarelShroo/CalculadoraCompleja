package dad.calculadoracompleja;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CalculadoraCompleja extends Application {
    private HBox hBoxFullScene;
    private HBox hBoxCenterFirst;
    private HBox hBoxCenterSecond;
    private HBox hBoxCenterThirt;
    private VBox vBoxLeft;
    private VBox vBoxCenter;
    private VBox vBoxRight;
    private ComboBox comboBoxOperacion;
    private TextField textFieldA;
    private TextField textFieldB;
    private TextField textFieldC;
    private TextField textFieldD;
    private TextField textFieldE;
    private TextField textFieldF;
    private Button buttonOperador;
    private Separator separator;
    private Label labelFirstRow;
    private Label labelSecondRow;
    private Label labelThirtRow;
    private ArrayList<String> listOperando;
    @Override
    public void start(Stage stage) throws IOException {
        //Declaraciones
        hBoxFullScene = new HBox();
        hBoxCenterFirst = new HBox();
        hBoxCenterSecond = new HBox();
        hBoxCenterThirt = new HBox();
        vBoxLeft = new VBox();
        vBoxCenter = new VBox();
        vBoxRight = new VBox();
        comboBoxOperacion = new ComboBox();
        textFieldA = new TextField();
        textFieldB = new TextField();
        textFieldC = new TextField();
        textFieldD = new TextField();
        textFieldE = new TextField();
        textFieldF = new TextField();
        labelFirstRow = new Label();
        labelSecondRow = new Label();
        labelThirtRow = new Label();
        buttonOperador = new Button("=");
        separator = new Separator();
        listOperando  = new ArrayList<>();

        //Alineación
        hBoxFullScene.setAlignment(Pos.CENTER);
        vBoxLeft.setAlignment(Pos.CENTER);
        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxRight.setAlignment(Pos.CENTER);

        //Tamaño
        buttonOperador.setMinWidth(30);
        comboBoxOperacion.setMinWidth(60);
        labelFirstRow.setMinWidth(20);
        labelSecondRow.setMinWidth(20);
        labelThirtRow.setMinWidth(20);

        //Padding
        hBoxFullScene.setPadding(new Insets(5,5,5,5));
        vBoxCenter.setPadding(new Insets(5,5,5,5));
        hBoxCenterFirst.setPadding(new Insets(5,5,5,5));
        hBoxCenterSecond.setPadding(new Insets(5,5,5,5));
        hBoxCenterThirt.setPadding(new Insets(5,5,5,5));
        labelFirstRow.setPadding(new Insets(5,5,5,5));
        labelSecondRow.setPadding(new Insets(5,5,5,5));
        labelThirtRow.setPadding(new Insets(5,5,5,5));


        //Construcción comboBox
        Collections.addAll(listOperando,"+","-","*","/");
        comboBoxOperacion.getItems().addAll(listOperando);

        //Construcción del contenido
        vBoxLeft.getChildren().addAll(comboBoxOperacion);
        hBoxCenterFirst.getChildren().addAll(textFieldA,labelFirstRow,textFieldB);
        hBoxCenterSecond.getChildren().addAll(textFieldC,labelSecondRow,textFieldD);
        hBoxCenterThirt.getChildren().addAll(textFieldE,labelThirtRow,textFieldF);
        vBoxCenter.getChildren().addAll(hBoxCenterFirst, hBoxCenterSecond, hBoxCenterThirt);
        vBoxRight.getChildren().addAll(buttonOperador);
        hBoxFullScene.getChildren().addAll(vBoxLeft,vBoxCenter,vBoxRight);

        comboBoxOperacion.setOnAction(e -> tipoOperacion(e));

        Scene scene = new Scene(hBoxFullScene, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void tipoOperacion(Event e) {
        String operacionSeleccionada =  comboBoxOperacion.getSelectionModel().getSelectedItem().toString();
        labelFirstRow.setText(operacionSeleccionada);
        labelSecondRow.setText(operacionSeleccionada);
        labelThirtRow.setText(operacionSeleccionada);
        switch(operacionSeleccionada){
            case "+":
                operacionSuma();
                break;
            case "-":
                operacionResta();
                break;
            case "*":
                operacionMultiplicacion();
                break;
            case "/":
                operacionDivision();
                break;
        }
    }

    private void operacionMultiplicacion() {
    }

    private void operacionResta() {
    }

    private void operacionSuma() {
        System.out.println(10);
    }

    private void operacionDivision() {
    }

    public static void main(String[] args) {
        launch();
    }
}