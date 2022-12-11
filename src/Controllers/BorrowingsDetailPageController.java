package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.Optional;

public class BorrowingsDetailPageController implements Controller
{
  public Reservation reservation;
  public TextField nameOfBoardgame;
  public Button deleteButton;
  public Button editButton;
  public TextField nameOfPlayer;
  public DatePicker start;
  public DatePicker end;
  public TextArea comments;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;
  private EventHandler save;
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    setData();
  }
  public void setData()
  {
    Reservation borrowing = model.getBorrowingsList().getBorrowingByID(ID);
    BoardGamesList boardGamesList = model.getBoardGamesList();
    PlayersList playersList = model.getPlayersList();
    nameOfBoardgame.setText(boardGamesList.getNameByID(borrowing.getGameID()));
    nameOfPlayer.setText(playersList.getNameByID(borrowing.getPlayerID()) + " " + playersList.getPlayerByID(
        borrowing.getPlayerID()).getPhoneNumber());
    start.setValue(borrowing.getFrom());
    end.setValue(borrowing.getTo());
    comments.setText(borrowing.getComment());
    nameOfBoardgame.setMouseTransparent(true);
    nameOfPlayer.setMouseTransparent(true);
    start.setMouseTransparent(true);
    end.setMouseTransparent(true);
    comments.setMouseTransparent(true);

    nameOfBoardgame.setFocusTraversable(false);
    nameOfPlayer.setFocusTraversable(false);
    start.setFocusTraversable(false);
    end.setFocusTraversable(false);
    comments.setFocusTraversable(false);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void edit(ActionEvent actionEvent)
  {
    Reservation borrowing = model.getBorrowingsList().getBorrowingByID(ID);
    start.setMouseTransparent(false);
    end.setMouseTransparent(false);
    comments.setMouseTransparent(false);

    start.setFocusTraversable(true);
    end.setFocusTraversable(true);
    comments.setFocusTraversable(true);
    editButton.setText("Save Changes");
    save = event -> {
      int nameOfBG = borrowing.getGameID() ;
      int nameOfP = borrowing.getPlayerID();
      LocalDate startDate = start.getValue();
      LocalDate endDate = end.getValue();
      String comment = comments.getText();
      try
      {
        ReservationsList reservationsList = model.getReservationsList();
        BorrowingsList borrowingsList = model.getBorrowingsList();
        BoardGamesList boardGamesList = model.getBoardGamesList();
        PlayersList playersList = model.getPlayersList();

      if (Reservation.VALIDATE_DATA(reservationsList, borrowingsList,startDate, endDate, nameOfBG,playersList, nameOfP,-1,ID))
      {
        Reservation reservation1 = new Reservation(ID,nameOfP,nameOfBG,startDate,endDate, comment);
        model.setBorrowing(reservation1, ID);
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
        setData();
        editButton.setOnAction(this::edit);
        editButton.setText("Edit");

      }
      }
    catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Invalid Data");
          alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
        }

    };

    editButton.setOnAction(save);

  }

  public void delete() throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The record about the borrowing is going to be deleted");
    Optional<ButtonType> result = alert.showAndWait();
    if(result.isPresent() && result.get() == ButtonType.OK){
      model.getBoardGamesList().getBoardGameByID(model.getBorrowingsList().getBorrowingByID(ID).getGameID()).setAvailabilityStatus(BoardGame.AVAILABLE_STATUS);
      model.getBorrowingsList().deleteByID(ID);
      model.saveBorrowing();
      model.saveBoardGames();
      viewHandler.openView(6,-1);}

  }

  public void showMemberInfo(){
    viewHandler.openView(8, model.getBorrowingsList().getBorrowingByID(ID).getPlayerID());
  }

  public void showBoardGameInfo(){
    viewHandler.openView(9, model.getBorrowingsList().getBorrowingByID(ID).getGameID());
  }
  public void goBack()
  {
    viewHandler.openView(6,-1);
  }
}
