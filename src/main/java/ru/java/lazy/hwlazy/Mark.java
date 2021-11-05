package ru.java.lazy.hwlazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "mark")
    private List<Model> models = new ArrayList<>();

    public Mark() {

    }

    public Mark(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Mark of(String name) {
        Mark mark = new Mark();
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(name, mark.name) && Objects.equals(models, mark.models);
    }

    public void addModel(Model model) {
        this.models.add(model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }

    @Override
    public String toString() {
        return "Mark{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
