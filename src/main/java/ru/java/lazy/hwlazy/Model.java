package ru.java.lazy.hwlazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Mark mark;

    public Model() {

    }

    public static Model of(String name, Mark mark) {
        Model model = new Model();
        model.name = name;
        model.mark = mark;
        return model;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id && Objects.equals(name, model.name) && Objects.equals(mark, model.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mark);
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", markCar=" + mark
                + '}';
    }
}
