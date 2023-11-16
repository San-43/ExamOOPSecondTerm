import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Bird extends Animal implements Serializable {
    private String typeOfBeak;
    private boolean itFlights;
    private String size;

    public Bird(int ID, String name, String color, String sex, LocalDate birthDate, String typeOfBeak, boolean itFlights, String size) {
        super(ID, name, color, sex, birthDate);
        this.typeOfBeak = typeOfBeak;
        this.itFlights = itFlights;
        this.size = size;
    }
    public Bird(int x) {
        super(1);
        this.typeOfBeak = JOptionPane.showInputDialog("Tipo de pico");
        this.size = JOptionPane.showInputDialog("Tamaño");

        do {
            String itFlights = JOptionPane.showInputDialog("¿Puede Volar?").toLowerCase();
            if (itFlights.equals("si")) {
                this.itFlights = true;
                break;
            } else if (itFlights.equals("no")) {
                this.itFlights = false;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Escriba si o no por favor", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);

    }
    public Bird() {
        super();
        this.typeOfBeak = null;
        this.itFlights = false;
        this.size = null;
    }

    public String getTypeOfBeak() {
        return typeOfBeak;
    }

    public void setTypeOfBeak(String typeOfBeak) {
        this.typeOfBeak = typeOfBeak;
    }

    public boolean isItFlights() {
        return itFlights;
    }

    public void setItFlights(boolean itFlights) {
        this.itFlights = itFlights;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getData() {
        return super.getData() + """
                Tipo de pico: %s
                Vuela: %s
                Categoría: %s
                """.formatted(getTypeOfBeak(), isItFlights() ? "si" : "no", getSize());
    }
}
