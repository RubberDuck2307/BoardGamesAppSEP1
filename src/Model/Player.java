package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Player
{
  private int ID;
  private String name;
  private String phoneNumber;
  private String email;
  private boolean membership;
  private String comment;
  private String address;
  private Boolean voted;

  private LocalDate feePaymentDate;

  public Player(int ID, String name, String phoneNumber, String email, boolean membership,
      String comment, String address, Boolean voted, LocalDate feePaymentDate)
  {
    set(ID, name, phoneNumber, email, membership, comment, address , voted, feePaymentDate);
  }
  public Player(String name, String phoneNumber, String email, boolean membership,
      String comment, String address, Boolean voted, LocalDate feePaymentDate)
  {
    int ID = -1;
    set(ID,name, phoneNumber, email, membership, comment, address , voted, feePaymentDate);
  }


  public Player(String name, String phoneNumber, boolean membership){
    int ID = -1;
    String email = "";
    String comment = "";
    String address = "";
    boolean voted = false;

    set(ID, name,phoneNumber,email, membership,comment,address,voted, null);

  }

  public void set(int ID,String name, String phoneNumber, String email,
      boolean membership, String comment, String address,
      Boolean voted, LocalDate feePaymentDate)
  {
    name.trim();
    phoneNumber.trim();
    email.trim();
    comment.trim();
    address.trim();
    this.ID = ID;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.membership = membership;
    this.comment = comment;
    this.address = address;
    this.voted = voted;
    this.feePaymentDate = feePaymentDate;
  }

  static public boolean validateName(String name)
  {
    name = name.trim();
    if (name.length() > 0)
    {
      return true;
    }
    throw new RuntimeException("Name is not valid");
  }

  static public boolean validatePhoneNumber(String phoneNumber)
  {
    phoneNumber = phoneNumber.trim();
    if(phoneNumber.length() == 0){
      throw new RuntimeException("Phone number is not valid");
    }
    for (int i = 0; i < phoneNumber.length(); i++)
    {
      //If character on position is not a + or number, false is returned
      if (Character.compare(phoneNumber.charAt(i), ('+')) != 0
          && !Character.isDigit(phoneNumber.charAt(i)))
      {
        throw new RuntimeException("Phone number is not valid");
      }
    }
    if (phoneNumber.contains("+")
        && Character.compare(phoneNumber.charAt(0), ('+')) != 0)
    {
      throw new RuntimeException("Phone number is not valid");
    }
    return true;
  }

  static public boolean validateEmail(String email)
  {
    email = email.trim();
    if (email.equals("")){
      return true;
    }

    int amountOfAts = email.length() - email.replace("@", "").length();

    if (!email.contains(".") || amountOfAts != 1)
    {
      throw new RuntimeException("Email is not valid");
    }
    if (email.indexOf('@') == 0 || email.lastIndexOf('.') == email.length() - 1)
    {
      throw new RuntimeException("Email is not valid");
    }

    if (email.lastIndexOf(".") <= email.indexOf('@') + 1)
    {
      throw new RuntimeException("Email is not valid");
    }
    String subString;
    subString = email.substring(email.indexOf('@'));
    if ((subString.length() - subString.replace(".", "").length()) != 1)
    {
      throw new RuntimeException("Email is not valid");
    }
    return true;
  }


  static public boolean validateData(String name, String phoneNumber,
      String email)
  {
    System.out.println(validateName(name));
    System.out.println(validatePhoneNumber(phoneNumber));
    System.out.println(validateEmail(email));
    return validateName(name) && validatePhoneNumber(phoneNumber)
        && validateEmail(email);
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
    return name;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public boolean isMembership()
  {
    return membership;
  }

  public String getComment()
  {
    return comment;
  }

  public String getAddress()
  {
    return address;
  }

  public Boolean getVoted()
  {
    return voted;
  }

  public void setVoted(Boolean voted)
  {
    this.voted = voted;
  }

  public LocalDate getFeePaymentDate()
  {
    return feePaymentDate;
  }

  public void setFeePaymentDate(LocalDate feePaymentDate)
  {
    this.feePaymentDate = feePaymentDate;
  }

  @Override public String toString()
  {
    return "Model.Player{" + "ID=" + ID + ", name='" + name + '\'' + ", phoneNumber='"
        + phoneNumber + '\'' + ", email='" + email + '\'' + ", membership="
        + membership +
        ", comment='" + comment + '\''
        + ", address='" + address + '\'' + ", voted=" + voted
        + ", feePaymentDate=" + feePaymentDate + '}';
  }
}
