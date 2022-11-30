package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableView;

public class EventsTable
{
  StringProperty name;
  StringProperty place;
  StringProperty from;
  StringProperty to;
  int ID;

  public EventsTable(String name, String place,
      String from, String to, int ID)
  {
    this.name = new SimpleStringProperty(name);
    this.place = new SimpleStringProperty(place);
    this.from = new SimpleStringProperty(from);
    this.to = new SimpleStringProperty(to);
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

  public String getPlace()
  {
    return place.get();
  }

  public StringProperty placeProperty()
  {
    return place;
  }

  public void setPlace(String location)
  {
    this.place.set(location);
  }

  public String getFrom()
  {
    return from.get();
  }

  public StringProperty fromProperty()
  {
    return from;
  }

  public void setFrom(String from)
  {
    this.from.set(from);
  }

  public String getTo()
  {
    return to.get();
  }

  public StringProperty toProperty()
  {
    return to;
  }

  public void setTo(String to)
  {
    this.to.set(to);
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }
}
