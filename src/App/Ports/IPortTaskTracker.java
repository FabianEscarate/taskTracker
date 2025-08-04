package App.Ports;

import java.util.ArrayList;


public interface IPortTaskTracker {
  public void add(String taskJsonData);
  public ArrayList<ITask> getAllTask();
  public ITask update(Integer id, String jsonTask);
  // public ArrayList<ITask> getPedingTask();
  public ITask getOneById(Integer id);
}
