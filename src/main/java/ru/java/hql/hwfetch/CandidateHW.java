package ru.java.hql.hwfetch;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidateshw")
public class CandidateHW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int experience;
    private double salary;

    @OneToOne(fetch = FetchType.LAZY)
    private BaseOfVacancies baseOfVacancies;

    public static CandidateHW of(String name, int experience, double salary) {
        CandidateHW candidate = new CandidateHW();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public BaseOfVacancies getBaseOfVacancies() {
        return baseOfVacancies;
    }

    public void setBaseOfVacancies(BaseOfVacancies baseOfVacancies) {
        this.baseOfVacancies = baseOfVacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CandidateHW that = (CandidateHW) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CandidateHW{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", experience=" + experience
                + ", salary=" + salary
                + ", baseOfVacancies=" + baseOfVacancies
                + '}';
    }
}
