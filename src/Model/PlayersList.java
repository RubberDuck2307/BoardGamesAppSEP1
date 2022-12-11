package Model;

import java.util.ArrayList;

/**
 * A class representing a list of Player objects
 *
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 */
public class PlayersList
{
  private ArrayList<Player> playerList;

  /**
   * the zero-argument constructor sets playerList to new empty ArrayList
   */
  public PlayersList()
  {
    playerList = new ArrayList<>();
  }

  /**
   * @param index the index of the player
   * @return the player with the given index
   */
  public Player getPlayer(int index)
  {
    return playerList.get(index);
  }

  /**
   * @param ID the player's ID
   * @return the player with the given ID
   * @throws RuntimeException if a player with the same ID is not on the list
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
   * @return the size of playerList
   */
  public int size()
  {
    return playerList.size();
  }

  /**
   * Adds the given player to the List
   * If the ID of the player is -1, it is set to the value of the
   * last player's ID in the list, incremented by 1.
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
   * Replaces a player having a particular ID with the given player.
   * If a player with the ID is not found in the list, the given player
   * is added at the end of the list instead.
   * @param player the player that will replace the current event.
   * @param ID     the ID of the player that will be replaced
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
   * Removes a player by ID from the list
   *
   * @param ID the ID of the player that is removed
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
   * @return a new PlayerList showing only players that do not have a membership
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
   * @return a new PlayerList showing only players that have a membership
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
   * @param charSequence the string containing the player's name
   * @return a new PlayersList showing only the players matching with charSequence
   */
  public PlayersList filterPlayerList(String charSequence)
  {
    PlayersList newPlayerList = new PlayersList();
    for (int i = 0; i < size(); i++)
    {
      if (playerList.get(i).getName().toLowerCase()
          .contains(charSequence.toLowerCase()) || playerList.get(i)
          .getPhoneNumber().contains(charSequence))
      {
        newPlayerList.addPlayer(playerList.get(i));
      }
    }
    return newPlayerList;
  }

  /**
   * @param ID the ID of the player
   * @return the name of the player with given ID
   * @throws RuntimeException if player with such ID is not in the list
   */
  public String getNameByID(int ID)
  {

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
   * Sets the attribute voted for all players in the list to false
   */
  public void setAllPlayersVotedFalse()
  {
    for (int i = 0; i < size(); i++)
    {
      getPlayer(i).setVoted(false);
    }
  }

  /**
   * @return the values of the attributes of all the players as string
   */
  @Override public String toString()
  {
    return "Model.PlayersList{" + "playerList=" + playerList + '}';
  }
}
