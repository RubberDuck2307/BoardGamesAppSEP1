package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import TableClasses.PlayerTable;
import Model.PlayersList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ElectionAddVoteSelectMemberController implements Controller
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

  public void fillTable()
  {
    playersInTable.clear();

    PlayersList playersList = model.getPlayersList().getMembers();

    playersList = playersList.filterPlayerList(searchField.getText());

    for (int i = 0; i < playersList.size(); i++)
    {
      System.out.println(playersList.getPlayer(i));
      Player player = playersList.getPlayer(i);
      if (!player.getVoted())
      {
        playersInTable.add(
            new PlayerTable(player.getName(), player.getPhoneNumber(),
                player.getEmail(), player.getID()));

      }
    }
  }

  @FXML public void goBack(){
    viewHandler.openView(4,-1);
  }

  @FXML public void choosePlayer(){
    if (membersTable.getSelectionModel().getSelectedItem() != null)
    {
      System.out.println(
          membersTable.getSelectionModel().getSelectedItem().getID());
      viewHandler.openView(34,
          membersTable.getSelectionModel().getSelectedItem().getID());
    }

  }
}
