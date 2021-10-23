package dad.calculadoracompleja;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
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
    private String operador;

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
        textFieldA = new TextField("0");
        textFieldB = new TextField("0");
        textFieldC = new TextField("0");
        textFieldD = new TextField("0");
        textFieldE = new TextField("0");
        textFieldF = new TextField("0");
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

        //Evento
        buttonOperador.setOnAction(e -> eventOperador(e));
        comboBoxOperacion.setOnAction(e -> tipoOperacion(e));

        Scene scene = new Scene(hBoxFullScene, 320, 240);
        stage.setTitle("CalculadoraCompleja");
        stage.setScene(scene);
        stage.show();
    }

    private void eventOperador(ActionEvent e) {
        if(operador!=null) {
            switch (operador) {
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
    }

    private void tipoOperacion(Event e) {
        String operacionSeleccionada =  comboBoxOperacion.getSelectionModel().getSelectedItem().toString();
        labelFirstRow.setText(operacionSeleccionada);
        labelSecondRow.setText(operacionSeleccionada);
        labelThirtRow.setText(operacionSeleccionada);

        switch(operacionSeleccionada){
            case "+":
                operador = "+";
                break;
            case "-":
                operador = "-";
                break;
            case "*":
                operador = "*";
                break;
            case "/":
                operador = "/";
                break;
        }
    }
    private void operacionSuma() {
        try{
            double textFieldA = Double.parseDouble(this.textFieldA.getText());
            double textFieldB = Double.parseDouble(this.textFieldB.getText());
            double textFieldC = Double.parseDouble(this.textFieldC.getText());
            double textFieldD = Double.parseDouble(this.textFieldD.getText());

            double parteReal = textFieldA+textFieldC;
            double parteImaginaria = textFieldB+textFieldD;

            this.textFieldE.setText(String.valueOf(parteReal));
            this.textFieldF.setText(String.valueOf(parteImaginaria));

        }catch(NumberFormatException e){
            System.out.println("No son números válidos");
        }
    }

    private void operacionResta() {
        try {
            double textFieldA = Double.parseDouble(this.textFieldA.getText());
            double textFieldB = Double.parseDouble(this.textFieldB.getText());
            double textFieldC = Double.parseDouble(this.textFieldC.getText());
            double textFieldD = Double.parseDouble(this.textFieldD.getText());

            double parteReal = textFieldA - textFieldC;
            double parteImaginaria = textFieldB - textFieldD;

            this.textFieldE.setText(String.valueOf(parteReal));
            this.textFieldF.setText(String.valueOf(parteImaginaria));
        }catch(NumberFormatException e){
            System.out.println("No son números válidos");
        }
    }

    private void operacionMultiplicacion() {
        try{
            double textFieldA = Double.parseDouble(this.textFieldA.getText());
            double textFieldB = Double.parseDouble(this.textFieldB.getText());
            double textFieldC = Double.parseDouble(this.textFieldC.getText());
            double textFieldD = Double.parseDouble(this.textFieldD.getText());

            double parteReal = (textFieldA*textFieldC - textFieldB*textFieldD);
            double parteImaginaria = (textFieldA*textFieldD + textFieldB*textFieldC);

            this.textFieldE.setText(String.valueOf(parteReal));
            this.textFieldF.setText(String.valueOf(parteImaginaria));
        }catch(NumberFormatException e){
            System.out.println("No son números válidos");
        }
    }

    private void operacionDivision() {
        try{

        }catch(NumberFormatException e){
            System.out.println("No son números válidos");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}