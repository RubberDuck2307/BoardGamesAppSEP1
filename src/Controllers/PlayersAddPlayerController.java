package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Optional;

public class PlayersAddPlayerController implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  @FXML public TextField nameField;
  @FXML public TextField phoneNumberField;
  @FXML public TextField addressField;
  @FXML public TextField emailField;
  @FXML public TextArea commentField;
  @FXML public DatePicker feeDatePicker;
  @FXML public CheckBox membershipBox;


  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
   this.region = region;
   this.model = model;
   this.viewHandler = viewHandler;
   feeDatePicker.setDisable(true);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {
  }

  public void addPlayer()
  {
    try
    {
      if (Player.VALIDATE_DATA(nameField.getText(), phoneNumberField.getText(),
          emailField.getText()))
      {
        Player newPlayer = new Player(nameField.getText(),
            phoneNumberField.getText(), emailField.getText(),
            membershipBox.isSelected(), commentField.getText(),
            addressField.getText(), false, feeDatePicker.getValue());
        boolean existing = false;
        for (int i = 0; i < model.getPlayersList().size(); i++)
        {
          if (newPlayer.getName()
              .equals(model.getPlayersList().getPlayer(i).getName())
              && newPlayer.getPhoneNumber()
              .equals(model.getPlayersList().getPlayer(i).getPhoneNumber()))
          {
            existing = true;
          }
        }
        if (existing)
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("The player exists already!");
          alert.setHeaderText(
              "A player with the same data is already existing. Do you want to create it anyway?");
          Optional<ButtonType> result = alert.showAndWait();
          if (result.isPresent() && result.get() == ButtonType.OK)
          {
            model.addPlayer(newPlayer);
            viewHandler.openView(2, model.getPlayersList().size() - 1);
          }
          model.savePlayers();

        }
        else
        {
          model.addPlayer(newPlayer);
          model.savePlayers();
          viewHandler.openView(2, model.getPlayersList().size() - 1);
        }
      }

    }
    catch (Exception e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid data");
      alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
    }
  }

  public void goBack()
  {
    viewHandler.openView(2, -1);
  }

  public void switchFeePayment()
  {
    feeDatePicker.setDisable(!feeDatePicker.isDisable());
    if (!membershipBox.isSelected())
    {
      feeDatePicker.setValue(null);
    }
  }
}
