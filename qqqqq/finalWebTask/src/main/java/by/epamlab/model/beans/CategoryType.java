package by.epamlab.model.beans;

public enum CategoryType {
    CAKES("Cakes"),
    PIES("Pies"),
    BREAD("Bread");

    private String name;

    CategoryType(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
