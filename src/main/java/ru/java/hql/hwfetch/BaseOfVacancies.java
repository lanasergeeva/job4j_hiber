package ru.java.hql.hwfetch;

import ru.java.hql.Account;
import ru.java.hql.BookSec;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basevacancies")
public class BaseOfVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacList = new ArrayList<>();

    public void addVac(Vacancy vacancy) {
        this.vacList.add(vacancy);
    }

    public static BaseOfVacancies of(String username) {
        BaseOfVacancies a = new BaseOfVacancies();
        a.username = username;
        return a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Vacancy> getVacList() {
        return vacList;
    }

    public void setVacList(List<Vacancy> vacList) {
        this.vacList = vacList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseOfVacancies that = (BaseOfVacancies) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseOfVacancies{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", vacList=" + vacList
                + '}';
    }
}
