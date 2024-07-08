package ECService.demo.dto;

import ECService.demo.entity.Apply;

public class ApplyForm {

    private String name;
    private String studentId;
    private String phoneNumber;
    private String question1;
    private String question2;
    private String question3;

    public ApplyForm(String name, String studentId, String phoneNumber, String question1, String question2, String question3) {
        this.name = name;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
    }

    @Override
    public String toString() {
        return "applyForm{" +
                "name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", question1='" + question1 + '\'' +
                ", question2='" + question2 + '\'' +
                ", question3='" + question3 + '\'' +
                '}';
    }

    //Entity로 만들기 위한 함수
    public Apply toEntity() {
        return new Apply(null, name, studentId, phoneNumber, question1, question2, question3);
    }
}
