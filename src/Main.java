import javax.swing.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int opt;
        do {
            do {
                opt = 0;
                try {
                    opt = Integer.parseInt(JOptionPane.showInputDialog("""
                                    ------ MENÚ CONTROL VETERINARIO ------
                                    1) Alta de un mamífero
                                    2) Alta de una ave
                                    3) Alta de un veterinario
                                    4) Alta de una revisión
                                    5) Listar mamíferos
                                    6) Listar aves
                                    7) Listar veterinarios
                                    8) Listar revisiones
                                    9) Ver detalle de un mamífero
                                    10) Ver detalle de una ave
                                    11) Ver detalle de un veterinario
                                    12) Ver detalle de una revisión
                                    13) Salir
                            Elija una opción ->"""));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "La opción debe ser numérica", "", JOptionPane.WARNING_MESSAGE);
                }
            }while (opt < 1);
            switch (opt) {
                case 1 -> {
                    ArrayList<Mammal> list = new ArrayList<>();
                    Mammal m = new Mammal(1);

                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("mammals.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();
                    } catch (FileNotFoundException ignored) {
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }
                    mammalsWriter(list, m);
                }
                case 2 -> {
                    ArrayList<Bird> list = new ArrayList<>();
                    Bird b = new Bird(1);
                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("birds.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();
                    } catch (FileNotFoundException ignored) {
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }

                    birdsWriter(list, b);
                }
                case 3 -> {
                    ArrayList<Veterinarian> list = new ArrayList<>();
                    Veterinarian b = new Veterinarian(1);

                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("veterinarians.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();
                    } catch (FileNotFoundException ignored) {
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }

                    veterinariansWriter(list, b);
                }
                case 4 -> {
                }
                case 5 -> {
                    ArrayList<Mammal> list = new ArrayList<>();
                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("mammals.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();
                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Fecha de Nacimiento           Pelaje     Categoría
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Mammal i :
                                list) {
                            s.append(i.getID()).append("                   ").append(i.getName()).append("                ").append(i.getDateOfBirth()).append("                  ").append(i.getHairType()).append("            ").append(i.getCategory()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, s, "LISTA DE MAMÍFEROS", JOptionPane.INFORMATION_MESSAGE);

                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encuentra el archivo", "", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 6 -> {
                    ArrayList<Bird> list = new ArrayList<>();
                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("birds.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();
                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Sexo        Vuela             Tamaño
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Bird i :
                                list) {
                            s.append(i.getID()).append("                   ").append(i.getName()).append("                ").append(i.getSex()).append("                  ").append(i.isItFlights()).append("            ").append(i.getSize()).append("\n");
                        }

                        JOptionPane.showMessageDialog(null, s, "LISTA DE AVES", JOptionPane.INFORMATION_MESSAGE);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encuentra el archivo", "", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 7 -> {
                    ArrayList<Veterinarian> list = new ArrayList<>();
                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream("veterinarians.dat"));
                        list = (ArrayList) input.readObject();
                        input.close();

                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Especialidad        Fecha de Contratación
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Veterinarian i :
                                list) {
                            s.append(i.getID()).append("                   ").append(i.getName()).append("                ").append(i.getSpecialization()).append("                  ").append(i.getDateOfHire().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, s, "LISTA DE VETERINARIOS", JOptionPane.INFORMATION_MESSAGE);

                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encuentra el archivo", "", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 8 -> {
                }
                case 9,10,11,12 -> {
                    int id;
                    do {
                        id = -1;
                        try {
                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la id a buscar"));
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
                                        switch (opt) {
                                            case 9 -> {
                                                ObjectInputStream input1 = new ObjectInputStream(new FileInputStream("mammals.dat"));
                                                ArrayList<Mammal> mammalList = (ArrayList) input1.readObject();
                                                input1.close();
                                                for (Mammal m :
                                                        mammalList) {
                                                    if (m.getID() == id) {
                                                        JOptionPane.showMessageDialog(null, m.getData(),"DETALLES MAMÍFERO", JOptionPane.INFORMATION_MESSAGE);
                                                        id = -1;
                                                    }
                                                }
                                            } case 10 -> {
                                                ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("birds.dat"));
                                                ArrayList<Bird> birdList = (ArrayList) input2.readObject();
                                                input2.close();
                                                for (Bird b :
                                                        birdList) {
                                                    if (b.getID() == id) {
                                                        JOptionPane.showMessageDialog(null, b.getData(),"DETALLES AVE", JOptionPane.INFORMATION_MESSAGE);
                                                        id = -1;
                                                    }
                                                }
                                            } case 11 -> {
                                                ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("veterinarians.dat"));
                                                ArrayList<Veterinarian> veterinarianList = (ArrayList) input3.readObject();
                                                input3.close();
                                                for (Veterinarian v :
                                                        veterinarianList) {
                                                    if (v.getID() == id) {
                                                        JOptionPane.showMessageDialog(null, v.getData(),"DETALLES VETERINARIO", JOptionPane.INFORMATION_MESSAGE);
                                                        id = -1;
                                                    }
                                                }
                                            } case 12 -> {
                                                ObjectInputStream input4 = new ObjectInputStream(new FileInputStream("revisions.dat"));
                                                ArrayList<Revision> revisionList = (ArrayList) input4.readObject();
                                                input4.close();
                                                for (Revision r :
                                                        revisionList) {
                                                    if (r.getID_Revision() == id) {
                                                        JOptionPane.showMessageDialog(null, r.getData(),"DETALLES REVISION", JOptionPane.INFORMATION_MESSAGE);
                                                        id = -1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (id != -1) {
                                    JOptionPane.showMessageDialog(null, "No se encontró la id", "", JOptionPane.INFORMATION_MESSAGE);
                                    id = -1;
                                } else {
                                    break;
                                }

                            } catch (FileNotFoundException ignored) {
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
                            } catch (ClassNotFoundException e) {
                                JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }while (id <= 0);
                }
                case 13 -> {
                }
            }
        } while (opt != 13);
    }

    private static void veterinariansWriter(ArrayList<Veterinarian> list, Veterinarian b) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("veterinarians.dat"));
            list.add(b);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo","", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S","", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void birdsWriter(ArrayList<Bird> list, Bird b) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("birds.dat"));
            list.add(b);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo","", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S","", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mammalsWriter(ArrayList<Mammal> list, Mammal m) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("mammals.dat"));
            list.add(m);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo","", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S","", JOptionPane.ERROR_MESSAGE);
        }
    }
}