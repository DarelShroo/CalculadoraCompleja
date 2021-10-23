package dad.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NumeroComplejo {
    private DoubleProperty parteReal = new SimpleDoubleProperty();
    private DoubleProperty parteImaginaria = new SimpleDoubleProperty();

    public NumeroComplejo() {
        super();
    }

    public NumeroComplejo(DoubleProperty parteReal, DoubleProperty parteImaginaria) {
        this.parteReal = parteReal;
        this.parteImaginaria = parteImaginaria;
    }

    public double getParteReal() {
        return parteReal.get();
    }

    public DoubleProperty parteRealProperty() {
        return parteReal;
    }

    public void setParteReal(double parteReal) {
        this.parteReal.set(parteReal);
    }

    public double getParteImaginaria() {
        return parteImaginaria.get();
    }

    public DoubleProperty parteImaginariaProperty() {
        return parteImaginaria;
    }

    public void setParteImaginaria(double parteImaginaria) {
        this.parteImaginaria.set(parteImaginaria);
    }
}
