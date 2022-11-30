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
      if (Player.validateData(nameField.getText(), phoneNumberField.getText(),
          emailField.getText()))
      {
        model.addPlayer(new Player(nameField.getText(), phoneNumberField.getText(),
            emailField.getText(), membershipBox.isSelected(), commentField.getText(), addressField.getText(), false,
            feeDatePicker.getValue()));
        model.savePlayers();
        viewHandler.openView(8, model.getPlayersList().size() - 1);
      }
    }
    catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid data");
      alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
    }
  }
  public void goBack(){
    viewHandler.openView(2,-1);
  }

  public void switchFeePayment()
  {
    feeDatePicker.setDisable(!feeDatePicker.isDisable());
  }
}
