package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Election
{
  private LocalDate startingDate;
  private LocalDate endingDate;

  public Election(LocalDate startingDate, LocalDate endingDate){

    this.startingDate = startingDate;
    this.endingDate = endingDate;
  }

  public static boolean VALIDATE_DATA(LocalDate startingDate, LocalDate endingDate){
    if(!startingDate.isAfter(LocalDate.now())){
      throw new RuntimeException("Election has to start in future");
    }
    if (!endingDate.isAfter(startingDate)){
      throw new RuntimeException("Election has to end after it starts");
    }
    return true;
  }
  public LocalDate getStartingDate()
  {
    return startingDate;
  }

  public void setStartingDate(LocalDate startingDate)
  {
    this.startingDate = startingDate;
  }

  public LocalDate getEndingDate()
  {
    return endingDate;
  }

  public void setEndingDate(LocalDate endingDate)
  {
    this.endingDate = endingDate;
  }

  @Override public String toString()
  {
    return "Model.Election{" + "startingDate=" + startingDate + ", endingDate="
        + endingDate + '}';
  }
}
