package ECService.demo.entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Entity
public class Apply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column(columnDefinition = "Text", length = 300)
    private String question1;
    @Column(columnDefinition = "Text", length = 300)
    private String question2;
    @Column(columnDefinition = "Text", length = 300)
    private String question3;

    private LocalDateTime creatTime;

    //NoArgConstructor
    public Apply() {

    }
    //AllArgConstructor
    public Apply(Long id, String name, String phoneNumber, String question1, String question2, String question3) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.creatTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", question1='" + question1 + '\'' +
                ", question2='" + question2 + '\'' +
                ", question3='" + question3 + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
