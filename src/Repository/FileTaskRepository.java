package Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import Entities.Task;

public class FileTaskRepository {
  private String emptyFile = "[]";
  private String currentPath = System.getProperty("user.dir");
  private String PATH_FILE = "/localDatabase";
  private String FILE_NAME = "/data.json";
  private Boolean exists;
  private Integer lengthString;

  private String getAbsotutePath() {
    return String.format("%s%s%s", currentPath, PATH_FILE, FILE_NAME);
  }

  private void createFile() throws FileSystemException {
    File directory = new File(String.join("", this.currentPath, this.PATH_FILE));
    if (!directory.exists()) {
      if (directory.mkdirs()) {
        System.out.println("Directory created.");
      } else {
        throw new FileSystemException(String.format("Cant create %s folders", this.PATH_FILE));
      }
    }
    File jsonFile = new File(getAbsotutePath());
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

  private void setLengthString() throws IOException {
    this.lengthString = Files.readString(Paths.get(getAbsotutePath())).length();
  }

  public FileTaskRepository() throws IOException {
    File fileDatabase = new File(String.join("", currentPath, PATH_FILE, FILE_NAME));
    if (!fileDatabase.exists()) {
      createFile();
    }
    this.exists = true;
    setLengthString();
  }

  public void add(Task newTask) {
    try {
      String currentContent = Files.readString(Paths.get(getAbsotutePath()));
      
      Files.writeString(
        Paths.get(getAbsotutePath()), 
        String.format("%s%s%s", 
          currentContent.substring(0, this.lengthString - 1),
          this.lengthString < 5 ? newTask.toJson() : String.format(",%s", newTask.toJson()),
          "]"
        )
      );
        
      setLengthString();
    } catch (Exception e) {
      System.out.println("Error to write file");
      System.out.println(e);
    } catch (OutOfMemoryError e) {
      System.out.println("Time to move persistand data to database please.");

    }
  }
}