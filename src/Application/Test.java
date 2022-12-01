package Application;

import Model.BoardGame;
import Model.BoardGamesList;
import Model.Event;
import Model.EventsList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException
  {
    BoardGame boardGame = new BoardGame("aa","aa",2,2,"aa","aa",2,2);
    ArrayList<BoardGame> boardGameArrayList = new ArrayList<>();
    boardGameArrayList.add(boardGame);
    BoardGamesList boardGamesList = new BoardGamesList(boardGameArrayList);
    System.out.println(boardGamesList);
    boardGameArrayList.remove(0);
    System.out.println(boardGamesList);
    }
}
