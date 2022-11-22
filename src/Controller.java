import javafx.scene.layout.Region;

public interface Controller
{
  public void init(Region region, ModelManager model, ViewHandler viewHandler);
  public Region getRegion();
  public void reset();
}
