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
  ReservationsList reservationsList = FileReader.readReservations();
  EventsList eventsList = FileReader.readEventsList();

  BorrowingsList borrowingsList = FileReader.readCurrentBorrowings();

  public BorrowingsList getBorrowingsList() {
    return borrowingsList;
  }

  public ReservationsList getReservationsList() {
    return reservationsList;
  }

  public BoardGamesList getBoardGamesList()
  {
    return boardGamesList;
  }

  public PlayersList getPlayersList()
  {
    return playersList;
  }

  public EventsList getEventsList()
  {
    return eventsList;
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

  public void setPlayer(Player player, int ID){
    playersList.setPlayer(player, ID);
  }
  public void setBoardGame(BoardGame boardGame, int ID){
    boardGamesList.setBoardGame(boardGame, ID);
  }
  public void saveBoardGames ()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.saveBoardGameList(boardGamesList);
  }

  public void addEvent(Event event){
    eventsList.addEvent(event);
  }

  public void saveEvent()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.saveEventsList(eventsList);
  }

  public void setEvent(Event event, int ID){
    eventsList.setEvent(event, ID);
  }
}
