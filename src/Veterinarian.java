import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Veterinarian implements Serializable {
    private int ID;
    private String name;
    private String specialization;
    private String phone;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfHire;

    public Veterinarian(int ID, String name, String specialization, String phone, LocalDate dateOfBirth, LocalDate dateOfHire) {
        this.ID = ID;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
    }

    public Veterinarian(int x) {
        int id;
        do {
            id = -1;
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                if (id <= 0) {
                    id = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
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
                            list.add(new AbstractMap.SimpleEntry<>(id, 11));
                            output.writeObject(list);
                            output.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (FileNotFoundException e) {
                    try {
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("id.dat"));
                        list.add(new AbstractMap.SimpleEntry<>(id, 11));
                        output.writeObject(list);
                        output.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        } while (id <= 0);

        this.ID = id;
        this.name = JOptionPane.showInputDialog("Nombre");
        this.specialization = JOptionPane.showInputDialog("Especialización");
        this.phone = JOptionPane.showInputDialog("Teléfono");

        LocalDate dateOfBirth;
        do {
            try {
                dateOfBirth = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de nacimiento con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (dateOfBirth.getYear() < 1970 || dateOfBirth.getYear() > LocalDate.now().getYear()) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango de año entre 1970 y el actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);
        this.dateOfBirth = dateOfBirth;

        LocalDate dateOfHire;
        do {
            try {
                dateOfHire = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de contratación con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (Period.between(dateOfBirth, dateOfHire).getYears() < 30 || Period.between(dateOfBirth, dateOfHire).getYears() > 80) {
                    JOptionPane.showMessageDialog(null, "La fecha de contratación debe 30 años posterior a la de nacimiento y menor a 80", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);
        this.dateOfHire = dateOfHire;
    }
    public Veterinarian() {
        this.ID = 0;
        this.name = null;
        this.specialization = null;
        this.phone = null;
        this.dateOfBirth = null;
        this.dateOfHire = null;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public String getData() {
        return """
                Id. Veterinario: %s
                Nombre: %s
                Especialidad: %s
                Teléfono: %s
                Fecha de nacimiento: %s
                Fecha de Contratación: %s
                """.formatted(getID(), getName(), getSpecialization(), getPhone(), getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yy")), getDateOfHire().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }
}
