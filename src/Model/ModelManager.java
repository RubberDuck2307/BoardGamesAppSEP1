package Model;

import Application.FileReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class ModelManager
{
  private PlayersList playersList = FileReader.READ_PLAYERS_LIST();
  private BoardGamesList boardGamesList = FileReader.READ_BOARD_GAMES_LIST();
  private ReservationsList reservationsList = FileReader.READ_RESERVATIONS();
  private EventsList eventsList = FileReader.READ_EVENTS_LIST();
  private BorrowingsList borrowingsList = FileReader.READ_CURRENT_BORROWINGS();

  private RatingsList ratingsList = FileReader.READ_RATINGS_LIST();
  private Election election = FileReader.READ_ELECTION();

  public BorrowingsList getBorrowingsList()
  {
    return borrowingsList;
  }

  public ReservationsList getReservationsList()
  {
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

  public void addPlayer(Player player)
  {
    playersList.addPlayer(player);
  }

  public void addBoardGame(BoardGame boardGame)
  {
    boardGamesList.addBoardGame(boardGame);
  }
  public void addBorrowing(Reservation reservation){
    borrowingsList.addBorrowing(reservation);
  }

  public void savePlayers()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.SAVE_PLAYERS_LIST(playersList);
  }

  public void saveBorrowing()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.SAVE_CURRENT_BORROWINGS(borrowingsList);
  }

  public void setPlayer(Player player, int ID)
  {
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
    FileReader.SAVE_ELECTION(election);
  }

  public void resetVotedOfAllPlayers()
  {
    playersList.setAllPlayersVotedFalse();
  }

  public void saveBoardGames()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.SAVE_BOARDGAMES_LIST(boardGamesList);
  }

  public void setAllVotesTo0()
  {
    boardGamesList.setAllVotesTo0();
  }

  public void setBoardGame(BoardGame boardGame, int ID)
  {
    boardGamesList.setBoardGame(boardGame, ID);
  }

  public void setBorrowing(Reservation borrowing, int ID)
  {
    borrowingsList.setBorrowing(borrowing, ID);
  }

  public void addEvent(Event event)
  {
    eventsList.addEvent(event);
  }

  public void saveEvent()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.SAVE_EVENTS_LIST(eventsList);
  }

  public BoardGamesList getBoardGamesByOwnership(int ID)
  {
    return boardGamesList.findByOwnership(ID);
  }

  public ReservationsList getReservationsByPlayer(int ID)
  {
    return reservationsList.getReservationsByPlayer(ID);
  }
  public BorrowingsList getBorrowingsByPlayer(int ID)
  {
    return borrowingsList.getBorrowingsByPlayer(ID);
  }

  public void setEvent(Event event, int ID)
  {
    eventsList.setEvent(event, ID);
  }

  public RatingsList getRatingsByGame(int ID){
    return ratingsList.getRatingByBoardGame(ID);
  }

  public RatingsList getRatings(){
    return ratingsList;
  }

  public void addRating(Rating rating){ratingsList.addRating(rating);
  }

  public void saveRatings()
      throws ParserConfigurationException, TransformerException
  {
    FileReader.SAVE_RATINGS_LIST(ratingsList);
  }

  public void setReservationByID(Reservation reservation, int ID){
    reservationsList.setReservationByID(reservation, ID);
  }
  public void saveReservation() throws ParserConfigurationException, TransformerException {
    FileReader.SAVE_RESERVATIONS_LIST(reservationsList);
  }

  public void deleteReservationByID(int ID){
    reservationsList.deleteByID(ID);
  }

  public void deleteRatingByID(int ID){
    ratingsList.deleteByID(ID);
  }

  public RatingsList getRatingsByPlayer(int ID){
    return ratingsList.getRatingsByPlayer(ID);
  }
}