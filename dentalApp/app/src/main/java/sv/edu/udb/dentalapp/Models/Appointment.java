package sv.edu.udb.dentalapp.Models;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String key;
    private String user;
    private String description;
    private String date;
    private String service;

    public Appointment(String user, String description, String date, String service,String key) {
        this.user = user;
        this.description = description;
        this.date = date;
        this.service = service;
        this.key = key;
    }

    public Appointment() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
