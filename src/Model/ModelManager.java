package Model;

import Application.FileReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class ModelManager
{
  private PlayersList playersList = FileReader.readPlayersList();
  private BoardGamesList boardGamesList = FileReader.readBoardGamesList();
  private ReservationsList reservationsList = FileReader.readReservations();
  private EventsList eventsList = FileReader.readEventsList();
  private BorrowingsList borrowingsList = FileReader.readCurrentBorrowings();
  private Election election = FileReader.readElection();

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

  public Election getElection()
  {
    return election;
  }

  public void setElection(Election election)
  {
    this.election = election;
  }

  public void saveElection()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.saveElection(election);
  }

  public void resetVotedOfAllPlayers(){
    playersList.setAllPlayersVotedFalse();
  }

  public void saveBoardGames()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.saveBoardGameList(boardGamesList);
  }

  public void setAllVotesTo0(){
    boardGamesList.setAllVotesTo0();
  }
}
