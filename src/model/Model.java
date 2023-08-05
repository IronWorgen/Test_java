package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Model implements iModel{

    List<Toy> toyList;
    Queue winners;

    public Model() {
        this.toyList = new ArrayList<>();
        this.winners = new PriorityQueue();
    }

    @Override
    public List<Toy> getToyList() {
        return toyList;
    }

    /**
     *
     * @param newToy - игрушка которую надо добавить
     * @return
     */
    @Override
    public boolean addToy(Toy newToy) {
        boolean toyIsFound =  false;
        int index=0;
        for (int i = 0; i < toyList.size(); i++) {
            String currentToyName = toyList.get(i).getName();
            if (currentToyName.equals(newToy.getName())){
                toyIsFound= true;
                index = i;

            }
        }

        if (toyIsFound){
            Toy  faundetToy = toyList.get(index);
            faundetToy.setQuantity(faundetToy.getQuantity() + newToy.getQuantity());
        }else {
            newToy.setId(toyList.size());
            toyList.add(newToy);
        }

        return true;
    }

    /**
     *
     * @param toyList - список для сохранения
     * @return
     */
    @Override
    public boolean saveToyList(List<Toy> toyList) {
        this.toyList = toyList;
        return true;
    }

    /**
     * добавить победителя розыгрыша в очередь на отправку
     * @param toy - победитель розыгрыша
     */
    @Override
    public void addWinnerInQueue(Toy toy) {
        winners.add(toy);
    }

    /**
     * добавить победителя в файл
     *
     */
    @Override
    public boolean saveWinnerInFile() {
        if (winners.size()==0){
            return false;
        }

        Toy toy = (Toy) winners.poll();
        try(FileWriter writer = new FileWriter("winners.txt", true))
        {
            String winnerToy = String.format("название игрушки - %s\n", toy.getName())+
                    "\n-----------------------------------\n\n";
            writer.append(winnerToy);
        }
        catch(IOException ex){
            System.out.println("файл не существует");
        }
        return true;
    }
}
