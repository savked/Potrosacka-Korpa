package pk.company.potrosackakorpa.Models;

public class Lists {
    private String name;
    private String importance;
    private String dateTime;

    public Lists(String name, String importance, String dateTime) {
        this.name = name;
        this.importance = importance;
        this.dateTime = dateTime;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }
}
