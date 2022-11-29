package Model;

import java.util.ArrayList;

public class BoardGamesList
{
  private ArrayList<BoardGame> boardGamesList;

  public BoardGamesList()
  {
    boardGamesList = new ArrayList<>();
  }

  public BoardGamesList(ArrayList<BoardGame> boardGames)
  {
    this.boardGamesList = boardGames;
  }

  public void addBoardGame(BoardGame boardGame)
  {
    if (boardGame.getID() == -1)
    {
      if (boardGamesList.size() != 0)
      {
        boardGame.setID(boardGamesList.get(boardGamesList.size() - 1).getID() + 1);
      }
      else
      {
        boardGame.setID(1);
      }
    }
    boardGamesList.add(boardGame);
  }

  public BoardGame getBoardGame(int index)
  {
    return boardGamesList.get(index);
  }

  public BoardGame getNameByID(int ID){
    return boardGamesList.get(ID);
  }

  public int size()
  {
    return boardGamesList.size();
  }

  @Override public String toString()
  {
    return "Model.BoardGamesList{" + "boardGamesList=" + boardGamesList + '}';
  }
}
