package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * A class representing the list of the BoardGame objects
 *
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */
public class BoardGamesList
{
  private ArrayList<BoardGame> boardGamesList;

  /**
   * The zero-argument constructor
   * sets the attribute boardGamesList to a new empty ArrayList
   */
  public BoardGamesList()
  {
    boardGamesList = new ArrayList<>();
  }

  /**
   * The one-argument constructor
   * sets the attribute boardGamesList to a new ArrayList and fills it with copies of the objects from the given ArrayList
   *
   * @param boardGames the Arraylist of the BoardGame objects
   */
  public BoardGamesList(ArrayList<BoardGame> boardGames) //This is composition
  {
    this.boardGamesList = new ArrayList<>();
    for (BoardGame boardGame : boardGames)
    {
      BoardGame newBoardGame = boardGame.copy();
      this.addBoardGame(newBoardGame);
    }
  }

  /**
   * Adds a board game into the ArrayList boardGamesList.
   * If the ID of the board game is -1, it is set to the value of the last board game's ID in the list, incremented by 1.
   * If the list is empty, the ID is set to 0
   */
  public void addBoardGame(BoardGame boardGame)
  {
    if (boardGame.getID() == -1)
    {
      if (boardGamesList.size() != 0)
      {
        boardGame.setID(
            boardGamesList.get(boardGamesList.size() - 1).getID() + 1);
      }
      else
      {
        boardGame.setID(0);
      }
    }
    boardGamesList.add(boardGame);
  }

  /**
   * Replaces a board game having a particular ID with the given board game.
   * If a game with the ID is not found in the list, the given board game is
   * added at the end of the list instead.
   *
   * @param boardgame the board game that will replace the current board game.
   * @param ID        the ID of the board game that will be replaced.
   */
  public void setBoardGame(BoardGame boardgame, int ID)
  {
    boolean found = false;
    for (int i = 0; i < boardGamesList.size(); i++)
    {
      if (boardGamesList.get(i).getID() == ID)
      {
        boardGamesList.set(i, boardgame);
        found = true;
        break;
      }
    }
    if (!found)
    {
      boardGamesList.add(boardgame);

    }
  }

  /**
   * Removes a game by ID from the ArrayList boardGamesList
   *
   * @param ID id of the game that is removed
   */
  public void deleteByID(int ID)
  {
    for (int i = 0; i < boardGamesList.size(); i++)
    {
      if (boardGamesList.get(i).getID() == ID)
      {
        boardGamesList.remove(i);
        break;
      }
    }
  }

  /**
   * @param status the status of board games
   * @return a new list of board games containing only the requested status
   */
  public BoardGamesList getBoardGameListByStatus(String status)
  {
    BoardGamesList newBoardGameList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (Objects.equals(boardGamesList.get(i).getAvailabilityStatus(), status))
      {
        newBoardGameList.addBoardGame(boardGamesList.get(i));
      }
    }
    return newBoardGameList;
  }

  /**
   * @param status  the status of board games
   * @param status2 the second status of board games
   * @return a new list of board games containing only the first or the second requested status
   */
  public BoardGamesList getBoardGameListByStatus(String status, String status2)
  {
    BoardGamesList newBoardGameList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (Objects.equals(boardGamesList.get(i).getAvailabilityStatus(), status)
          || Objects.equals(boardGamesList.get(i).getAvailabilityStatus(),
          status2))
      {
        newBoardGameList.addBoardGame(boardGamesList.get(i));
      }
    }
    return newBoardGameList;
  }

  /**
   * @param type the type of board games
   * @return a new list containing only the requested type
   */
  public BoardGamesList getBoardGameListByType(String type)
  {
    BoardGamesList newBoardGameList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (Objects.equals(boardGamesList.get(i).getType(), type))
      {
        newBoardGameList.addBoardGame(boardGamesList.get(i));
      }
    }
    return newBoardGameList;
  }

  /**
   * @param numberOfPlayers the wished number of players
   * @return a new list of boardgames containing the requested number of players
   */
  public BoardGamesList getBoardGameListByNumberOfPlayers(int numberOfPlayers)
  {
    BoardGamesList newBoardGameList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (boardGamesList.get(i).getNumberOfPlayersMin() <= numberOfPlayers
          && numberOfPlayers <= boardGamesList.get(i).getNumberOfPlayersMax())
      {
        newBoardGameList.addBoardGame(boardGamesList.get(i));
      }
    }
    return newBoardGameList;
  }

  /**
   * @param index the index of the board game
   * @return the board game with the requested index
   */
  public BoardGame getBoardGame(int index)
  {
    return boardGamesList.get(index);
  }

  /**
   * @param charSequence the string containing the searched board game
   * @return new list of board games containing only charSequence
   */
  public BoardGamesList filterBoardGameList(String charSequence)
  {
    BoardGamesList newBoardGameList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (boardGamesList.get(i).getName().contains(charSequence))
      {
        newBoardGameList.addBoardGame(boardGamesList.get(i));
      }
    }
    return newBoardGameList;
  }

  /**
   * @param ID The ID of the board game
   * @return the name of the board game with the given ID
   * @throws RuntimeException, if the board game with this ID is not in the list
   */
  public String getNameByID(int ID)
  {
    for (int i = 0; i < boardGamesList.size(); i++)
    {
      if (boardGamesList.get(i).getID() == ID)
      {
        return boardGamesList.get(i).getName();
      }
    }
    throw new RuntimeException("Board game with such ID is not in the list");
  }

  /**
   * @return the size of the board games list boardGamesList
   */
  public int size()
  {
    return boardGamesList.size();
  }

  /**
   * @return new BoardGamesList containing only games with the status
   * "Considered to be bought" sorted by numberOfVotes in descending order
   */
  public BoardGamesList getConsideredToBeBoughtGames()
  {
    BoardGamesList newBoardGamesList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {

      if (boardGamesList.get(i).getAvailabilityStatus()
          .equals(BoardGame.CONSIDERED_TO_BE_BOUGHT_STATUS))
      {
        newBoardGamesList.addBoardGame(boardGamesList.get(i));
      }
    }

    ArrayList<BoardGame> arrayList = newBoardGamesList.getBoardGamesListAsArrayList();
    arrayList.sort(Comparator.comparing(BoardGame::getNumberOfVotes));
    return new BoardGamesList(arrayList);
  }

  /**
   * @return A new ArrayList with the copies of the board games
   */
  public ArrayList<BoardGame> getBoardGamesListAsArrayList()
  {
    ArrayList<BoardGame> newBoardGamesList = new ArrayList<>();

    for (BoardGame boardGame : boardGamesList)
    {
      newBoardGamesList.add(boardGame.copy());
    }
    return newBoardGamesList;
  }

  /**
   * @param ID the ID of the board game
   * @return the board game with given ID
   * @throws RuntimeException if a game with the given ID is not in boardGamesList
   */
  public BoardGame getBoardGameByID(int ID)
  {

    for (int i = 0; i < size(); i++) // 1 + 1 + 2n - 1
    {
      if (getBoardGame(i).getID()
          == ID) //3n  Compares ID of every board game in the list
      {
        return getBoardGame(
            i); //1 If the game with the same ID is found it is returned
      }
    }
    throw new RuntimeException(
        "In the list is no game with such ID"); //1 Otherwise an exception is thrown
  }
  // variable n is the size of the list
  // In the worst case scenario, the board game is not in the list.
  // In the worst case scenario we loop n times.
  // T(n) = 5n + 2 Ignoring the constants we get
  // T(n) = O(n)
  // We choose this method because it is one of the most essentials methods for our program to work

  /**
   * @return the board game that has the highest value for numberOfVotes
   */
  public BoardGame getBoardGameWithMostVotes()
  {
    BoardGame max = boardGamesList.get(0);
    for (int i = 1; i < size(); i++)
    {
      if (boardGamesList.get(i).getNumberOfVotes() > max.getNumberOfVotes())
      {
        max = boardGamesList.get(i);
      }
    }
    return max;
  }

  /**
   * Sets the attribute of all the boardgames in the list to 0
   */
  public void setAllVotesTo0()
  {
    for (int i = 0; i < size(); i++)
    {
      boardGamesList.get(i).setNumberOfVotes(0);
    }

  }

  /**
   * @param ID the ID of a player
   * @return new BoardGamesList containing only the games owned by the player with the given ID
   */
  public BoardGamesList findByOwnership(int ID)
  {
    BoardGamesList newBoardGamesList = new BoardGamesList();
    for (int i = 0; i < size(); i++)
    {
      if (getBoardGame(i).getOwnerID() == ID)
      {
        newBoardGamesList.addBoardGame(getBoardGame(i));
      }
    }
    return newBoardGamesList;
  }

  /**
   * @return the values of the attributes of all the board games in the list as string
   */
  @Override public String toString()
  {
    return "Model.BoardGamesList{" + "boardGamesList=" + boardGamesList + '}';
  }
}
