package model;

import java.util.*;

public class Model implements iModel{

    List<Toy> toyList;
    Queue winners;

    public Model() {
        this.toyList = new ArrayList<>(Arrays.asList(new Toy[]{new Toy(1, "car", 2),
                new Toy(2, "bal", 1)}));
        toyList.get(0).setDrop(0.1);
        toyList.get(1).setDrop(0.1);
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
}
