package Model;

/**
 * A class representing a rating of a board game
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */
public class Rating
{
  private int ID;
  private int value;
  private int playerID;
  private int gameID;

  /**
   * The three-argument constructor calling the set method. ID is set to -1.
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the ID of the player rating the game
   * @param gameID the ID of the rated game
   */
  public Rating(int value, int playerID, int gameID)
  {
    int ID = -1;
    set(ID, value, playerID, gameID);
  }

  /**
   * The four-argument constructor calling the set method
   * @param ID The ID of the rating
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the ID of the player rating the game
   * @param gameID the ID of the rated game
   */
  public Rating(int ID, int value, int playerID, int gameID)
  {
    set(ID, value, playerID, gameID);
  }
  /**
   * The setter for all attributes
   * @param ID The ID of the rating
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the ID of the player rating the game
   * @param gameID the ID of the rated game
   */
  public void set(int ID, int value, int playerID, int gameID)
  {
    this.ID = ID;
    this.value = value;
    this.playerID = playerID;
    this.gameID = gameID;
  }

  /**
   * The getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * The setter for ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * The getter for value
   */
  public int getValue()
  {
    return value;
  }

  /**
   * The setter for value
   */
  public void setValue(int value)
  {
    this.value = value;
  }

  /**
   * The getter for playerID
   */
  public int getPlayerID()
  {
    return playerID;
  }

  /**
   * The setter for playerID
   */
  public void setPlayerID(int playerID)
  {
    this.playerID = playerID;
  }

  /**
   * The getter for gameID
   */
  public int getGameID()
  {
    return gameID;
  }


  /**
   * The setter for gameID
   */
  public void setGameID(int gameID)
  {
    this.gameID = gameID;
  }

  /**
   *
   * @return the values of all attributes as string
   */
  @Override public String toString()
  {
    return "Model.Rating{" + "ID=" + ID + ", value=" + value + ", playerID=" + playerID
        + ", gameID=" + gameID + '}';
  }
}
