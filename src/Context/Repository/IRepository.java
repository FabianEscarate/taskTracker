package Context.Repository;

import java.util.List;

public interface IRepository {

  public void add(String jsonTask);
  public List<String> getAll();
}