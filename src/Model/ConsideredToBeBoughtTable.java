package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConsideredToBeBoughtTable
{
  private StringProperty name;
  private IntegerProperty position;
  private IntegerProperty numberOfVotes;
  private StringProperty type;
  private StringProperty numberOfPlayers;
  private int ID;

  public ConsideredToBeBoughtTable(String name, int position, int votes, int ID){
    this.name = new SimpleStringProperty(name);
    this.position = new SimpleIntegerProperty(position);
    this.numberOfVotes = new SimpleIntegerProperty(votes);
    this.ID = ID;
  }

  public ConsideredToBeBoughtTable(String name, String type, String numberOfPlayers, int votes, int ID){
    this.type = new SimpleStringProperty(type);
    this.name = new SimpleStringProperty(name);
    this.numberOfPlayers = new SimpleStringProperty(numberOfPlayers);
    this.position = new SimpleIntegerProperty(0);
    this.numberOfVotes = new SimpleIntegerProperty(votes);
    this.ID = ID;
  }

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public int getPosition()
  {
    return position.get();
  }

  public IntegerProperty positionProperty()
  {
    return position;
  }

  public void setPosition(int position)
  {
    this.position.set(position);
  }

  public int getNumberOfVotes()
  {
    return numberOfVotes.get();
  }

  public IntegerProperty numberOfVotesProperty()
  {
    return numberOfVotes;
  }

  public void setNumberOfVotes(int numberOfVotes)
  {
    this.numberOfVotes.set(numberOfVotes);
  }

  public String getType()
  {
    return type.get();
  }

  public StringProperty typeProperty()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type.set(type);
  }

  public String getNumberOfPlayers()
  {
    return numberOfPlayers.get();
  }

  public StringProperty numberOfPlayersProperty()
  {
    return numberOfPlayers;
  }

  public void setNumberOfPlayers(String numberOfPlayers)
  {
    this.numberOfPlayers.set(numberOfPlayers);
  }

  public int getID()
  {
    return ID;
  }
}
