package Business.Ports;

import java.util.ArrayList;

public interface IPortTaskTracker {
  public void add(String taskJsonData);

  public ArrayList<ITask> getAllTask();

  public ITask update(Integer id, String jsonTask);

  public ITask getOneById(Integer id);

  public ArrayList<ITask> getInProgressTask();

  public ArrayList<ITask> getPendingTask();

  public ArrayList<ITask> getCompleteTask();
}
