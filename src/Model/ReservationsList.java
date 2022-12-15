package Model;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class representing the list of reservations
 * @author Jan Metela
 * @version 1.0 - November 2022
 */
public class ReservationsList
{
  private ArrayList<Reservation> reservationsList;

  /**
   * The zero-argument constructor
   * sets the attribute reservationsList to new empty ArrayList
   */
  public ReservationsList()
  {
    reservationsList = new ArrayList<>();
  }
  /**
   * Adds a reservation into reservationList.
   * If the ID of the reservation is -1, it is set to the value of the last reservation's ID in the list, incremented by 1.
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
   * Removes a reservation by ID from the list
   * @param ID the ID of the reservation that is removed
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
   * @param index the index of the reservation
   * @return the reservation with the given index
   */
  public Reservation getReservation(int index)
  {
    return reservationsList.get(index);
  }

  /**
   *
   * @param ID the ID of the player reserving the game
   * @return a new reservations list showing only reservations matching with the player's ID
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
   * Removes the reservation with the given index from the list
   * @param index the index of the removed game
   */
  public void delete(int index){
    reservationsList.remove(index);
  }

  /**
   *
   * @return the size of reservationsList
   */
  public int size(){
    return reservationsList.size();
  }

  /**
   * Replaces a reservation having a particular ID with the given reservation.
   * If a reservation with the ID is not found in the list, the given reservation is added at the end of the list instead.
   * @param reservation the reservation that will replace the current reservation.
   * @param ID the ID of the reservation that will be replaced.
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
   * @param ID the ID of the reservation
   * @throws RuntimeException if the reservation with the given ID is not in the list
   * @return the reservation matching the given ID
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
   * @param ID the ID of the game that is reserved
   * @return a new reservations list showing only reservations matching with the given ID
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
   * @return a new ArrayList containing copies of all the Reservation objects
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
   * @param name the string containing either the name of the player reserving
   * or the name of the reserved game
   * @param boardGamesList the list containing all the board games
   * @param playersList the list containing all the players
   * @return a new ReservationsList showing only the reservations matching with the attribute name
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
   * @param ID the ID of the game that is reserved
   * @return a new Arraylist sorted by the attribute "from" showing only
   * reservations matching with the game's ID
   */
  public ArrayList<Reservation> getSortedArrayListByGameID(int ID){

    ArrayList<Reservation> reservationsList1 = getReservationByGameID(ID).getReservationsAsArrayList();
    reservationsList1.sort(Comparator.comparing(Reservation::getFrom));

    return reservationsList1;

  }

  /**
   *
   * @return the values of the attributes of all the reservations as string
   */
  @Override public String toString()
  {
    return "Model.ReservationsList{" + "reservationsList=" + reservationsList + '}';
  }
}
