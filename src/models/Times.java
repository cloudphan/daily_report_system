package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "times")
@NamedQueries({
    @NamedQuery(
        name = "getAllTimes",
        query = "SELECT t FROM Times AS t ORDER BY t.id DESC"
    ),
    @NamedQuery(
        name = "getCount",
        query = "SELECT COUNT(t) FROM Times AS t"
    ),
    @NamedQuery(
            name = "getMyAllTimes",
            query = "SELECT t FROM Times AS t WHERE t.employee = :employee ORDER BY t.id DESC"
        ),
        @NamedQuery(
            name = "getMyCount",
            query = "SELECT COUNT(t) FROM Times AS t WHERE t.employee = :employee"
        )
})
@Entity
public class Times {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "in_time")
    private Timestamp in_time;

    @Column(name = "out_time")
    private Timestamp out_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Timestamp getIn_time() {
        return in_time;
    }

    public void setIn_time(Timestamp in_time) {
        this.in_time = in_time;
    }
    public Timestamp getOut_time() {
        return out_time;
    }

    public void setOut_time(Timestamp out_time) {
        this.out_time = out_time;
    }


}