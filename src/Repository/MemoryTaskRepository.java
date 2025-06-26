package Repository;

import java.util.ArrayList;
import Entities.Task;

public class MemoryTaskRepository {

  private ArrayList<Task> DATA = new ArrayList<Task>();

  public ArrayList<Task> getData(){
    return DATA;
  }

  public void add(Task newTask) {
    DATA.add(newTask);
  }

  public void update(int id, String newDescription) {
    DATA.get(id).setDescription(newDescription);
  }

  public void remove(int id) {
    DATA.remove(id);
  }

  public Task read(int id) {
    return DATA.get(id);
  }
}