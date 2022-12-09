package Controllers;

import Application.ViewHandler;
import Model.*;
import TableClasses.PlayerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationAddSelectMemberController implements Controller
{


  @FXML public TextField searchField;
  @FXML public TableView<PlayerTable> membersTable;
  @FXML public TableColumn<PlayerTable, String> name;
  @FXML public TableColumn<PlayerTable, String> email;
  @FXML public TableColumn<PlayerTable, String> number;
  private ObservableList<PlayerTable> playersInTable = FXCollections.observableArrayList();
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

    fillTable();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    number.setCellValueFactory(new PropertyValueFactory<>("phone"));
    membersTable.setItems(playersInTable);


  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  @FXML public void goBack(){
    viewHandler.openView(5,-1, -1);
  }
  public void fillTable()
  {
    playersInTable.clear();

    PlayersList playersList = model.getPlayersList().getMembers();

    playersList = playersList.filterPlayerList(searchField.getText());

    for (int i = 0; i < playersList.size(); i++)
    {
      Player player = playersList.getPlayer(i);
        playersInTable.add(
            new PlayerTable(player.getName(), player.getPhoneNumber(),
                player.getEmail(), player.getID()));

    }
  }

  public void choosePlayer(){
    if (membersTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(203,
          membersTable.getSelectionModel().getSelectedItem().getID());
    }
  }
}
