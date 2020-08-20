package lootweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bosses")
public class Boss {

    @Id
    @Column(name = "boss_id", length = 11, nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
