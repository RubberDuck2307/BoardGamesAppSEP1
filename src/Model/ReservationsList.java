package Model;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class representing list of reservations
 * @author Jan Metela
 * @version 1.0 - November 2022
 */
public class ReservationsList
{
  private ArrayList<Reservation> reservationsList;

  /**
   * Zero-argument constructor
   * set attribute reservationsList to new empty ArrayList
   */
  public ReservationsList()
  {
    reservationsList = new ArrayList<>();
  }
  /**
   * add a reservation into reservationList.
   * If the ID of the reservation is -1 it is set to the value of the ID of the last reservation in the list incremented by 1.
   * If the list is empty the ID is set to 0
   */
  public void addReservation(Reservation reservation)
  {
    if (reservation.getID() == -1)
    {
      if (reservationsList.size() != 0)
      {
        reservation.setID(reservationsList.get(reservationsList.size() - 1).getID() + 1);
      }
      else
      {
        reservation.setID(0);
      }
    }
    reservationsList.add(reservation);
  }

  /**
   * Removes a reservation from the list by ID
   * @param ID ID of the reservation that is removed
   */
  public void deleteByID(int ID){
    for (int i = 0; i < reservationsList.size(); i++) {
      if(getReservation(i).getID() == ID){
        reservationsList.remove(i);
        break;
      }
    }

    }

  /**
   *
   * @param index index of the reservation which is returned
   * @return reservation on the given index
   */
  public Reservation getReservation(int index)
  {
    return reservationsList.get(index);
  }

  /**
   *
   * @param ID ID of the player who reserve the game
   * @return new reservations list with only reservation from the original list which has the same playerID as the given one
   */
  public ReservationsList getReservationsByPlayer(int ID){
    ReservationsList newReservationList = new ReservationsList();
    for (int i = 0; i < size(); i++)
    {
      if(getReservation(i).getPlayerID() == ID){
        newReservationList.addReservation(getReservation(i));
      }
    }
    return newReservationList;
  }

  /**
   * Remove the reservation on the given index from the list
   * @param index index of the game which is removed
   */
  public void delete(int index){
    reservationsList.remove(index);
  }

  /**
   *
   * @return size of reservationsList
   */
  public int size(){
    return reservationsList.size();
  }

  /**
   * Replace a reservation with particular ID with the given reservation.
   * If a reservation with the ID is not found in the list, the given reservation is added on the end of the list instead.
   * @param reservation reservation by which is the current reservation replaced.
   * @param ID the ID of the reservation that will is replaced.
   */
  public void setReservationByID(Reservation reservation, int ID){
    boolean found = false;
    for (int i = 0; i < size(); i++) {
      if(reservationsList.get(i).getID() == ID){
        reservationsList.set(i, reservation);
        found = true;
        break;
      }
    }
    if (!found)
    {
      reservationsList.add(reservation);
    }
  }

  /**
   *
   * @param ID ID of the reservation that is returned
   * @throws RuntimeException if the reservation with given ID is not in the list
   * @return the reservation whose ID equals the given one
   */
  public Reservation getReservationByID( int ID) {
    for (int i = 0; i < size(); i++) {
      if (reservationsList.get(i).getID() == ID) {
        return reservationsList.get(i);
      }
    }
    throw new RuntimeException("Reservation with such ID is not in the list");
  }

  /**
   *
   * @param ID ID of the game that is reserved
   * @return new reservations list with only reservation from the original list which has the same gameID as the given one
   */
  public ReservationsList getReservationByGameID(int ID){
    ReservationsList reservationsList1 = new ReservationsList();
    for (int i = 0; i < size(); i++)
    {
      if (getReservation(i).getGameID() == ID){
        reservationsList1.addReservation(getReservation(i));
      }
    }
    return reservationsList1;
  }

  /**
   *
   * @return new ArrayList of copies of all Reservation objects from the original list
   */
  public ArrayList<Reservation> getReservationsAsArrayList(){ //this is composition
    ArrayList<Reservation> reservations = new ArrayList<>();
    for (int i = 0; i < size(); i++)
    {
      reservations.add(getReservation(i).copy());
    }
    return reservations;
  }

  /**
   *
   * @param name string that name of the player who reserve the game or name of the game which is reserved must include
   * @param boardGamesList the boardGamesList from which is the board game name get
   * @param playersList the playersList from which is the player name get
   * @return a new ReservationsList with only reservations from the original list whose name of the player who reserve the game or name of the game which is reserved contains the given name string
   */
  public ReservationsList filterByName(String name, BoardGamesList boardGamesList, PlayersList playersList){
    ReservationsList reservationsList1 = new ReservationsList();
    for (int i = 0; i < size(); i++)
    {
      if( boardGamesList.getBoardGameByID(getReservation(i).getGameID()).getName().toLowerCase().contains(name.trim()) || playersList.getNameByID(getReservation(i).getPlayerID()).toLowerCase().contains(name.trim())){
        reservationsList1.addReservation(getReservation(i));
      }
    }
    return reservationsList1;
  }

  /**
   *
   * @param ID ID of the game that is reserved
   * @return a new Arraylist sorted by the from attribute with only reservation from the original list which has the same gameID as the given one
   */
  public ArrayList<Reservation> getSortedArrayListByGameID(int ID){

    ArrayList<Reservation> reservationsList1 = getReservationByGameID(ID).getReservationsAsArrayList();
    reservationsList1.sort(Comparator.comparing(Reservation::getFrom));

    return reservationsList1;

  }

  /**
   *
   * @return values of all the attributes of all the reservations as string
   */
  @Override public String toString()
  {
    return "Model.ReservationsList{" + "reservationsList=" + reservationsList + '}';
  }
}
