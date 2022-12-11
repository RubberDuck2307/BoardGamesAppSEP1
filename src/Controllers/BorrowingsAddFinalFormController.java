package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.Optional;

public class BorrowingsAddFinalFormController implements ExtendedController
{
  public TextField bgname;
  public TextField playername;
  public DatePicker start;
  public DatePicker end;
  public TextArea comments;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;
  private int ID;
  private int ID2;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID, int ID2)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    this.ID2 = ID2;
    setData();
  }

  public void setData()
  {
    bgname.setMouseTransparent(true);
    bgname.setFocusTraversable(false);
    playername.setFocusTraversable(false);
    playername.setMouseTransparent(true);
    PlayersList playersList = model.getPlayersList();
    BoardGamesList boardGamesList = model.getBoardGamesList();
    bgname.setText(boardGamesList.getNameByID(ID2));
    playername.setText(playersList.getNameByID(ID));
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void goBack()
  {
    viewHandler.openView(134, ID);
  }

  public void createBorrowing()
      throws ParserConfigurationException, TransformerException
  {

    LocalDate startDate = start.getValue();
    LocalDate endDate = end.getValue();
    if (start.getValue() == null || end.getValue() == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Data");
      alert.setHeaderText("Dates are not filled");
      Optional<ButtonType> result = alert.showAndWait();
    }
    else
    {

      String comment = comments.getText();

      try
      {
        ReservationsList reservationsList = model.getReservationsList();
        BorrowingsList borrowingsList = model.getBorrowingsList();
        PlayersList playersList = model.getPlayersList();
        BoardGamesList boardGamesList = model.getBoardGamesList();

        if (Reservation.VALIDATE_DATA(reservationsList, borrowingsList,
            startDate, endDate, ID2, playersList, ID, -1, -1))
        {
          model.addBorrowing(
              new Reservation(ID, ID2, startDate, endDate, comment));
          model.saveBorrowing();
          viewHandler.openView(6, -1);
          model.getBoardGamesList().getBoardGameByID(ID2).setAvailabilityStatus(BoardGame.BORROWED_STATUS);
          model.saveBoardGames();
          try
          {
            model.saveBorrowing();
          }
          catch (ParserConfigurationException e)
          {
            throw new RuntimeException(e);
          }
          catch (TransformerException e)
          {
            throw new RuntimeException(e);
          }

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
  }



}
