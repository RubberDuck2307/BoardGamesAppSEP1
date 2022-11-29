package Model;

import java.time.LocalDateTime;

public class Election
{
  private LocalDateTime startingDate;
  private LocalDateTime endingDate;

  public Election(LocalDateTime startingDate, LocalDateTime endingDate){
    this.startingDate = startingDate;
    this.endingDate = endingDate;
  }

  public LocalDateTime getStartingDate()
  {
    return startingDate;
  }

  public void setStartingDate(LocalDateTime startingDate)
  {
    this.startingDate = startingDate;
  }

  public LocalDateTime getEndingDate()
  {
    return endingDate;
  }

  public void setEndingDate(LocalDateTime endingDate)
  {
    this.endingDate = endingDate;
  }

  @Override public String toString()
  {
    return "Model.Election{" + "startingDate=" + startingDate + ", endingDate="
        + endingDate + '}';
  }
}
