package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class representing an Election
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */
public class Election
{
  private LocalDate startingDate;
  private LocalDate endingDate;

  /**
   * The two-argument constructor
   * @param startingDate the starting date of the election
   * @param endingDate the ending date of the election
   */
  public Election(LocalDate startingDate, LocalDate endingDate){

    this.startingDate = startingDate;
    this.endingDate = endingDate;
  }

  /**
   *
   * @param startingDate the starting date of the election
   * @param endingDate the ending date of the election
   * @throws RuntimeException if the starting date is not in the future
   * @throws RuntimeException if the ending date is not after the starting date
   * @return true, if the starting date is in the future and the ending date is after the starting date
   */
  public static boolean VALIDATE_DATA(LocalDate startingDate, LocalDate endingDate){
    if(!startingDate.isAfter(LocalDate.now())){
      throw new RuntimeException("Election has to start in future");
    }
    if (!endingDate.isAfter(startingDate)){
      throw new RuntimeException("Election has to end after it starts");
    }
    return true;
  }

  /**
   * The getter for startingDate
   */
  public LocalDate getStartingDate()
  {
    return startingDate;
  }

  /**
   * The setter for startingDate
   */
  public void setStartingDate(LocalDate startingDate)
  {
    this.startingDate = startingDate;
  }

  /**
   * The getter for endingDate
   */
  public LocalDate getEndingDate()
  {
    return endingDate;
  }

  /**
   * The setter for endingDate
   */

  public void setEndingDate(LocalDate endingDate)
  {
    this.endingDate = endingDate;
  }

  /**
   *
   * @return the values of all attributes as string
   */
  @Override public String toString()
  {
    return "Model.Election{" + "startingDate=" + startingDate + ", endingDate="
        + endingDate + '}';
  }
}
