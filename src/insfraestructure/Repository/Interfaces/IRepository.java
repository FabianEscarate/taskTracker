package insfraestructure.Repository.Interfaces;

import java.util.List;


public interface IRepository {
  public void add(String jsonTask);
  public List<String> getAll();
  public String getOneById(Integer id);
  public Boolean update(Integer id, String newJsonTask);
  public List<String> getRecordsByParameter(String parameter);
}