package dad.calculadoracompleja;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.Collections;

public class CalculadoraCompleja extends Application {
    private HBox hBoxFullScene;
    private HBox hBoxCenterFirst;
    private HBox hBoxCenterSecond;
    private HBox hBoxCenterThirt;
    private VBox vBoxLeft;
    private VBox vBoxCenter;
    private ComboBox comboBoxOperacion;
    private TextField textFieldA;
    private TextField textFieldB;
    private TextField textFieldC;
    private TextField textFieldD;
    private TextField textFieldE;
    private TextField textFieldF;
    private Separator separator;
    private ArrayList<String> listOperando;
    private NumeroComplejo numeroComplejoFirst;
    private NumeroComplejo numeroComplejoSecond;
    private NumeroComplejo numeroComplejoResultado;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Declaraciones
        hBoxFullScene = new HBox(10);
        hBoxCenterFirst = new HBox(10);
        hBoxCenterSecond = new HBox(10);
        hBoxCenterThirt = new HBox(10);
        vBoxLeft = new VBox(10);
        vBoxCenter = new VBox(10);
        comboBoxOperacion = new ComboBox();
        textFieldA = new TextField("0");
        textFieldB = new TextField("0");
        textFieldC = new TextField("0");
        textFieldD = new TextField("0");
        textFieldE = new TextField("0");
        textFieldF = new TextField("0");
        separator = new Separator();
        listOperando = new ArrayList<>();
        numeroComplejoFirst = new NumeroComplejo();
        numeroComplejoSecond = new NumeroComplejo();
        numeroComplejoResultado = new NumeroComplejo();
        textFieldE.setDisable(true);
        textFieldF.setDisable(true);

        //Alineación
        hBoxFullScene.setAlignment(Pos.CENTER);
        vBoxLeft.setAlignment(Pos.CENTER);
        vBoxCenter.setAlignment(Pos.CENTER);
        textFieldA.setAlignment(Pos.CENTER);
        textFieldB.setAlignment(Pos.CENTER);
        textFieldC.setAlignment(Pos.CENTER);
        textFieldD.setAlignment(Pos.CENTER);
        textFieldE.setAlignment(Pos.CENTER);
        textFieldF.setAlignment(Pos.CENTER);

        //Tamaño
        comboBoxOperacion.setMinWidth(60);
        textFieldA.setMaxWidth(60);
        textFieldB.setMaxWidth(60);
        textFieldC.setMaxWidth(60);
        textFieldD.setMaxWidth(60);
        textFieldE.setMaxWidth(60);
        textFieldF.setMaxWidth(60);


        //Construcción comboBox
        Collections.addAll(listOperando, "+", "-", "*", "/");
        comboBoxOperacion.getItems().addAll(listOperando);
        comboBoxOperacion.getSelectionModel().select(0);

        //Construcción del contenido
        vBoxLeft.getChildren().addAll(comboBoxOperacion);
        hBoxCenterFirst.getChildren().addAll(textFieldA, new Label("+"), textFieldB, new Label("i"));
        hBoxCenterSecond.getChildren().addAll(textFieldC, new Label("+"), textFieldD, new Label("i"));
        hBoxCenterThirt.getChildren().addAll(textFieldE, new Label("+"), textFieldF, new Label("i"));
        vBoxCenter.getChildren().addAll(hBoxCenterFirst, hBoxCenterSecond, separator, hBoxCenterThirt);
        hBoxFullScene.getChildren().addAll(vBoxLeft, vBoxCenter);

        //Evento
        comboBoxOperacion.getSelectionModel().selectedIndexProperty().addListener((v, ov, nv) -> tipoOperacion());

        //Bindings
        try {
            textFieldA.textProperty().bindBidirectional(numeroComplejoFirst.parteRealProperty(), new NumberStringConverter());
            textFieldB.textProperty().bindBidirectional(numeroComplejoFirst.parteImaginariaProperty(), new NumberStringConverter());
            textFieldC.textProperty().bindBidirectional(numeroComplejoSecond.parteRealProperty(), new NumberStringConverter());
            textFieldD.textProperty().bindBidirectional(numeroComplejoSecond.parteImaginariaProperty(), new NumberStringConverter());
            textFieldE.textProperty().bindBidirectional(numeroComplejoResultado.parteRealProperty(), new NumberStringConverter());
            textFieldF.textProperty().bindBidirectional(numeroComplejoResultado.parteImaginariaProperty(), new NumberStringConverter());

        } catch (RuntimeException e) {
            System.out.println(e);
        }
        Scene scene = new Scene(hBoxFullScene, 320, 240);
        stage.setTitle("CalculadoraCompleja");
        stage.setScene(scene);
        stage.show();
    }

    private void tipoOperacion() {
        switch (this.comboBoxOperacion.getSelectionModel().getSelectedIndex()) {
            case 0:
                try {
                    this.numeroComplejoResultado.parteRealProperty().bind(this.numeroComplejoFirst.parteRealProperty().add(this.numeroComplejoSecond.parteRealProperty()));
                    this.numeroComplejoResultado.parteImaginariaProperty().bind(this.numeroComplejoFirst.parteImaginariaProperty().add(this.numeroComplejoSecond.parteImaginariaProperty()));
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
                break;
            case 1:
                this.numeroComplejoResultado.parteRealProperty().bind(this.numeroComplejoFirst.parteRealProperty().subtract(this.numeroComplejoSecond.parteRealProperty()));
                this.numeroComplejoResultado.parteImaginariaProperty().bind(this.numeroComplejoFirst.parteImaginariaProperty().subtract(this.numeroComplejoSecond.parteImaginariaProperty()));
                break;

            case 2:
                this.numeroComplejoResultado.parteRealProperty().bind((this.numeroComplejoFirst.parteRealProperty()
                        .multiply(this.numeroComplejoSecond.parteRealProperty()))
                        .subtract(this.numeroComplejoFirst.parteImaginariaProperty().multiply(this.numeroComplejoSecond.parteImaginariaProperty())));
                this.numeroComplejoResultado.parteImaginariaProperty().bind((numeroComplejoFirst.parteRealProperty()
                        .multiply(numeroComplejoSecond.parteImaginariaProperty()))
                        .add(numeroComplejoFirst.parteImaginariaProperty().multiply(numeroComplejoSecond.parteRealProperty())));
                break;

            case 3:
                this.numeroComplejoResultado.parteRealProperty().bind((numeroComplejoFirst.parteRealProperty()
                        .multiply(numeroComplejoSecond.parteRealProperty()))
                        .add(numeroComplejoFirst.parteImaginariaProperty()
                                .multiply(numeroComplejoSecond.parteImaginariaProperty()))
                        .divide((numeroComplejoSecond.parteRealProperty()
                                .multiply(numeroComplejoSecond.parteRealProperty()))
                                .add(numeroComplejoSecond.parteImaginariaProperty()
                                        .multiply(numeroComplejoSecond.parteImaginariaProperty()))));

                this.numeroComplejoResultado.parteImaginariaProperty().bind((numeroComplejoFirst.parteImaginariaProperty()
                        .multiply(numeroComplejoSecond.parteRealProperty()))
                        .subtract(numeroComplejoFirst.parteRealProperty()
                                .multiply(numeroComplejoSecond.parteImaginariaProperty()))
                        .divide((numeroComplejoSecond.parteRealProperty()
                                .multiply(numeroComplejoSecond.parteRealProperty()))
                                .add(numeroComplejoSecond.parteImaginariaProperty()
                                        .multiply(numeroComplejoSecond.parteImaginariaProperty()))));
                break;
        }
    }

    public HBox gethBoxFullScene() {
        return hBoxFullScene;
    }

    public void sethBoxFullScene(HBox hBoxFullScene) {
        this.hBoxFullScene = hBoxFullScene;
    }

    public HBox gethBoxCenterFirst() {
        return hBoxCenterFirst;
    }

    public void sethBoxCenterFirst(HBox hBoxCenterFirst) {
        this.hBoxCenterFirst = hBoxCenterFirst;
    }

    public HBox gethBoxCenterSecond() {
        return hBoxCenterSecond;
    }

    public void sethBoxCenterSecond(HBox hBoxCenterSecond) {
        this.hBoxCenterSecond = hBoxCenterSecond;
    }

    public HBox gethBoxCenterThirt() {
        return hBoxCenterThirt;
    }

    public void sethBoxCenterThirt(HBox hBoxCenterThirt) {
        this.hBoxCenterThirt = hBoxCenterThirt;
    }

    public VBox getvBoxLeft() {
        return vBoxLeft;
    }

    public void setvBoxLeft(VBox vBoxLeft) {
        this.vBoxLeft = vBoxLeft;
    }

    public VBox getvBoxCenter() {
        return vBoxCenter;
    }

    public void setvBoxCenter(VBox vBoxCenter) {
        this.vBoxCenter = vBoxCenter;
    }

    public ComboBox getComboBoxOperacion() {
        return comboBoxOperacion;
    }

    public void setComboBoxOperacion(ComboBox comboBoxOperacion) {
        this.comboBoxOperacion = comboBoxOperacion;
    }

    public TextField getTextFieldA() {
        return textFieldA;
    }

    public void setTextFieldA(TextField textFieldA) {
        this.textFieldA = textFieldA;
    }

    public TextField getTextFieldB() {
        return textFieldB;
    }

    public void setTextFieldB(TextField textFieldB) {
        this.textFieldB = textFieldB;
    }

    public TextField getTextFieldC() {
        return textFieldC;
    }

    public void setTextFieldC(TextField textFieldC) {
        this.textFieldC = textFieldC;
    }

    public TextField getTextFieldD() {
        return textFieldD;
    }

    public void setTextFieldD(TextField textFieldD) {
        this.textFieldD = textFieldD;
    }

    public TextField getTextFieldE() {
        return textFieldE;
    }

    public void setTextFieldE(TextField textFieldE) {
        this.textFieldE = textFieldE;
    }

    public TextField getTextFieldF() {
        return textFieldF;
    }

    public void setTextFieldF(TextField textFieldF) {
        this.textFieldF = textFieldF;
    }

    public Separator getSeparator() {
        return separator;
    }

    public void setSeparator(Separator separator) {
        this.separator = separator;
    }

    public ArrayList<String> getListOperando() {
        return listOperando;
    }

    public void setListOperando(ArrayList<String> listOperando) {
        this.listOperando = listOperando;
    }

    public NumeroComplejo getNumeroComplejoFirst() {
        return numeroComplejoFirst;
    }

    public void setNumeroComplejoFirst(NumeroComplejo numeroComplejoFirst) {
        this.numeroComplejoFirst = numeroComplejoFirst;
    }

    public NumeroComplejo getNumeroComplejoSecond() {
        return numeroComplejoSecond;
    }

    public void setNumeroComplejoSecond(NumeroComplejo numeroComplejoSecond) {
        this.numeroComplejoSecond = numeroComplejoSecond;
    }

    public NumeroComplejo getNumeroComplejoResultado() {
        return numeroComplejoResultado;
    }

    public void setNumeroComplejoResultado(NumeroComplejo numeroComplejoResultado) {
        this.numeroComplejoResultado = numeroComplejoResultado;
    }
}