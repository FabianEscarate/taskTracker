package Context.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import App.Ports.IPortTaskTracker;
import App.Ports.ITask;
import Context.Repository.Interfaces.IRepository;
import Context.Repository.Interfaces.IRepositoryTask;

public class ProxyTaskRepository implements IPortTaskTracker {

  private static IRepository fileRepository;
  // private static MemoryTaskRepository memRepository = new
  // MemoryTaskRepository();

  public ProxyTaskRepository() {
    try {
      fileRepository = new FileTaskRepository();
    } catch (Exception e) {
      System.out.println(String.format("Error in ProxyTaskRepository: %s", e));
    }
  }

  public void add(String taskJsonData) {
    fileRepository.add(taskJsonData);
  }

  public ITask getOneById(Integer id) {
    String jsonTask = fileRepository.getOneById(id);
    if (jsonTask == null) {
      System.out.println("Tarea no encontrada");
      return null;
    }

    return new IRepositoryTask(jsonTask);
  }

  public ITask update(Integer id, String jsonTask) {
    IRepositoryTask task = new IRepositoryTask(jsonTask);
    task.setUpdateAt(new Date());

    Boolean result = fileRepository.update(id, task.toJson());
    if (!result) {
      System.out.println("Tarea no actualizada");
      return null;
    }

    return task;
  }

  public ArrayList<ITask> getAllTask() {
    List<String> taskLikeString = fileRepository.getAll();
    ArrayList<ITask> dataToReturn = new ArrayList<ITask>();

    for (String taskString : taskLikeString) {
      dataToReturn.add(new IRepositoryTask(taskString));
    }

    return dataToReturn;
  }

  public ArrayList<ITask> getInProgressTask() {
    List<String> taskLikeString = fileRepository.getRecordsByParameter("\"status\":\"IN_PROGRESS\"");
    ArrayList<ITask> dataToReturn = new ArrayList<ITask>();

    for (String taskString : taskLikeString) {
      dataToReturn.add(new IRepositoryTask(taskString));
    }

    return dataToReturn;
  }

  public ArrayList<ITask> getPendingTask() {
    List<String> taskLikeString = fileRepository.getRecordsByParameter("\\\"status\\\":\\\"PENDING\\\"");
    ArrayList<ITask> dataToReturn = new ArrayList<ITask>();

    for (String taskString : taskLikeString) {
      dataToReturn.add(new IRepositoryTask(taskString));
    }

    return dataToReturn;
  }

  public ArrayList<ITask> getCompleteTask() {
    List<String> taskLikeString = fileRepository.getRecordsByParameter("\"status\":\"COMPLETE\"");
    ArrayList<ITask> dataToReturn = new ArrayList<ITask>();

    for (String taskString : taskLikeString) {
      dataToReturn.add(new IRepositoryTask(taskString));
    }

    return dataToReturn;
  }

}