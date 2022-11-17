import java.time.LocalDate;

public class Reservation extends Period
{
  private boolean ASAP;
  private int queue;

  public Reservation(int ID, int playerID, int gameID, LocalDate from,
      LocalDate to, String comment, boolean ASAP, int queue)
  {
    super(ID, playerID, gameID, from, to, comment);
    this.ASAP = ASAP;
    this.queue = queue;
  }

  public Reservation(int playerID, int gameID, LocalDate from, LocalDate to,
      String comment, boolean ASAP, int queue)
  {
    super(playerID, gameID, from, to, comment);
    this.ASAP = ASAP;
    this.queue = queue;
  }

  public boolean isASAP()
  {
    return ASAP;
  }

  public void setASAP(boolean ASAP)
  {
    this.ASAP = ASAP;
  }

  public int getQueue()
  {
    return queue;
  }

  public void setQueue(int queue)
  {
    this.queue = queue;
  }

  @Override public String toString()
  {
    return super.toString() + "Reservation{" + "ASAP=" + ASAP + ", queue=" + queue + '}';
  }
}
