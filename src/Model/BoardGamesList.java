package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * A class representing list of BoardGame objects
 *
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */
public class BoardGamesList
{
  private ArrayList<BoardGame> boardGamesList;

  /**
   * Zero-argument constructor
   * set attribute boardGamesList to new empty ArrayList
   */
  public BoardGamesList()
  {
    boardGamesList = new ArrayList<>();
  }

  /**
   * set attribute boardGamesList to new ArrayList and fills it with copies of objects from given ArrayList
   *
   * @param boardGames Arraylist of BoardGame objects
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
   * add a board game into boardGamesList.
   * If the ID of the board game is -1, it is set to the value of the ID of the last board game in the list incremented by 1.
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
   * Replace a board game with particular ID with the given board game.
   * If a game with the ID is not found in the list, the given board game is added on the end of the list instead.
   * @param boardgame a board game by which will be the current board game replaced.
   * @param ID the ID of the board game that will be replaced.
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
   * Removes a game from boardGamesList by ID
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
   * @param status the status of board games which are returned
   * @return a new BoardGamesList with only board games from the original list whose status is equal to status
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
   * @param status the status of board games which are returned
   * @param status2 the status of board games which are returned
   * @return a new BoardGamesList with only board games from the original list whose status is equal either to status1 or status2
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
   * @param type the type of board games which are returned
   * @return a new BoardGamesList with only board games from the original list whose type is equal to inputted type
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
   * @param numberOfPlayers the amount of players for which are returned board games playable (number of players is in range of minNumberOfPlayers - maxNumberOfPlayers)
   * @return a new BoardGamesList with only board games from the original list that are playable by inputted number of players
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
   *
   * @param index index of the board game which is returned
   * @return the board game on given index
   */
  public BoardGame getBoardGame(int index)
  {
    return boardGamesList.get(index);
  }

  /**
   *
   * @param charSequence the string that names of returned boardgames contain
   * @return new BoardGamesList with only the board games from the original list whose name contains charSequence
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
   *
   * @param ID ID of the board game
   * @throws RuntimeException, if board game with such ID is not in the list
   * @return the name of the board game with the given ID
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
   *
   * @return the size of boardGamesList
   */
  public int size()
  {
    return boardGamesList.size();
  }

  /**
   *
   * @return new BoardGamesList with all board games from the original list whose status equals to "Considered to be bought status" sorted by numberOfVotes
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
   *
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
   * return the board game with given ID. If there is no game with such ID a new BoardGame is returned.
   * @param ID ID of the board game
   * @throws RuntimeException if a game with given ID is not in boardGamesList
   * @return the board game with given ID
   */
  public BoardGame getBoardGameByID(int ID)
  {
    for (int i = 0; i < size(); i++)
    {
      if (getBoardGame(i).getID() == ID)
      {
        return getBoardGame(i);
      }
    }
    throw new RuntimeException("In the list is no game with such ID");
  }

  /**
   *
   * @return board game that has the biggest numberOfVotes
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
   * Set attribute of all the boardgames in the list to 0
   */
  public void setAllVotesTo0()
  {
    for (int i = 0; i < size(); i++)
    {
      boardGamesList.get(i).setNumberOfVotes(0);
    }

  }

  /**
   *
   * @param ID ID of a player
   * @return new BoardGamesList of board games from the original list which are owned by player with the given ID
   */
  public BoardGamesList findByOwnership(int ID)
  {
    System.out.println(ID);
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
   *
   * @return values of attribute of all the board games in the list as string
   */
  @Override public String toString()
  {
    return "Model.BoardGamesList{" + "boardGamesList=" + boardGamesList + '}';
  }
}
