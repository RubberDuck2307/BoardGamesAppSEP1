import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class AddPlayerController implements Controller
{
  Region region;
  ModelManager model;
  ViewHandler viewHandler;
  @FXML TextField nameField;
  @FXML TextField addressField;
  @FXML TextField phoneNumberField;
  @FXML TextField emailField;
  @FXML TextArea commentField;
  @FXML CheckBox membershipBox;
  @FXML Button addButton;

  public AddPlayerController()
  {
  }

  public void init(Region region, ModelManager model, ViewHandler viewHandler)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
  }

  @FXML private void addPlayer()
      throws ParserConfigurationException, TransformerException
  {
    Player player = new Player(nameField.getText(), phoneNumberField.getText(),
        emailField.getText(), membershipBox.isSelected(), new ArrayList<>(),
        new ArrayList<>(), commentField.getText(), addressField.getText(),
        false);
    model.addPlayer(player);
    model.savePlayers();
  }

  public Region getRegion()
  {
    return region;
  }
}
