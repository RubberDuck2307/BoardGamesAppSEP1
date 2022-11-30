package Model;

import java.util.ArrayList;

public class PlayersList
{
  private ArrayList<Player> playerList;

  public PlayersList()
  {
    playerList = new ArrayList<>();
  }

  public PlayersList(ArrayList<Player> playerList)
  {
    this.playerList = playerList;
  }

  public Player getPlayer(int index)
  {
    return playerList.get(index);
  }

  public Player getPlayerByID(int ID){
    for (int i = 0; i < size(); i++){
      if (playerList.get(i).getID() == ID){
        return playerList.get(i);
      }
    }
    return playerList.get(0);
  }
  public int size()
  {
    return playerList.size();
  }

  public void addPlayer(Player player)
  {
    if (player.getID() == -1)
    {
      if (playerList.size() == 0)
      {
        player.setID(0);
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

  public void setPlayer(Player player, int ID)
  {
    for (int i = 0; i < playerList.size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        playerList.set(i, player);
        break;
      }
      else
      {
        playerList.add(player);
      }
    }
  }

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

    @Override public String toString () {
    return "Model.PlayersList{" + "playerList=" + playerList + '}';
  }
  public String getNameByID(int ID)
  {
    for (int i = 0; i < playerList.size(); i++)
    {
      if (playerList.get(i).getID() == ID)
      {
        return playerList.get(i).getName();
      }
    }
    return "Anna";
  }
  }
