package Model;

import java.util.ArrayList;

/**
 * A class representing a list of Player objects
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 */
public class PlayersList
{
  private ArrayList<Player> playerList;

  /**
   * Zero-argument constructor. Set playerList to new empty ArrayList
   */
  public PlayersList()
  {
    playerList = new ArrayList<>();
  }

  /**
   *
   * @param index index of the player
   * @return player on the given index
   */
  public Player getPlayer(int index)
  {
    return playerList.get(index);
  }

  /**
   *
   * @param ID the player ID
   * @throws RuntimeException if player with same ID is not in the list
   * @return player whose ID equals the given ID
   */
  public Player getPlayerByID(int ID)
  {
    for (int i = 0; i < size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        return playerList.get(i);
      }
    }
    throw new RuntimeException("Player with such ID is not in the list");
  }

  /**
   *
   * @return size of playerList
   */
  public int size()
  {
    return playerList.size();
  }


   /**
   * Add the given player to the List
   * If the ID of the player is -1, it is set to the value of the ID of the last player in the list incremented by 1.
   * If the list is empty, the ID is set to 1
   */
  public void addPlayer(Player player)
  {
    if (player.getID() == -1)
    {
      if (playerList.size() == 0)
      {
        player.setID(1);
        playerList.add(player);
      }
      else
      {
        int lastID = playerList.get(playerList.size() - 1).getID();
        player.setID(lastID + 1);
        playerList.add(player);
      }
    }
    else
    {
      playerList.add(player);
    }
  }

  /**
   * Replace a player with a particular ID with the given player.
   * If a player with the ID is not found in the list, the given player is added on the end of the list instead.
   * @param player the player by which is the other event replaced
   * @param ID ID of player that is replaced
   */
  public void setPlayer(Player player, int ID)
  {
    boolean found = false;
    for (int i = 0; i < playerList.size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        playerList.set(i, player);
        found = true;
        break;
      }
    }
    if (!found)
    {
      playerList.add(player);
    }
  }

  /**
   * Removes a player from the list by given ID
   * @param ID ID of the player that is removed
   */
  public void deleteByID(int ID)
  {
    for (int i = 0; i < playerList.size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        playerList.remove(i);
        break;
      }
    }
  }

  /**
   *
   * @return new PlayerList with only player from the original list whose membership is false
   */
  public PlayersList getGuests()
  {
    PlayersList newPlayerList = new PlayersList();
    for (int i = 0; i < size(); i++)
    {

      if (!playerList.get(i).isMembership())
      {
        newPlayerList.addPlayer(playerList.get(i));
      }

    }
    return newPlayerList;
  }
  /**
   *
   * @return new PlayerList with only player from the original list whose membership is true
   */
  public PlayersList getMembers()
  {
    PlayersList newPlayerList = new PlayersList();
    for (int i = 0; i < size(); i++)
    {

      if (playerList.get(i).isMembership())
      {
        newPlayerList.addPlayer(playerList.get(i));
      }

    }
    return newPlayerList;
  }

  /**
   *
   * @param charSequence the string that names of returned players contain
   * @return new PlayersList with only the players from the original list whose name contains charSequence
   */
  public PlayersList filterPlayerList(String charSequence)
  {
    PlayersList newPlayerList = new PlayersList();
    for (int i = 0; i < size(); i++)
    {
      if (playerList.get(i).getName().toLowerCase().contains(charSequence.toLowerCase()) || playerList.get(i).getPhoneNumber().contains(charSequence)){
        newPlayerList.addPlayer(playerList.get(i));
      }
    }
    return newPlayerList;
  }

  /**
   * @param ID the ID of the player
   * @throws RuntimeException if player with such ID is not in the list
   * @return name of the player with given ID
   */
  public String getNameByID(int ID)
  {
    System.out.println("hello" + ID);
    for (int i = 0; i < playerList.size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        return playerList.get(i).getName();
      }
    }
    throw new RuntimeException("Player with such ID is not in the List");
  }

  /**
   * Set the attribute voted of all players in the list to false
   */
  public void setAllPlayersVotedFalse(){
    for (int i = 0; i < size(); i++)
    {
      System.out.println("false");
      getPlayer(i).setVoted(false);
    }
  }

  /**
   *
   * @return values of all the attributes of all the players as string
   */
  @Override public String toString()
  {
    return "Model.PlayersList{" + "playerList=" + playerList + '}';
  }
  }
