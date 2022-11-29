package Controllers;

import Application.FileReader;
import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import Model.PlayerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class PlayersController implements Controller
{
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  @FXML private TableView<PlayerTable> playersTable;
  @FXML public TableColumn<PlayerTable, String> name;
  @FXML public TableColumn<PlayerTable, String> email;
  @FXML public TableColumn<PlayerTable, String> phone;
  @FXML public TableColumn<PlayerTable, String> membership;
  public PlayersController(){}
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

    ObservableList<PlayerTable> playersTables = FXCollections.observableArrayList(
    );

    for(int i = 0; i < model.getPlayersList().size(); i++){
      Player player = model.getPlayersList().getPlayer(i);
      String membership = "";
      if (player.isMembership()){
        membership = "Member";
      }
      else {
        membership = "Guest";
      }
      playersTables.add(new PlayerTable(player.getName(),player.getPhoneNumber(), player.getEmail(), membership));


    }


    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    membership.setCellValueFactory(new PropertyValueFactory<>("membership"));

    playersTable.setItems(playersTables);
  }


  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
