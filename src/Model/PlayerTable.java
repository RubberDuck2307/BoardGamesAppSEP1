package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableView;

import javax.swing.*;

public class PlayerTable
{
  StringProperty name;
  StringProperty phone;
  StringProperty email;
  StringProperty membership;

  int ID;

  public PlayerTable(String name,String phone, String email, String membership, int ID){
    this.name = new SimpleStringProperty(name);
    this.phone = new SimpleStringProperty(phone);
    this.email = new SimpleStringProperty(email);
    this.membership = new SimpleStringProperty(membership);
    this.ID = ID;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
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

  public String getPhone()
  {
    return phone.get();
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone.set(phone);
  }

  public String getEmail()
  {
    return email.get();
  }

  public StringProperty emailProperty()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email.set(email);
  }

  public String getMembership()
  {
    return membership.get();
  }

  public StringProperty membershipProperty()
  {
    return membership;
  }

  public void setMembership(String membership)
  {
    this.membership.set(membership);
  }
}
