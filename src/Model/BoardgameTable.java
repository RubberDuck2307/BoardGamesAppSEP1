package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BoardgameTable {

    StringProperty name;
    StringProperty type;
    StringProperty availability;
    StringProperty numberOfPlayers;

    int ID;

    public BoardgameTable(String name,String type, String availability, String numberOfPlayers, int ID){
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.availability = new SimpleStringProperty(availability);
        this.numberOfPlayers = new SimpleStringProperty(numberOfPlayers);
        this.ID = ID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAvailability() {
        return availability.get();
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability.set(availability);
    }

}



