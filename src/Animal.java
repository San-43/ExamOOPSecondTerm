import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Animal implements Serializable {
    private int ID;
    private String name;
    private String color;
    private String sex;
    private final LocalDate dateOfBirth;

    public Animal(int ID, String name, String color, String sex, LocalDate dateOfBirth) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }
    public Animal(int x) {
        int id;
        do {
            id = -1;
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                if (id <= 0) {
                    id = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> list = new ArrayList<>();
                try {
                    ObjectInputStream input = new ObjectInputStream(new FileInputStream("id.dat"));
                    list = (ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>) input.readObject();
                    input.close();
                    for (AbstractMap.SimpleEntry<Integer, Integer> i : list) {
                        if (i.getKey() == id) {
                            id = -1;
                            JOptionPane.showMessageDialog(null, "La id ya se encuentra registrada", null, JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                    if (id != -1) {
                        try {
                            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("id.dat"));
                            list.add(new AbstractMap.SimpleEntry<>(id, this instanceof Mammal ? 9: 10));
                            output.writeObject(list);
                            output.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                } catch (FileNotFoundException e) {
                    try {
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("id.dat"));
                        list.add(new AbstractMap.SimpleEntry<>(id, this instanceof Mammal ? 9: 10));
                        output.writeObject(list);
                        output.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        } while (id <= 0);

        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        String color = JOptionPane.showInputDialog("Ingrese el color");
        String sex = JOptionPane.showInputDialog("Ingrese el sexo");

        LocalDate birthDate;
        do {
            try {
                birthDate = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de producción con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (birthDate.getYear() < 2000 || birthDate.getYear() > LocalDate.now().getYear()) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango de año entre 1970 y el actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);

        this.name = name;
        this.ID = id;
        this.dateOfBirth = birthDate;
        this.color = color;
        this.sex = sex;
    }

    public Animal() {
        this.ID = 0;
        this.name = null;
        this.color = null;
        this.sex = null;
        this.dateOfBirth = null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Period getAge() {
        return Period.between(Objects.requireNonNull(dateOfBirth), LocalDate.now());
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Objects.requireNonNull(dateOfBirth).format(formatter);
    }

    public String getData() {
        return """
                Id. Animal: %s
                Nombre: %s
                Color: %s
                Edad: %s años y %s
                Sexo: %s
                Fecha de nacimiento: %s
                """.formatted(getID(), getName(), getColor(), getAge().getYears(), (getAge().getMonths() != 1) ? (getAge().getMonths() + " meses") : (getAge().getMonths() + " mes"), getSex(), getDateOfBirth());
    }
}
