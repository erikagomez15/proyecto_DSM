package sv.edu.udb.dentalapp.Models;

public class Appointment {
    private String user;
    private String description;
    private String date;
    private String service;

    public Appointment(String user, String description, String date, String service) {
        this.user = user;
        this.description = description;
        this.date = date;
        this.service = service;
    }

    public Appointment() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
