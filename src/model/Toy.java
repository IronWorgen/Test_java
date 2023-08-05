package model;

public class Toy implements Comparable {
    /**
     * id игрушки
     */
    private int id;

    /** текстовое название    */
    private  String name;
    /**
     * количество
     */
    private int quantity;
    /**
     * частота выпадения игрушки (вес в % от 100)
     *  НЕ МОЖЕТ БЫТЬ РАВНА 1
     */
    private double drop;

    public Toy(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Toy() {

    }

    public Toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDrop() {
        return drop;
    }

    public void setDrop(double drop) {
        this.drop = drop;
    }

    @Override
    public int compareTo(Object o) {
        Toy toy = (Toy) o;
        boolean equalsCheck =  this.id==toy.getId()&&
                this.name.equals(toy.getName())&&
                this.quantity==toy.getQuantity()&&
                this.drop==toy.getDrop();
        if (equalsCheck){
            return 0;
        }else{
            return  -1;
        }
    }

    @Override
    public Toy clone()  {
        Toy newToy = new Toy(this.id, this.name, this.quantity);
        newToy.setDrop(this.getDrop());
        return newToy;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", drop=" + drop +
                '}';
    }
}
