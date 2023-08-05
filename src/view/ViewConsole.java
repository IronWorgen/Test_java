package view;

import model.Toy;


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
            System.out.printf("%d. %s - %dшт.\n",i+1, toyList.get(i).getName(),toyList.get(i).getQuantity()  );
        }
        System.out.printf("%d. %s - %dшт.\n",toyList.size(), toyList.get(toyList.size()-1).getName(), toyList.get(toyList.size()-1).getQuantity());
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
        String toyName = scanner.nextLine().trim();
        return  toyName;
    }

    @Override
    public Toy editToy(Toy toy) {
        System.out.println("\nРедактирование игрушки:");
        System.out.println("--------------------------------------------");
        boolean flagEdit = true;

        Toy editedToy = toy.clone();
        boolean nameIsEdited = false;
        boolean quantityIsEdited = false;
        boolean dropIsEdited = false;

        while(flagEdit) {
            Scanner scanner;
            System.out.println("Выберите поле для редактирования:");
            System.out.printf("1. Название: %s\n",
                    nameIsEdited ? String.format("Изменено %s -> %s",toy.getName(), editedToy.getName()):
                            toy.getName());
            System.out.printf("2. Количество: %s\n",
                    quantityIsEdited ? String.format("Изменено %dшт. -> %dшт.",toy.getQuantity(), editedToy.getQuantity()):
                            toy.getQuantity());
            System.out.printf("3. Шанс выпадения: %s\n",
                    dropIsEdited ? String.format("Изменено %f -> %f",toy.getDrop(), editedToy.getDrop()):
                    toy.getDrop());
            System.out.println("4. Сохранить");
            System.out.println("5. Отмена ");
            System.out.println("--------------------------------------------");
            System.out.print(": ");


            boolean flag = true;
            int userInput = 0;
            while (flag) {
                flag = false;
                try {
                    scanner = new Scanner(System.in);
                    userInput = scanner.nextInt();
                } catch (Exception e) {
                    showError("Введите номер раздела(число)!");
                    flag = true;
                }
            }

            switch (userInput) {
                case 1:// Название
                    System.out.print("Введите новое название: ");
                    scanner = new Scanner(System.in);
                    editedToy.setName(scanner.nextLine().trim());
                    nameIsEdited = true;
                    break;

                case 2:// Количество
                    flag = true;
                    int newQuantity=0;
                    while(flag) {
                        flag =false;
                        System.out.print("Введите новое количество: ");

                        try {
                            scanner = new Scanner(System.in);
                            newQuantity = scanner.nextInt();
                            if(newQuantity<0){
                                showError("Количество не может быть отрицательным");
                                flag=true;
                            }


                        }catch (Exception e){
                            showError("Количество должно быть целым числом");
                            flag=true;
                        }
                    }
                    editedToy.setQuantity(newQuantity);
                    quantityIsEdited = true;
                    break;

                case 3:// Шанс выпадения
                    flag = true;
                    double newDrop= 0 ;
                    while(flag) {
                        flag =false;
                        System.out.print("Введите новый шанс выпадения:");

                        try {
                            scanner = new Scanner(System.in);
                            newDrop = scanner.nextDouble();
                            if(newDrop<0||newDrop>=1){
                                showError("Шанс выпадения должен быть в диапазоне [0,1)");
                                flag=true;
                            }

                        }catch (Exception e){
                            showError("Количество должно быть дробным числом числом(разделитель \",\")");
                            flag=true;
                        }
                    }
                    editedToy.setDrop(newDrop);
                    dropIsEdited = true;
                    break;

                case 4:// Сохранить
                    if (nameIsEdited||quantityIsEdited||dropIsEdited) {
                        showMessage("Подтвердить сохранение?(y/n)");
                        boolean inputIsFalse = true;
                        while (inputIsFalse) {
                            inputIsFalse = false;
                            scanner = new Scanner(System.in);
                            String input = scanner.nextLine().trim().toLowerCase();
                            if (input.equals("y")) {
                                return editedToy;
                            } else if (input.equals("n")) {
                                flagEdit = true;
                            } else {
                                showError("Чтобы сохранить все изменения введите \"y\", чтобы продолжить редактирование \"n\"");
                                inputIsFalse = true;
                            }
                        }
                    }else {
                        showError("Вы не внесли изменений");
                        flagEdit = true;
                    }
                    break;

                case 5://Отмена изменений
                    if (nameIsEdited||quantityIsEdited||dropIsEdited) {
                        showMessage("Внесенные изменения не сохранятся. Выйти без сохранения?(y/n)");
                        boolean inputIsFalse = true;
                        while (inputIsFalse) {
                            inputIsFalse = false;
                            scanner = new Scanner(System.in);
                            String input = scanner.nextLine().trim().toLowerCase();
                            if (input.equals("y")) {
                                return toy;
                            } else if (input.equals("n")) {
                                flagEdit = true;
                            } else {
                                showError("Чтобы отменить все изменения введите \"y\", чтобы продолжить редактирование \"n\"");
                                inputIsFalse = true;
                            }
                        }
                    }else {
                        showMessage("Редактирование отменено");
                        flagEdit = false;
                    }
                    break;

            }
        }
        return toy;
    }

    @Override
    public void showDrawing(Toy toy) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.printf("ПОБЕДИТЕЛЬ РОЗЫГРЫША - %s\n",toy.getName());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
