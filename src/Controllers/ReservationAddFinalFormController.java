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
    boolean valid = true;
    try
    {
      Long.parseLong(durationField.getText());
    }
    catch (Exception e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Data");
      alert.setHeaderText("Duration can be only number");
      Optional<ButtonType> buttonType = alert.showAndWait();
      valid = false;
    }

    if (valid)
    {

      Reservation borrowing = model.getBorrowingsList()
          .getBorrowingByGameID(boardGameID);
      ArrayList<Reservation> reservations = model.getReservationsList()
          .getSortedArrayListByGameID(boardGameID);

      if (borrowing != null)
      {
        reservations.add(0, borrowing);
      }
      System.out.println(reservations);
      if (reservations.size() == 0)
      {
        startingDate.setValue(LocalDate.now());
        endingDate.setValue(
            LocalDate.now().plusDays(Long.parseLong(durationField.getText())));
      }

      else
      {
        Period firstPeriod = Period.between(LocalDate.now(),
            reservations.get(0).getFrom());
        if (firstPeriod.getDays() >= Long.parseLong(durationField.getText()))
        {
          startingDate.setValue(LocalDate.now());
          endingDate.setValue(LocalDate.now()
              .plusDays(Long.parseLong(durationField.getText())));
        }
        else
        {
          boolean set = false;
          for (int i = 1; i < reservations.size(); i++)
          {
            Period period = Period.between(reservations.get(i - 1).getTo(),
                reservations.get(i).getFrom());
            System.out.println(period.getDays() + "The days between");
            if (period.getDays() >= Long.parseLong(durationField.getText()))
            {
              startingDate.setValue(reservations.get(i - 1).getTo());
              endingDate.setValue(reservations.get(i - 1).getTo()
                  .plusDays(Long.parseLong(durationField.getText())));
              set = true;
              break;
            }
          }
          if (!set)
          {
            startingDate.setValue(
                reservations.get(reservations.size() - 1).getTo());
            endingDate.setValue(
                reservations.get(reservations.size() - 1).getTo()
                    .plusDays(Long.parseLong(durationField.getText())));
          }
        }
      }
    }
  }

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
        System.out.println("LLLLLLL" + model.getReservationsList());
        model.saveReservation();
        viewHandler.openView(5, -1,-1);
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
      e.printStackTrace();
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
