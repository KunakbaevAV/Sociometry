package backend;

import java.util.List;

public interface IBaseRespondents<T> {
    void createNewBase();

    void deleteTable();

    void insert(T respondent);

    void update(T respondent);

    List<Integer> getIdToName(String name);

    void delete(int id);

    void insertBatch(List<T> list);

    T get(int id);

    List<T> getAll();
}
