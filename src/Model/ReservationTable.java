package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReservationTable {

    StringProperty boardGameName;
    StringProperty memberName;
    StringProperty from;
    StringProperty to;

    int ID;

    public ReservationTable(String boardGameName,String memberName, String from, String to, int ID){
        this.boardGameName = new SimpleStringProperty(boardGameName);
        this.memberName = new SimpleStringProperty(memberName);
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBoardGameName() {
        return boardGameName.get();
    }

    public StringProperty boardGameNameProperty() {
        return boardGameName;
    }

    public void setBoardGameName(String boardGameName) {
        this.boardGameName.set(boardGameName);
    }

    public String getMemberName() {
        return memberName.get();
    }

    public StringProperty memberNameProperty() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName.set(memberName);
    }

    public String getFrom() {
        return from.get();
    }

    public StringProperty fromProperty() {
        return from;
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getTo() {
        return to.get();
    }

    public StringProperty toProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }
}
