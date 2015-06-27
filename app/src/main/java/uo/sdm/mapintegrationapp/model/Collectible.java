package uo.sdm.mapintegrationapp.model;

/**
 * Created by Adrian on 26/06/2015.
 */
public class Collectible {
    private Long id;
    private Integer type;
    private String name;
    private String category;
    private Integer amount;

    public Collectible(Long id, Integer type,String name, String category, Integer amount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public Collectible(Integer type,String name, String category, Integer amount) {
        this.type = type;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Collectible setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Collectible setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Collectible setCategory(String category) {
        this.category = category;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Collectible setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
    public Collectible incrementAmount(Integer amount){
        this.amount += amount;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
