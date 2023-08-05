package model;

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


        return new ArrayList<Toy>(Arrays.asList(new Toy[]{new Toy("игрушка 1"),
                new Toy("игрушка 2"),
                new Toy("игрушка 3")}));
    }

    /**
     *
     * @param newToy - игрушка которую надо добавить
     * @return
     */
    @Override
    public boolean addToy(Toy newToy) {


        return true;
    }

    /**
     *
     * @param toyList - список для сохранения
     * @return
     */
    @Override
    public boolean saveToyList(List<Toy> toyList) {
        return true;
    }
}
