package feedbacksystem.com.demo.model.responses.chart;


public interface DataToChartOptimized {
    Long    getunityId();
    String  getquestionDescription();
    Long    getquestionId();
    String  getpredefinedResponseDescription();
    Long    getpredefinedId();
    Long    getsum();
    Boolean getisPositiveResponse();
}