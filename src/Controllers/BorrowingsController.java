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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class BorrowingsController implements Controller
{
  public TextField searchField;
  @FXML Button backButton;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  @FXML private TableView<BorrowingsTable> borrowingsTable;
  @FXML public TableColumn<BoardgameTable, String> boardGameName;
  @FXML public TableColumn<BoardgameTable, String> playerName;
  @FXML public TableColumn<BoardgameTable, String> from;
  @FXML public TableColumn<BoardgameTable, String> to;
  ObservableList<BorrowingsTable> borrowingsTables = FXCollections.observableArrayList();


  public BorrowingsController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    fillTable();


    /*for (int i = 0; i < model.getBorrowingsList().size(); i++)
    {
      System.out.println(model.getBorrowingsList().size());
      System.out.println(i);
      Reservation reservation = model.getBorrowingsList().getBorrowing(i);
      String boardgame = model.getBoardGamesList()
              .getNameByID(reservation.getGameID());
      String player = model.getPlayersList().getNameByID(
          reservation.getPlayerID());

      borrowingsTables.add(
                new BorrowingsTable(boardgame,
                      player,
                      String.valueOf(reservation.getFrom()),
                      String.valueOf(reservation.getTo()), reservation.getID()));
    }*/

    boardGameName.setCellValueFactory(new PropertyValueFactory<>("boardGameName"));
    playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
    from.setCellValueFactory(new PropertyValueFactory<>("from"));
    to.setCellValueFactory(new PropertyValueFactory<>("to"));
    borrowingsTable.setItems(borrowingsTables);
  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(1, -1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
  public void fillTable(){
    borrowingsTables.clear();
    BorrowingsList borrowingsList = model.getBorrowingsList();
    PlayersList playersList = model.getPlayersList();
    BoardGamesList boardGamesList = model.getBoardGamesList();
    borrowingsList = borrowingsList.filterBorrowingList(searchField.getText(), playersList,boardGamesList);
    for (int i = 0; i < borrowingsList.size(); i++)
    {


      Reservation borrowing = borrowingsList.getBorrowing(i);
      System.out.println(borrowing.toString());
      String from = borrowing.getFrom() + "";
      String to = borrowing.getTo() + "";
      System.out.println(borrowing.toString());
      borrowingsTables.add(
          new BorrowingsTable(boardGamesList.getBoardGameByID(borrowing.getGameID()).getName(), playersList.getPlayerByID(borrowing.getPlayerID()).getName()
              ,from, to, borrowing.getID()));


    }
  }

  public void chooseBorrowings()
  {
    if (borrowingsTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(131,
          borrowingsTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  public void addBorrowing()
  {
    viewHandler.openView(133,
        -1);
  }
}
