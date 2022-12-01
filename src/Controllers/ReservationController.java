package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationController implements Controller
{

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;
  @FXML private TableView<ReservationTable> reservationTable;
  @FXML public TableColumn<ReservationTable, String> boardGameName;
  @FXML public TableColumn<ReservationTable, String> memberName;
  @FXML public TableColumn<ReservationTable, String> from;
  @FXML public TableColumn<ReservationTable, String> to;
  @FXML public Button addButton;



  public ReservationController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;

    ObservableList<ReservationTable> reservationTables = FXCollections.observableArrayList();
    ReservationsList reservationsList;
    if (ID == -1)
    {

      reservationsList = model.getReservationsList();
    }

    else {
      reservationsList = model.getReservationsByPlayer(ID);
      addButton.setVisible(false);
    }
    for (int i = 0; i < reservationsList.size(); i++)
    {
      Reservation reservation = reservationsList.getReservation(i);
      String boardgame = model.getBoardGamesList()
          .getNameByID(reservation.getGameID());
      String player = model.getPlayersList()
          .getNameByID(reservation.getPlayerID());

      reservationTables.add(new ReservationTable(boardgame, player,
          String.valueOf(reservation.getFrom()),
          String.valueOf(reservation.getTo()), reservation.getID()));
    }

    boardGameName.setCellValueFactory(
        new PropertyValueFactory<>("boardGameName"));
    memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
    from.setCellValueFactory(new PropertyValueFactory<>("from"));
    to.setCellValueFactory(new PropertyValueFactory<>("to"));

    reservationTable.setItems(reservationTables);
  }

  @FXML public void backToHomePage()
  {
    if (ID == -1){

    viewHandler.openView(1, -1);
  }
  else {
    viewHandler.openView(8, ID);
    }
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
