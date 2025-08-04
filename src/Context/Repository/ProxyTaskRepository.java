package Context.Repository;

import java.util.ArrayList;
import java.util.List;

import App.Ports.IPortTaskTracker;
import App.Ports.ITask;

public class ProxyTaskRepository implements IPortTaskTracker {

  private static IRepository fileRepository;
  // private static MemoryTaskRepository memRepository = new
  // MemoryTaskRepository();

  public ProxyTaskRepository() {
    try {
      fileRepository = new FileTaskRepository();
    } catch (Exception e) {
      System.out.println("Error in ProxyTaskRepository");
    }
  }

  public void add(String taskJsonData) {
    // memRepository.add(newTask);
    // ITask task = new ITask();
    fileRepository.add(taskJsonData);
  }

  // public void update(int id, String newDescription) {
  // memRepository.update(id, newDescription);
  // }

  // public void remove(int id) {
  // memRepository.remove(id);
  // }

  // public Task read(int id) {
  // return memRepository.read(id);
  // }

  public ArrayList<ITask> getAllTask() {
    List<String> taskLikeString = fileRepository.getAll();
    ArrayList<ITask> dataToReturn = new ArrayList<ITask>();

    for (String taskString : taskLikeString) {
      dataToReturn.add(new IRepositoryTask(taskString));
    }

    return dataToReturn;
  }

}