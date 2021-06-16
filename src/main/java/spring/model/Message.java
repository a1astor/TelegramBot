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
@Table(name = "message")
public class Message {

    @Id
    @Column(name = "message_id")
    private long message_id;

    @Column(name = "town_id")
    private long town_id;

    @Column(name = "description_id")
    private long description_id;
}
