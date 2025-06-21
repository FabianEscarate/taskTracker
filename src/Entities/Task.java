package Entities;
import java.util.Date;

public class Task {

  public Integer id;
  public String description;
  Status status;
  Date createAt;
  Date updateAt;

  public Task(String description) {
    this.id = (int) (Math.random() * 1000); // Simple ID generation for demonstration
    this.description = description;
    this.status = Status.PENDING; // Default status
    this.createAt = new Date();
    this.updateAt = new Date();
  }

  public void setDescription(String description){
    this.description = description;
  }

}