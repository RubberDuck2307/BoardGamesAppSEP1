package TableClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BorrowingsTable {

    StringProperty boardGameName;
    StringProperty playerName;
    StringProperty from;
    StringProperty to;

    int ID;

    public BorrowingsTable(String boardGameName,String playerName, String from, String to, int ID){
        this.boardGameName = new SimpleStringProperty(boardGameName);
        this.playerName = new SimpleStringProperty(playerName);
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
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

    public String getPlayerName() {
        return playerName.get();
    }

    public StringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}