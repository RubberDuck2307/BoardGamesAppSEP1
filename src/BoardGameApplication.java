

import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;

public class BoardGameApplication extends javafx.application.Application
{
  public void start(Stage stage)
      throws TransformerConfigurationException, ParserConfigurationException,
      IOException, SAXException
  {
    ModelManager gradeListModelManager = new ModelManager();
    ViewHandler viewHandler = new ViewHandler(gradeListModelManager);
    viewHandler.start(stage);
  }


}
