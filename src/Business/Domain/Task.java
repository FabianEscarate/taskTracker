package Business.Domain;

import java.util.Date;

import Business.Ports.ITask;
import utils.jsonExtension;

enum TaskFields {
  id, description, status, createAt, updateAt
}

public class Task extends jsonExtension implements ITask{
  private Integer id;
  private String description;
  private Status status;
  private Date createAt;
  private Date updateAt;

  public Task() {
  }

  public Task(Integer id, String description, Status status, Date createAt, Date updateAt) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public Task(Integer id, String description) {
    this.id = 0;
    this.description = description;
    this.status = Status.PENDING; // Default status
    this.createAt = new Date();
    this.updateAt = new Date();
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status.name();
  }

  public void setStatus(String status) {
    this.status = Status.valueOf(status);
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public Date getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Date updateAt) {
    this.updateAt = updateAt;
  }
}
