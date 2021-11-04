package ru.java.tomany.hw;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "car_mark")
public class MarkCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelCar> models = new ArrayList<>();

    public MarkCar() {

    }

    public MarkCar(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MarkCar of(String name) {
        MarkCar mark = new MarkCar();
        mark.name = name;
        return mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkCar markCar = (MarkCar) o;
        return id == markCar.id && Objects.equals(name, markCar.name) && Objects.equals(models, markCar.models);
    }

    public void addModel(ModelCar model) {
        this.models.add(model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }

    @Override
    public String toString() {
        return "MarkCar{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", models=" + models
                + '}';
    }
}
