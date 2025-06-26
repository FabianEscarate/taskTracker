package Repository;

import java.nio.file.FileSystemException;
import java.util.ArrayList;
import Entities.Task;

public class ProxyTaskRepository {

  private static FileTaskRepository fileRepository;
  private static MemoryTaskRepository memRepository = new MemoryTaskRepository();

  public ProxyTaskRepository() {
    try {
      fileRepository = new FileTaskRepository();
    } catch (Exception e) {
      System.out.println("Error in ProxyTaskRepository");
    }

    fileRepository.loadDataIn(memRepository.getData());

  }

  public void add(Task newTask) {
    // memRepository.add(newTask);
    fileRepository.add(newTask);
  }

  public void update(int id, String newDescription) {
    memRepository.update(id, newDescription);
  }

  public void remove(int id) {
    memRepository.remove(id);
  }

  public Task read(int id) {
    return memRepository.read(id);
  }

  public ArrayList<Task> getTasks() {
    return memRepository.getData();
  }

}