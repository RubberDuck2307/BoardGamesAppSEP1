package Model;

/**
 * A class representing a board game
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */

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
  static public String[] ALLOWED_STATUSES = {"Available", "Borrowed",
      "Unavailable", "Considered to be bought"};
  static public String AVAILABLE_STATUS = "Available";
  static public String BORROWED_STATUS = "Borrowed";
  static public String UNAVAILABLE_STATUS = "Unavailable";
  static public String CONSIDERED_TO_BE_BOUGHT_STATUS = "Considered to be bought";

  /**
   * Eight-argument constructor calling the set method. The missing attribute ID is set to -1
   * @param name the name of the board game
   * @param type the genre of the board game (see ALLOWED_TYPES)
   * @param numberOfPlayersMin minimal number of players who can play the board game
   * @param numberOfPlayersMax maximum number of player who can play the board game
   * @param availabilityStatus the status of the board game (see ALLOWED_STATUSES attribute)
   * @param comment a custom comment
   * @param ownerID the ID of the player who owns the board games
   * @param numberOfVotes the amount of votes the game has received
   */
  public BoardGame(String name, String type, int numberOfPlayersMin,
      int numberOfPlayersMax, String availabilityStatus, String comment,
      int ownerID, int numberOfVotes)
  {
    int ID = -1;
    set(ID, name, type, numberOfPlayersMin, numberOfPlayersMax,
        availabilityStatus, comment, ownerID, numberOfVotes);
  }

  /**
   * Nine-argument constructor calling the set method.
   * @param ID the ID of the board game
   * @param name the name of the board game
   * @param type the type of the board game (see ALLOWED_TYPES)
   * @param numberOfPlayersMin minimal number of players who can play the board game
   * @param numberOfPlayersMax maximum number of player who can play the board game
   * @param availabilityStatus the status of the board game (see ALLOWED_STATUSES attribute)
   * @param comment a custom comment
   * @param ownerID the ID of the player who owns the board games
   * @param numberOfVotes the amount of votes the game has received
   */
  public BoardGame(int ID, String name, String type, int numberOfPlayersMin,
      int numberOfPlayersMax, String availabilityStatus, String comment,
      int ownerID, int numberOfVotes)
  {
    set(ID, name, type, numberOfPlayersMin, numberOfPlayersMax,
        availabilityStatus, comment, ownerID, numberOfVotes);

  }

  /**
   * Setter for all attributes.
   * @param ID the ID of the board game
   * @param name the name of the board game
   * @param type the type of the board game (see ALLOWED_TYPES)
   * @param numberOfPlayersMin minimal number of players who can play the board game
   * @param numberOfPlayersMax maximum number of player who can play the board game
   * @param availabilityStatus the status of the board game (see ALLOWED_STATUSES attribute)
   * @param comment a custom comment
   * @param ownerID the ID of the player who owns the board games
   * @param numberOfVotes the amount of votes the game has received
   */

  public void set(int ID, String name, String type, int numberOfPlayersMin,
      int numberOfPlayersMax, String availabilityStatus, String comment,
      int ownerID, int numberOfVotes)
  {
    this.ID = ID;
    this.name = name;
    this.type = type;
    this.numberOfPlayersMin = numberOfPlayersMin;
    this.numberOfPlayersMax = numberOfPlayersMax;
    this.availabilityStatus = availabilityStatus;
    this.comment = comment;
    this.ownerID = ownerID;
    this.numberOfVotes = numberOfVotes;
  }

  /**
   * Getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * Setter for ID
   */

  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * Getter for name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Setter for name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Getter for type
   */
  public String getType()
  {
    return type;
  }

  /**
   * Setter for type
   */

  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * Getter for numberOfPlayersMin
   */
  public int getNumberOfPlayersMin()
  {
    return numberOfPlayersMin;
  }

  /**
   * Setter for numberOfPlayersMin
   */

  public void setNumberOfPlayersMin(int numberOfPlayersMin)
  {
    this.numberOfPlayersMin = numberOfPlayersMin;
  }

  /**
   * Getter for numberOfPlayersMax
   */
  public int getNumberOfPlayersMax()
  {
    return numberOfPlayersMax;
  }

  /**
   * Setter for numberOfPlayersMin
   */
  public void setNumberOfPlayersMax(int numberOfPlayersMax)
  {
    this.numberOfPlayersMax = numberOfPlayersMax;
  }

  /**
   * Getter for availabilityStatus
   */

  public String getAvailabilityStatus()
  {
    return availabilityStatus;
  }

  /**
   *  Setter for availabilityStatus
   */
  public void setAvailabilityStatus(String availabilityStatus)
  {
    this.availabilityStatus = availabilityStatus;
  }

  /**
   * Getter for comment
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Setter for comment
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Getter for ownerID
   */
  public int getOwnerID()
  {
    return ownerID;
  }

  /**
   * Setter for owner ID
   */
  public void setOwnerID(int ownerID)
  {
    this.ownerID = ownerID;
  }

  /**
   * Getter for NumberOfVotes
   */
  public int getNumberOfVotes()
  {
    return numberOfVotes;
  }

  /**
   * Increment numberOfVotes by 1
   */
  public void addVote()
  {
    numberOfVotes++;
  }

  /**
   * Setter for numberOfVotes
   */
  public void setNumberOfVotes(int numberOfVotes)
  {
    this.numberOfVotes = numberOfVotes;
  }

  /**
   * get a copy of the board game
   */
  public BoardGame copy()
  {
    return new BoardGame(ID, name, type, numberOfPlayersMin, numberOfPlayersMax,
        availabilityStatus, comment, ownerID, numberOfVotes);
  }

  /**
   *
   * @return values of all the attributes as string
   */
  @Override public String toString()
  {
    return "Model.BoardGame{" + "ID=" + ID + ", name='" + name + '\''
        + ", type='" + type + '\'' + ", numberOfPlayersMin="
        + numberOfPlayersMin + ", numberOfPlayersMax=" + numberOfPlayersMax
        + ", availabilityStatus='" + availabilityStatus + '\'' + ", comment='"
        + comment + '\'' + ", ownerID=" + ownerID + ", numberOfVotes="
        + numberOfVotes + '}';
  }

  /**
   *
   * @param name The name of the board game
   * @throws RuntimeException if name is an empty string
   * @return true if name is not an empty string
   */

  static public boolean VALIDATE_NAME(String name)
  {
    name = name.trim();
    if (name.length() == 0)
    {
      throw new RuntimeException("The text field name is empty");
    }
    return true;
  }

  /**
   *
   * @param name
   * @param min
   * @param max
   * @return
   */
  static public boolean VALIDATE_DATA(String name, String min, String max)
  {
    return  VALIDATE_NAME(name) && validateMinAndMax(min, max);

  }

  /**
   *
   * @param min the minimum number of players that can play the game
   * @param max the maximum number of players that can play the game
   * @throws RuntimeException if the parameters cannot be converted to int or if min is bigger than max
   * @return true if min is smaller than max
   */
  static public boolean validateMinAndMax(String min, String max)
  {
    try
    {
      int minimum;
      int maximum;
      minimum = Integer.parseInt(min);
      maximum = Integer.parseInt(max);
      if (minimum < 0 || minimum > maximum)
      {
        throw new RuntimeException(
            "The numbers the first number is not smaller then the other.");
      }
    }
    catch(NumberFormatException nfe)
    {
      throw new RuntimeException("Please insert a number as a value for minimum and maximum number of players.");
    }
    return true;

  }


}
