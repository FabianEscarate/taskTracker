package Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Entities.Task;

public class FileTaskRepository {
  private String emptyFile = "[]";
  private String currentPath = System.getProperty("user.dir");
  private String PATH_FILE = "/localDatabase";
  private String FILE_NAME = "/data.json";
  private Boolean exists;
  private Integer countedBytes;

  private void createFile() throws FileSystemException {
    File directory = new File(String.join("", this.currentPath, this.PATH_FILE));
    if (!directory.exists()) {
      if (directory.mkdirs()) {
        System.out.println("Directory created.");
      } else {
        throw new FileSystemException(String.format("Cant create %s folders", this.PATH_FILE));
      }
    }
    File jsonFile = new File(String.join("", currentPath, PATH_FILE, FILE_NAME));
    if (!jsonFile.exists()) {
      try {
        Files.write(jsonFile.toPath(), this.emptyFile.getBytes(StandardCharsets.UTF_8));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return;
  }

  private void countBytes () throws IOException{
    String fileContent = Files.readString(Paths.get(String.format("%s%s%s", currentPath, PATH_FILE, FILE_NAME))); 
    this.countedBytes = fileContent.length();
  }

  public FileTaskRepository() throws IOException {
    File fileDatabase = new File(String.join("", currentPath, PATH_FILE, FILE_NAME));
    if (!fileDatabase.exists()) {
      createFile();
    }
    this.exists = true;
    countBytes();
  }

  public <T> void loadDataIn(T variableToload) {

  }

  public void add(Task newTask) {
    System.out.println(newTask.toJson());
  }
}