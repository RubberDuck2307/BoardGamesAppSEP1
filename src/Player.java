import java.time.LocalDate;
import java.util.ArrayList;

public class Player
{
  private int ID;
  private String name;
  private String phoneNumber;
  private String email;
  private boolean membership;
  private ArrayList<Integer> ownedBoardGamesIDs;
  private ArrayList<Integer> borrowingsIDs;
  private String comment;
  private String address;
  private Boolean voted;

  private LocalDate feePaymentDate;

  public Player(int ID, String name, String phoneNumber, String email, boolean membership,
      ArrayList<Integer> ownedBoardGamesIDs, ArrayList<Integer> borrowingsIDS,
      String comment, String address, Boolean voted)
  {
    set(ID, name, phoneNumber, email, membership, ownedBoardGamesIDs, borrowingsIDS, comment, address , voted);
  }
  public Player(String name, String phoneNumber, String email, boolean membership,
      ArrayList<Integer> ownedBoardGamesIDs, ArrayList<Integer> borrowingsIDS,
      String comment, String address, Boolean voted)
  {
    int ID = -1;
    set(ID,name, phoneNumber, email, membership, ownedBoardGamesIDs, borrowingsIDS, comment, address , voted);
  }


  public Player(String name, String phoneNumber, boolean membership){
    int ID = -1;
    String email = "";
    ArrayList<Integer> ownedBoardGamesIDs = new ArrayList<>();
    ArrayList<Integer> borrowingsIDs = new ArrayList<>();
    String comment = "";
    String address = "";
    boolean voted = false;

    set(ID, name,phoneNumber,email, membership,ownedBoardGamesIDs,borrowingsIDs,comment,address,voted);

  }

  public void set(int ID,String name, String phoneNumber, String email,
      boolean membership, ArrayList<Integer> ownedBoardGamesIDs,
      ArrayList<Integer> borrowingsIDs, String comment, String address,
      Boolean voted)
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
    this.ownedBoardGamesIDs = ownedBoardGamesIDs;
    this.borrowingsIDs = borrowingsIDs;
    this.comment = comment;
    this.address = address;
    this.voted = voted;
  }

  static public boolean validateName(String name)
  {
    if (name.length() > 0)
    {
      return true;
    }
    return false;
  }

  static public boolean validatePhoneNumber(String phoneNumber)
  {
    if(phoneNumber.length() == 0){
      return false;
    }
    for (int i = 0; i < phoneNumber.length(); i++)
    {
      //If character on position is not a + or number, false is returned
      if (Character.compare(phoneNumber.charAt(i), ('+')) != 0
          && !Character.isDigit(phoneNumber.charAt(i)))
      {
        return false;
      }
    }
    if (phoneNumber.contains("+")
        && Character.compare(phoneNumber.charAt(0), ('+')) != 0)
    {
      return false;
    }
    return true;
  }

  static public boolean validateEmail(String email)
  {
    if (email.equals("")){
      return true;
    }

    int amountOfAts = email.length() - email.replace("@", "").length();

    if (!email.contains(".") || amountOfAts != 1)
    {
      return false;
    }
    if (email.indexOf('@') == 0 || email.lastIndexOf('.') == email.length() - 1)
    {
      return false;
    }

    if (email.lastIndexOf(".") <= email.indexOf('@') + 1)
    {
      return false;
    }
    String subString;
    subString = email.substring(email.indexOf('@'));
    if ((subString.length() - subString.replace(".", "").length()) != 1)
    {
      return false;
    }
    return true;
  }

  static public boolean validateData(String name, String phoneNumber)
  {
    return validateName(name) && validatePhoneNumber(phoneNumber);
  }

  static public boolean validateData(String name, String phoneNumber,
      String email)
  {
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

  public ArrayList<Integer> getOwnedBoardGamesIDs()
  {
    return ownedBoardGamesIDs;
  }

  public ArrayList<Integer> getBorrowingsIDs()
  {
    return borrowingsIDs;
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
    return "Player{" + "ID=" + ID + ", name='" + name + '\'' + ", phoneNumber='"
        + phoneNumber + '\'' + ", email='" + email + '\'' + ", membership="
        + membership + ", ownedBoardGamesIDs=" + ownedBoardGamesIDs
        + ", borrowingsIDs=" + borrowingsIDs + ", comment='" + comment + '\''
        + ", address='" + address + '\'' + ", voted=" + voted
        + ", feePaymentDate=" + feePaymentDate + '}';
  }
}
