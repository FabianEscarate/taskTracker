package Entities;

import java.util.Date;
import utils.jsonExtension;

public class Task extends jsonExtension {

  public Integer id;
  public String description;
  Status status;
  Date createAt;
  Date updateAt;

  public Task(Integer id, String description, Status status, Date createAt, Date updateAt) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public Task(String description) {
    this.id = (int) (Math.random() * 1000); // Simple ID generation for demonstration
    this.description = description;
    this.status = Status.PENDING; // Default status
    this.createAt = new Date();
    this.updateAt = new Date();
  }

}