package Controllers;

import Application.ViewHandler;
import Model.BorrowingsList;
import Model.ModelManager;
import Model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationAddFinalFormController implements ExtendedController
{
  private Region region;
  private ViewHandler viewHandler;
  private ModelManager model;
  int playerID;
  int boardGameID;

  @FXML public TextField nameGameField;
  @FXML public TextField namePlayerField;
  @FXML public TextArea commentField;
  @FXML public TextField durationField;
  @FXML public DatePicker startingDate;
  @FXML public DatePicker endingDate;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID, int ID2)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.region = region;
    playerID = ID;
    boardGameID = ID2;

    namePlayerField.setText(model.getPlayersList().getNameByID(playerID));
    nameGameField.setText(model.getBoardGamesList().getNameByID(boardGameID));
    nameGameField.setMouseTransparent(true);
    nameGameField.setFocusTraversable(false);
    namePlayerField.setFocusTraversable(false);
    namePlayerField.setMouseTransparent(true);

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @FXML public void ASAP()
  {
    boolean valid = true; //2
    try
    {
      Long.parseLong(durationField.getText()); // 2 Trying to convert inputted data to int
    }
    catch (Exception e) //If data is not a number, a pop-up error is shown
        //This does not execute in the worst case
    {
      Alert alert = new Alert(Alert.AlertType.ERROR); //2
      alert.setTitle("Invalid Data"); //1
      alert.setHeaderText("Duration can be only number"); //1
      Optional<ButtonType> buttonType = alert.showAndWait(); //2
      valid = false; //2
    }

    if (valid) //1 If data was number, the execution of method continues
    {
      Reservation borrowing = model.getBorrowingsList() //Return a record of borrowing the game
          .getBorrowingByGameID(boardGameID); // 3 + n
      ArrayList<Reservation> reservations = model.getReservationsList()
          .getSortedArrayListByGameID(boardGameID); // 4 + n * log(n)
      //Return arrayList of reservations of the game sorted by the starting day
      if (borrowing != null) //1
      {
        reservations.add(0, borrowing); //n If there is a record about
        // borrowing, it is added at the beginning of the arrayList
      }
      if (reservations.size() == 0) //2 This does not execute in the worst case
      {//If there is no reservation or borrowing, the starting day is set to today
        // and the ending day se set to today + the duration of the reservation
        startingDate.setValue(LocalDate.now()); //2
        endingDate.setValue(
            LocalDate.now().plusDays(Long.parseLong(durationField.getText())));//5
      }

      else
      {
        Period firstPeriod = Period.between(LocalDate.now(),
            reservations.get(0).getFrom());//5
        if (firstPeriod.getDays() >= Long.parseLong(durationField.getText())) //4
        {//Checks if there is enough days between today and the first reservation to create a new one
          // and if so, set the dates
          //This does not execute in the worst case
          startingDate.setValue(LocalDate.now());//2
          endingDate.setValue(LocalDate.now()
              .plusDays(Long.parseLong(durationField.getText()))); //5
        }
        else
        { //Lops through all reservations and checks whether there is sufficient amount of days for
          // creating a reservation between the start of one and end of the next one and if so,
          // set dates
          boolean set = false;//2
          for (int i = 1; i < reservations.size(); i++) //2 + 2n - 3
          {
            Period period = Period.between(reservations.get(i - 1).getTo(),
                reservations.get(i).getFrom()); //7n
            if (period.getDays() >= Long.parseLong(durationField.getText())) //4n
            { //This does not execute in the worst case
              startingDate.setValue(reservations.get(i - 1).getTo()); //3
              endingDate.setValue(reservations.get(i - 1).getTo() //6
                  .plusDays(Long.parseLong(durationField.getText())));
              set = true; //1
              break;
            }
          }
          if (!set) //1 If the space for the new reservation is not found,
            //the dates are set after the end of the last reservation
          {
            startingDate.setValue(
                reservations.get(reservations.size() - 1).getTo()); //4
            endingDate.setValue(
                reservations.get(reservations.size() - 1).getTo() //7
                    .plusDays(Long.parseLong(durationField.getText())));
          }
        }
      }
    }
  }
  // The worst scenario is when the game is borrowed and reserved and there is not
  // a big enough gap between two reservation to insert a new one
  // T(n) = 39 + 15n + n * log(n)) after deleting the constants and choosing the fastest
  // increasing part we get:
  // T(n) = O(n * log(n))
  // We chose this algorithm because it is the one most complex algorithms in the program

  @FXML public void reserveGame()
      throws ParserConfigurationException, TransformerException
  {

    try
    {
      if (Reservation.VALIDATE_DATA(
          model.getReservationsList().getReservationByGameID(boardGameID),
          model.getBorrowingsList().getByGameID(boardGameID),
          startingDate.getValue(), endingDate.getValue(), boardGameID,
          model.getPlayersList(), playerID , -1,-1))
        ;
      {

        Reservation reservation = new Reservation(playerID, boardGameID,
            startingDate.getValue(), endingDate.getValue(),
            commentField.getText());
        model.getReservationsList().addReservation(reservation);
        model.saveReservation();
        viewHandler.openView(5, -1,-1);
      }
    }
    catch (Exception e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Data");
      alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
    }
  }

  @FXML public void goBack()
  {
    viewHandler.openView(203, playerID);
  }

  @Override public void reset()
  {

  }
}
