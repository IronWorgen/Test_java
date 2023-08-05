package model;

import java.util.List;

public interface iModel {
    /**
     * получить список игрушек из файла
     * @return - список игрушек
     */
    List<Toy> getToyList();

    /**
     * добавить новую игрушку в файл
     * @param newToy - игрушка которую надо добавить
     */
    boolean addToy(Toy newToy);

    /**
     * сохранить список игрушек в файл
     * @param toyList - список для сохранения
     * @return true -> успешно / false -> ошибка
     */
    boolean saveToyList(List<Toy> toyList);

    /**
     * добавить победителя розыгрыша в очередь на отправку
     * @param toy - победитель розыгрыша
     */
    void addWinnerInQueue(Toy toy);


    /**
     * добавить победителя в файл
     */
    boolean saveWinnerInFile();

}
