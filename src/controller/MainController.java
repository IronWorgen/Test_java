package controller;

import model.Model;
import model.Toy;
import model.iModel;
import view.ViewConsole;
import view.iView;

import java.util.*;

public class MainController {
    private Map<Integer, String> window;
    private iView view;
    private iModel model;
    /**
     * список игрушек
     */
    List<Toy> toyList;

    public MainController() {
        this.window = new HashMap<>();
        window.put(1, "Показать список игрушек");
        window.put(2, "Добавить игрушку");
        window.put(3, "Редактировать игрушку");
        window.put(4, "Начать розыгрыш");
        window.put(5, "Отправить приз победителю розыгрыша(сохранить в файл)");
        window.put(6, "Завершить работу");

        this.view = new ViewConsole(window);
        this.model = new Model();
    }


    public void run(){
        boolean flag = true;
        while (flag){
            view.showMainFrame();

            Scanner scanner = new Scanner(System.in);
            System.out.print(": ");
            int userInput=0;
            try{
                userInput = scanner.nextInt();
            }catch (InputMismatchException e){
                view.showError("Введите номер раздела(число)!");
                continue;
            }

            boolean inputIsOk=false;
            for (Integer key : window.keySet()) {
                if (key==userInput){
                    inputIsOk=true;
                }
            }
            if (inputIsOk){

                flag = functionSelect(userInput);

            }else {
                view.showError("Раздел №"+ userInput +" не найден!");
            }

        }
    }


    /**
     * выбор функции программы
     * @param userInput - номер функции
     * @return true -> если не выбрано завершение работы, false -> если выбрано завершение работы
     */
    private  boolean functionSelect(int userInput){

        switch (userInput){
            case 1://показать список игрушек
                toyList =  model.getToyList();
                if (toyList.size()==0){
                    view.showMessage("Список игрушек пуст!");
                    break;
                }
                view.showToyList(toyList);
                System.out.print("Нажмите 'enter' чтобы вернуться на главный экран");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                break;

            case 2:// добавить игрушку
                Toy newToy = view.addToy();
                if (newToy==null){
                    view.showMessage("Создание отменено");
                    break;
                }
                boolean addResult = model.addToy(newToy);
                if (addResult){
                    view.showMessage(String.format("Игрушка \"%s\" добавлена.", newToy.getName()));
                }else {
                    view.showError("Не удалось добавить игрушку");
                }
                break;

            case 3:// Редактировать игрушку

                String toyToEditName = view.selectToyToEdit();
                toyList = model.getToyList();
                int toyToEditIndex = toyIsFound(toyList, toyToEditName);
                if (toyToEditIndex>=0){
                    Toy toyToEdit = toyList.get(toyToEditIndex);
                    Toy editedToy = view.editToy(toyToEdit);
                    if (toyToEdit.compareTo(editedToy)!=0){
                        toyList.set(toyToEditIndex, editedToy);
                        if (model.saveToyList(toyList)){
                            view.showMessage("Изменения сохранены");
                        }else{
                            view.showError("Не удалось сохранить изменения");
                        }

                    }
                }else {
                    view.showError(String.format("Игрушка \"%s\" - не найдена!",toyToEditName));
                }
                break;

            case 4:// Начать розыгрыш
                toyList= model.getToyList();
                var  drawingWinner = startDrawing(toyList);
                if (drawingWinner==null){
                    break;
                }
                model.addWinnerInQueue(drawingWinner);
                view.showDrawing(drawingWinner);
                break;

            case  5://отправить приз победителю розыгрыша
                if (model.saveWinnerInFile()){
                    view.showMessage("игрушка отправлена победителю");
                }else {
                    view.showError("очередь на отправку пуста");
                }
                break;

            case 6:// Завершить работу
                System.out.println("До свидания");
                return false;
        }
        return  true;
    }

    /**
     * начать розыгрыш
     * @return - победитель розыгрыша
     */
    private Toy startDrawing (List<Toy> toyList)  {
        toyList = model.getToyList();
        List<Integer> randomDrawingList = new ArrayList<>(0);
        for (int i = 0; i < toyList.size(); i++) {
            if (toyList.get(i).getQuantity()==0){
                continue;
            }

            for (int j = 0; j < (int)(toyList.get(i).getDrop()*100); j++) {
                randomDrawingList.add(i);
            }
        }
        if ( randomDrawingList.size()>0) {
            Random random = new Random();
            int winnerIndex = randomDrawingList.get(random.nextInt(0, randomDrawingList.size()));
            Toy winner = toyList.get(winnerIndex);

            winner.setQuantity(winner.getQuantity() - 1);

            Toy winnerClone = winner.clone();
            winnerClone.setQuantity(1);
            return winnerClone;
        }else {
            view.showError("нет игрушек доступных для розыгрыша");
        }

        return null;


    }


    /**
     *
     * @param toyList - список игрушек, в котором будет выполнен поиск
     * @param toyName - название искомой игрушки
     * @return - игрушка найдена -> индекс игрушки в списке / игрушка не найдена -> -1
     */
    private int toyIsFound(List<Toy> toyList, String toyName){
        for (int i = 0; i < toyList.size(); i++) {
            if (toyList.get(i).getName().equals(toyName)){
                return i;
            }
        }
        return -1;
    }

}
