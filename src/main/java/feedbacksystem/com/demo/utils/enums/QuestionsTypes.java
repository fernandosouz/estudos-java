package feedbacksystem.com.demo.utils.enums;

import lombok.Getter;

@Getter
public enum QuestionsTypes {


    PREDEFINED(1, "predefined"),
    TEXT(2, "text");


    QuestionsTypes(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;

    private String description;


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static QuestionsTypes toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (QuestionsTypes x : QuestionsTypes.values()) {
            if (id.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + id);
    }
}
