package spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Description")
public class Description {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "Content")
    private String content;
}
