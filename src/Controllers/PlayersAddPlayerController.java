package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
      throws ParserConfigurationException, TransformerException
  {

    if(Player.validateData(nameField.getText(), phoneNumberField.getText(), emailField.getText())){
      model.addPlayer(new Player(nameField.getText(), phoneNumberField.getText(), emailField.getText(), membershipBox.isSelected(), commentField.getText(), addressField.getText(), false, feeDatePicker.getValue() ));
      model.savePlayers();
      viewHandler.openView(8,model.getPlayersList().size() -1);
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