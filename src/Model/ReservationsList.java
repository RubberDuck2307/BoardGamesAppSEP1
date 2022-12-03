package Model;

import java.util.ArrayList;

public class ReservationsList
{
  private ArrayList<Reservation> reservationsList;

  public ReservationsList()
  {
    reservationsList = new ArrayList<>();
  }

  public ReservationsList(ArrayList<Reservation> reservations)
  {
    this.reservationsList = reservations;
  }

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

  public void deleteByID(int ID){
    for (int i = 0; i < reservationsList.size(); i++) {
      if(getReservation(i).getID() == ID){
        reservationsList.remove(i);
        break;
      }
    }

    }

  public Reservation getReservation(int index)
  {
    return reservationsList.get(index);
  }

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


  public void delete(int index){
    reservationsList.remove(index);
  }
  public int size(){
    return reservationsList.size();
  }

  public void setReservationByID(Reservation reservation, int ID){
    for (int i = 0; i < size(); i++) {
      if(reservationsList.get(i).getID() == ID){
        reservationsList.set(i, reservation);
        break;
      }

    }
  }

  public Reservation getReservationByID( int ID) {
    for (int i = 0; i < size(); i++) {
      if (reservationsList.get(i).getID() == ID) {
        return reservationsList.get(i);
      }
    }
    return reservationsList.get(0);
  }

  @Override public String toString()
  {
    return "Model.ReservationsList{" + "reservationsList=" + reservationsList + '}';
  }
}
