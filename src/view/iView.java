package view;

import model.Toy;

import java.util.List;

public interface iView {
    /**
     * отобразить главное меню
     */
    void showMainFrame();

    /**
     * показать список игрушек
     */
    void showToyList(List<Toy> ToyList);

    /**
     * добавить игрушку
     */
    Toy addToy();

    /**
     * выбрать игрушку для редактирования
     * @return - название игрушки
     */
    String selectToyToEdit();

    /**
     * редактировать игрушку
     * @param toy - игрушка для редактирования
     * @return - отредактированная игрушка
     */
    Toy editToy(Toy toy);

    /**
     *
     * @param toy - победитель розыгрыша
     */
    void showDrawing(Toy toy);


    /**
     * показать сообщение об ошибке
     * @param message - текст сообщения
     */
    void showError(String message);

    /**
     * отобразить сообщение
     * @param message - текст сообщения
     */
    void showMessage(String message);


}
