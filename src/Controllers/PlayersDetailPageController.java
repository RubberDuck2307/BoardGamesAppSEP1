package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import Model.PlayerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class PlayersDetailPageController implements Controller
{
  public TextArea commentField;
  public TextField nameField;
  public TextField phoneNumberField;
  public TextField emailField;
  public TextField addressField;
  public DatePicker paymentField;
  public CheckBox membershipBox;
  public CheckBox votedBox;
  public Button editButton;
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
  public void setData(){
    Player player = model.getPlayersList().getPlayer(ID);
    nameField.setText(player.getName());
    commentField.setText(player.getComment());
    emailField.setText(player.getEmail());
    phoneNumberField.setText(player.getPhoneNumber());
    addressField.setText(player.getAddress());
    nameField.setDisable(true);
    commentField.setDisable(true);
    emailField.setDisable(true);
    phoneNumberField.setDisable(true);
    addressField.setDisable(true);
    if(player.isMembership()){
      membershipBox.setSelected(true);
    }
    membershipBox.setDisable(true);
    votedBox.setDisable(true);

    // TODO implemet feepayment date -- paymentField.setDayCellFactory(Player.get,22);
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
    nameField.setDisable(false);
    commentField.setDisable(false);
    emailField.setDisable(false);
    phoneNumberField.setDisable(false);
    addressField.setDisable(false);
    membershipBox.setDisable(false);
    votedBox.setDisable(false);
    editButton.setText("Save Changes");

    save = new EventHandler()
    {
      @Override public void handle(Event event)
      {
        System.out.println(paymentField.getTranslateX());
      }
    };

    editButton.setOnAction(save);

  }
}
