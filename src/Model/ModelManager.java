package Model;

import Application.FileReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class ModelManager
{
  PlayersList playersList = FileReader.readPlayersList();
  BoardGamesList boardGamesList = FileReader.readBoardGamesList();

  public BoardGamesList getBoardGamesList()
  {
    return boardGamesList;
  }

  public PlayersList getPlayersList()
  {
    return playersList;
  }

  public ModelManager()
      throws TransformerConfigurationException, ParserConfigurationException,
      IOException, SAXException
  {
  }

  public void addPlayer(Player player){
    playersList.addPlayer(player);
  }

  public void savePlayers ()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.savePlayersList(playersList);
  }
}
