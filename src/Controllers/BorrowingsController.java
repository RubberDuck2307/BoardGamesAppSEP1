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

public class BorrowingsController implements Controller
{
  @FXML Button backButton;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  @FXML private TableView<BorrowingsTable> borrowingsTable;
  @FXML public TableColumn<BoardgameTable, String> boardGameName;
  @FXML public TableColumn<BoardgameTable, String> playerName;
  @FXML public TableColumn<BoardgameTable, String> from;
  @FXML public TableColumn<BoardgameTable, String> to;

  public BorrowingsController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

    ObservableList<BorrowingsTable> borrowingsTables = FXCollections.observableArrayList();

    for (int i = 0; i < model.getBorrowingsList().size(); i++)
    {
      System.out.println(model.getBorrowingsList().size());
      System.out.println(i);
      Reservation reservation = model.getBorrowingsList().getBorrowing(i);
      String boardgame = model.getBoardGamesList()
              .getNameByID(reservation.getGameID());
      String player = model.getPlayersList().getNameByID(reservation.getID());

      borrowingsTables.add(
                new BorrowingsTable(boardgame,
                      player,
                      String.valueOf(reservation.getFrom()),
                      String.valueOf(reservation.getTo()), reservation.getID()));
    }

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
}
