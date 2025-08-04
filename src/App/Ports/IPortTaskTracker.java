package App.Ports;

import java.util.ArrayList;


public interface IPortTaskTracker {
  public void add(String taskJsonData);
  public ArrayList<ITask> getAllTask();
}
