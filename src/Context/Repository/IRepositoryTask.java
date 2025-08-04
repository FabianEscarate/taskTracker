package Context.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import App.Ports.ITask;
import utils.DateFormats;

enum Status {
  PENDING, IN_PROGRESS, COMPLETED
}

enum IRepositoryTaskFields {
  id, description, status, createAt, updateAt
}

public class IRepositoryTask implements ITask {
  private Integer id;
  private String description;
  private Status status;
  private Date createAt;
  private Date updateAt;

  public IRepositoryTask(Integer id, String description, Status status, Date createAt, Date updateAt) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public IRepositoryTask(String jsonData) {
    Pattern pattern = Pattern.compile(
        "(?<=\\{\"id\":)\\d*(?=,)|(?<=\"description\":\").+(?=\",\"status)|(?<=\"status\":\").*(?=\",\"createAt)|(?<=\"createAt\":\").*(?=\",\"updateAt)|(?<=\"updateAt\":\").*(?=\"\\})");
    Matcher matcher = pattern.matcher(jsonData);
    IRepositoryTaskFields[] fieldsNames = IRepositoryTaskFields.values();
    Map<IRepositoryTaskFields, Object> keyValueTask = new HashMap<>();

    int indexField = 0;
    while (matcher.find()) {
      keyValueTask.put(fieldsNames[indexField], matcher.group(0));
      indexField++;
    }

    this.id = Integer.parseInt(keyValueTask.get(fieldsNames[0]).toString());
    this.description = keyValueTask.get(fieldsNames[1]).toString();
    this.status = Status.valueOf(keyValueTask.get(fieldsNames[2]).toString());
    this.createAt = DateFormats.parse(keyValueTask.get(fieldsNames[3]).toString());
    this.updateAt = DateFormats.parse(keyValueTask.get(fieldsNames[4]).toString());
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
