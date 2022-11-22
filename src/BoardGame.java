import java.util.ArrayList;

public class BoardGame
{
  private int ID;
  private String name;
  private String type;
  private int numberOfPlayersMin;
  private int numberOfPlayersMax;
  private String availabilityStatus;
  private String comment;
  private int ownerID; //0 is the ID of association

  private int numberOfVotes;

  static public String[] ALLOWED_TYPES = {"Engine Building", "Deck Building",
      "Coop", "Work Placement", "Social Deduction", "RPG", "Without Dice",
      "Roll and Write", "Other"};
  static public String ENGINE_BUILDING_TYPE = "Engine Building";
  static public String DECK_BUILDING_TYPE = "Deck Building";
  static public String COOP_TYPE = "Coop";
  static public String WORK_PLACEMENT = "Work Placement";
  static public String SOCIAL_DEDUCTION_TYPE = "Social Deduction";
  static public String RPG_TYPE = "RPG";
  static public String WITHOUT_DICE_TYPE = "Without Dice";
  static public String ROLL_AND_WRITE = "Roll and Write";
  static public String OTHER = "Other";
  static public String[] ALLOWED_STATUSES = {"Available","Borrowed","Unavailable","Considered to be bought"};
  static public String AVAILABLE_STATUS = "Available";
  static public String BORROWED_STATUS = "Borrowed";
  static public String UNAVAILABLE_STATUS = "Unavailable";
  static public String CONSIDERED_TO_BE_BOUGHT_STATUS = "Considered to be bought";

  public BoardGame(String name, String type, int numberOfPlayersMin, int numberOfPlayersMax, String availabilityStatus, String comment, int ownerID, int numberOfVotes){
    int ID = -1;
    set(ID, name, type , numberOfPlayersMin, numberOfPlayersMax, availabilityStatus, comment, ownerID, numberOfVotes);}

  public BoardGame(int ID, String name, String type, int numberOfPlayersMin, int numberOfPlayersMax, String availabilityStatus, String comment, int ownerID, int numberOfVotes){
    set(ID, name, type , numberOfPlayersMin, numberOfPlayersMax, availabilityStatus, comment, ownerID, numberOfVotes);

  }

  public void set(int ID, String name, String type, int numberOfPlayersMin, int numberOfPlayersMax, String availabilityStatus, String comment, int ownerID, int numberOfVotes){
    this.ID = ID;
    this.name = name;
    this.type = type;
    this.numberOfPlayersMin = numberOfPlayersMin;
    this.numberOfPlayersMax = numberOfPlayersMax;
    this.availabilityStatus = availabilityStatus;
    this.comment = comment;
    this.ownerID = ownerID;
    this.numberOfVotes =numberOfVotes;
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

  public void setName(String name)
  {
    this.name = name;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public int getNumberOfPlayersMin()
  {
    return numberOfPlayersMin;
  }

  public void setNumberOfPlayersMin(int numberOfPlayersMin)
  {
    this.numberOfPlayersMin = numberOfPlayersMin;
  }

  public int getNumberOfPlayersMax()
  {
    return numberOfPlayersMax;
  }

  public void setNumberOfPlayersMax(int numberOfPlayersMax)
  {
    this.numberOfPlayersMax = numberOfPlayersMax;
  }

  public String getAvailabilityStatus()
  {
    return availabilityStatus;
  }

  public void setAvailabilityStatus(String availabilityStatus)
  {
    this.availabilityStatus = availabilityStatus;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public int getOwnerID()
  {
    return ownerID;
  }

  public void setOwnerID(int ownerID)
  {
    this.ownerID = ownerID;
  }

  public int getNumberOfVotes()
  {
    return numberOfVotes;
  }

  public void setNumberOfVotes(int numberOfVotes)
  {
    this.numberOfVotes = numberOfVotes;
  }

  @Override public String toString()
  {
    return "BoardGame{" + "ID=" + ID + ", name='" + name + '\'' + ", type='"
        + type + '\'' + ", numberOfPlayersMin=" + numberOfPlayersMin
        + ", numberOfPlayersMax=" + numberOfPlayersMax
        + ", availabilityStatus='" + availabilityStatus + '\'' + ", comment='"
        + comment + '\'' + ", ownerID=" + ownerID + ", numberOfVotes="
        + numberOfVotes + '}';
  }
}
