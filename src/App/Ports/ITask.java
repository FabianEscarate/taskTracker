package App.Ports;

import java.util.Date;
import App.Domain.Status;

public interface ITask {
  Integer id = null;
  String description = null;
  Status status = null;
  Date createAt = null;
  Date updateAt = null;

  Integer getId();
  void setId(Integer id);

  String getDescription();
  void setDescription(String description);

  String getStatus();
  void setStatus(String status);

  Date getCreateAt();
  void setCreateAt(Date createAt);

  Date getUpdateAt();
  void setUpdateAt(Date updateAt);

}
