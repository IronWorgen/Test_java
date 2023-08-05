package view;

import model.Toy;

import java.sql.SQLOutput;
import java.util.*;

public class ViewConsole implements iView{
    private Map<Integer, String> window;

    public ViewConsole(Map <Integer, String> window ) {
        this.window = window ;



    }

    /**
     * отобразить главное меню
     */
    public void showMainFrame(){
        System.out.println("\nГлавное меню\n------------------------------------------------");
        System.out.println("Выберите номер раздела:");

        for (Map.Entry<Integer, String> integerStringEntry : window.entrySet()) {
            System.out.printf("%d. %s\n", integerStringEntry.getKey(), integerStringEntry.getValue());
        }
        System.out.println("------------------------------------------------");
    }


    @Override
    public void showToyList(List<Toy> toyList) {
        System.out.println("\nСписок игрушек:");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < toyList.size()-1; i++) {
            System.out.printf("%s. %s\n",i+1, toyList.get(i).getName() );
        }
        System.out.printf("%s. %s\n",toyList.size(), toyList.get(toyList.size()-1).getName() );
        System.out.println("------------------------------------------------");
    }

    @Override
    public Toy addToy() {

        System.out.println("\nСоздание новой игрушки(для отмены введите \'q\'):");
        System.out.println("------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название игрушки:\t");
        String toyName = scanner.nextLine().trim().toLowerCase();
        if (toyName.equals("q")){
            return  null;
        }

        boolean flag = true;
        int toyQuantity = 0;
        while (flag) {
            try {
                System.out.print("Введите количество игрушек:\t");
                scanner = new Scanner(System.in);
                toyQuantity = scanner.nextInt();
                if (toyQuantity>=0){
                    flag=false;
                }else {
                    showError("Количество не может быть отрицательным");
                }

            }catch (InputMismatchException e){
                showError("Введите целое число");
            }

        }
        double toyDrop = 0;
        flag = true;
        while (flag) {
            try {
                System.out.print("Введите шанс выпадения игрушки:\t");
                scanner = new Scanner(System.in);
                toyDrop = scanner.nextDouble();
                if (toyDrop>=1||toyDrop<0){
                    showError("Шанс выпадения должен быть в диапазоне [0,1)");
                }else{
                    flag =  false;
                }
            }catch (InputMismatchException e){
                showError("Введите дробное число (разделитель \",\")");
            }
        }

        System.out.println("\nСохранить следующую игрушку?(y/n)");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("Название: %s\n", toyName);
        System.out.printf("Количество: %d шт.\n", toyQuantity);
        System.out.printf("Шанс выпадения: %f.\n", toyDrop);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ");

        flag =true;
        while (flag) {
            System.out.print(": ");
            scanner = new Scanner(System.in);
            String saveToy = scanner.nextLine().trim().toLowerCase();
            if (saveToy.equals("n")) {
                return null;
            } else if (saveToy.equals("y")) {
                flag = false;
            }else{
                showError("Не верный ввод. чтобы сохранить игрушку введите \"y\", чтобы удалить введите \"n\"");
            }
        }
        Toy newToy = new Toy();
        newToy.setName(toyName);
        newToy.setQuantity(toyQuantity);
        newToy.setDrop(toyDrop);


        return newToy;
    }

    @Override
    public String selectToyToEdit() {
        System.out.print("Введите название игрушки:\t");
        Scanner scanner = new Scanner(System.in);
        String toyName = scanner.nextLine();
        return  toyName;
    }

    @Override
    public Toy editToy(Toy toy) {
        return null;
    }

    @Override
    public void showDrawing(Toy toy) {

    }

    @Override
    public void showMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print("~");
        }
        System.out.println("\n"+message);
        for (int i = 0; i < message.length(); i++) {
            System.out.print("~");
        }
        System.out.println("\n");

    }

    /**
     * показать сообщение об ошибке
     * @param message - текст сообщения
     */
    public void showError(String message){

        int SystemMessageLength = "\nОшибка!!!".length();
        for (int i = 0; i < message.length()+SystemMessageLength; i++) {
            System.out.print("=");
        }
        System.out.println("\nОшибка!!!"+message);
        for (int i = 0; i < message.length()+SystemMessageLength; i++) {
            System.out.print("=");
        }
        System.out.println("\n");

    }



}
