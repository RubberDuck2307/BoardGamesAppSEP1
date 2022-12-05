package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationController implements ExtendedController
{

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int playerID;
  private int gameID;
  @FXML private TableView<ReservationTable> reservationTable;
  @FXML public TableColumn<ReservationTable, String> boardGameName;
  @FXML public TableColumn<ReservationTable, String> memberName;
  @FXML public TableColumn<ReservationTable, String> from;
  @FXML public TableColumn<ReservationTable, String> to;
  @FXML public Button addButton;

  @FXML public TextField searchField;

  ObservableList<ReservationTable> reservationTables;

  public ReservationController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID, int ID2)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    playerID = ID;
    gameID = ID2;

    reservationTables = FXCollections.observableArrayList();
    fillTable();

    boardGameName.setCellValueFactory(
        new PropertyValueFactory<>("boardGameName"));
    memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
    from.setCellValueFactory(new PropertyValueFactory<>("from"));
    to.setCellValueFactory(new PropertyValueFactory<>("to"));

    reservationTable.setItems(reservationTables);
  }

  @FXML public void backToHomePage()
  {
    if (gameID == -1 && playerID == -1)
    {

      viewHandler.openView(1, -1);
    }
    else if (gameID != -1)
    {
      viewHandler.openView(9,gameID);
    }

    else
    {
      viewHandler.openView(8, playerID);
    }
  }

  public void chooseReservation()
  {
    if (reservationTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(201,
          reservationTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  @FXML public void addReservation()
  {
    viewHandler.openView(202, -1);
  }

  public void fillTable()
  {
    reservationTables.clear();
    ReservationsList reservationsList;
    if (gameID == -1 && playerID == -1)
    {

      reservationsList = model.getReservationsList();
    }

    else if (gameID != -1 && playerID == -1)
    {
      reservationsList = model.getReservationsList();
      reservationsList = reservationsList
          .getReservationByGameID(gameID);
      addButton.setVisible(false);
    }

    else
    {
      reservationsList = model.getReservationsByPlayer(playerID);
      addButton.setVisible(false);
    }

    reservationsList = reservationsList.filterByName(searchField.getText(),
        model.getBoardGamesList(), model.getPlayersList());

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

  }

}
