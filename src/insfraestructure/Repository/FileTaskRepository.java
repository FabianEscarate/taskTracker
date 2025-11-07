package insfraestructure.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import insfraestructure.Repository.Interfaces.IRepository;

public class FileTaskRepository implements IRepository {
  private String emptyFile = "[]";
  private String currentPath = System.getProperty("user.dir");
  private String PATH_FILE = "/localDatabase";
  private String FILE_NAME = "/data.json";
  private String AbsolutePathFile = String.format("%s%s%s", currentPath, PATH_FILE, FILE_NAME);
  private Integer lengthString;

  private String getContent() {
    try {
      return Files.readString(Paths.get(this.AbsolutePathFile));
    } catch (Exception e) {
      System.out.println("Error to write file");
    } catch (OutOfMemoryError e) {
      System.out.println("Time to move persistand data to database please.");
    }

    return null;
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
    File jsonFile = new File(this.AbsolutePathFile);
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
    this.lengthString = Files.readString(Paths.get(this.AbsolutePathFile)).length();
  }

  public FileTaskRepository() throws IOException {
    File fileDatabase = new File(String.join("", currentPath, PATH_FILE, FILE_NAME));
    if (!fileDatabase.exists()) {
      createFile();
    }
    setLengthString();
  }

  public void add(String jsonTask) {
    try {
      String currentContent = Files.readString(Paths.get(this.AbsolutePathFile));

      Files.writeString(Paths.get(this.AbsolutePathFile),
          String.format("%s%s%s", currentContent.substring(0, this.lengthString - 1),
              this.lengthString < 5 ? jsonTask : String.format(",%s", jsonTask), "]"));

      setLengthString();
    } catch (Exception e) {
      System.out.println("Error to write file");
      System.out.println(e);
    } catch (OutOfMemoryError e) {
      System.out.println("Time to move persistand data to database please.");

    }
  }

  public List<String> getAll() {
    List<String> allData = new ArrayList<String>();
    String fileContent = this.getContent();
    Pattern pattern = Pattern.compile("\\{.*?\\}");
    Matcher matcher = pattern.matcher(fileContent);

    while (matcher.find()) {
      allData.add(matcher.group(0));
    }

    return allData;
  }

  public String getOneById(Integer id) {
    String finded = "";
    String fileContent = this.getContent();
    Pattern pattern = Pattern.compile(String.format("\\{\"id\":%d,.*?\\}", id));
    Matcher matcher = pattern.matcher(fileContent);

    while (matcher.find()) {
      finded = matcher.group(0);
    }

    return finded;
  }

  public Boolean update(Integer id, String jsonTask) {
    String fileContent = this.getContent();
    Pattern pattern = Pattern.compile(String.format("\\{\"id\":%d,.*?\\}", id));
    Matcher matcher = pattern.matcher(fileContent);

    String newContent = matcher.replaceAll(jsonTask);
    try {
      Files.writeString(Paths.get(this.AbsolutePathFile), String.format("%s", newContent));
      setLengthString();
    } catch (Exception e) {
      System.out.println("Error to write file");
      System.out.println(e);
      return false;
    }

    return true;
  }

  public List<String> getRecordsByParameter(String query) {
    List<String> records = new ArrayList<String>();
    String fileContent = this.getContent();
    Pattern pattern = Pattern.compile(String.format("\\{[a-zA-Z\",\\s:\\d]+%s[a-zA-Z\",:\\s\\d]+\\}", query));
    Matcher matcher = pattern.matcher(fileContent);

    while (matcher.find()) {
      records.add(matcher.group(0));
    }

    return records;
  }
}