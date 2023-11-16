import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Revision implements Serializable {
    private int ID_Revision;
    private int ID_Animal;
    private int ID_Veterinarian;
    private LocalDate revisionDate;
    private String remarks;

    public Revision(int ID_Revision, int ID_Animal, int ID_Veterinarian, LocalDate revisionDate, String remarks) {
        this.ID_Revision = ID_Revision;
        this.ID_Animal = ID_Animal;
        this.ID_Veterinarian = ID_Veterinarian;
        this.revisionDate = revisionDate;
        this.remarks = remarks;
    }

    public Revision() {
        this.ID_Revision = 0;
        this.ID_Animal = 0;
        this.ID_Veterinarian = 0;
        this.revisionDate = null;
        this.remarks = null;
    }

    public int getID_Revision() {
        return ID_Revision;
    }

    public void setID_Revision(int ID_Revision) {
        this.ID_Revision = ID_Revision;
    }

    public int getID_Animal() {
        return ID_Animal;
    }

    public void setID_Animal(int ID_Animal) {
        this.ID_Animal = ID_Animal;
    }

    public int getID_Veterinarian() {
        return ID_Veterinarian;
    }

    public void setID_Veterinarian(int ID_Veterinarian) {
        this.ID_Veterinarian = ID_Veterinarian;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getData() {
        return """
                Fecha de Revision: %s
                Observaciones: %s
                """.formatted(getRevisionDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")), getRemarks());
    }
}
