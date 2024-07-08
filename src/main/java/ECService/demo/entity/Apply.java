package ECService.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Apply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String studentId;
    @Column
    private String phoneNumber;
    @Column(columnDefinition = "Text", length = 300)
    private String question1;
    @Column(columnDefinition = "Text", length = 300)
    private String question2;
    @Column(columnDefinition = "Text", length = 300)
    private String question3;
    @Column
    private String state; //합격 여부를 확인할 변수 -> 이후 repository에서 update를 통해 상태 변화하기
    @Column
    private LocalDateTime createTime;

    //getter
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getQuestion1() {
        return question1;
    }
    public String getQuestion2() {
        return question2;
    }
    public String getQuestion3() {
        return question3;
    }
    public String getState() {
        return state;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    //setter
    public void setState(String state) {
        this.state = state;
    }
    //NoArgsConstructor
    public Apply() {

    }
    //AllArgConstructor
    public Apply(Long id, String name, String studentId, String phoneNumber, String question1, String question2, String question3) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.state = "unDefined";
        this.createTime = LocalDateTime.now();
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
                ", creatTime=" + createTime +
                '}';
    }
}
