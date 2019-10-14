package feedbacksystem.com.demo.service;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.responses.chart.DataToChartOptimized;
import feedbacksystem.com.demo.model.responses.chart.WrappedPredefinedResponse;
import feedbacksystem.com.demo.model.responses.chart.WrappedPredefinedResponseInterface;
import feedbacksystem.com.demo.model.responses.chart.WrapperQuestion;
import feedbacksystem.com.demo.repository.QuestionRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import feedbacksystem.com.demo.repository.UnityRepository;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ResponseToChartService {

    private QuestionRepository questionRepository;
    private ResponseCountRepository responseCountRepository;
    private UnityRepository unityRepository;

    public ResponseToChartService(QuestionRepository questionRepository, ResponseCountRepository responseCountRepository, UnityRepository unityRepository) {
        this.questionRepository = questionRepository;
        this.responseCountRepository = responseCountRepository;
        this.unityRepository = unityRepository;
    }

    public List<WrapperQuestion> getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDates(Long unityId, String startDate, String endDate) throws ParseException {

        List<Question> questionList = new ArrayList<>();
        questionList.addAll(questionRepository.findAllByUnityIdAndFilter(unityId));

        return getWrapperQuestions(startDate, endDate, questionList);
    }

    public List<WrapperQuestion> getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDatesByUniqueChart(List<Long> idQuestionList, String startDate, String endDate) throws ParseException {

        List<Question> questionList = new ArrayList<>();

        questionList.addAll((List<Question>) questionRepository.findAllById(idQuestionList));

        return getWrapperQuestions(startDate, endDate, questionList);
    }

    public List<WrapperQuestion> getWrapperQuestions(String startDate, String endDate, List<Question> questionList) throws ParseException {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        /*TODO ver outra forma de adicionar um à data final*/
        Date end = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime() + (1000 * 60 * 60 * 24));

        List<WrapperQuestion> wrapperQuestions = questionList.stream().map(question -> {
            WrapperQuestion wrapperQuestion = WrapperQuestion.builder().build();
            wrapperQuestion.setQuestionDescription(question.getDescription());
            wrapperQuestion.setQuestionId(question.getId());
            List<WrappedPredefinedResponse> wrappedPredefinedResponseInterface = responseCountRepository.getSumBetweenDatesByQuestion(question.getId(), start, end);
            wrapperQuestion.setWrappedPredefinedResponse(wrappedPredefinedResponseInterface);
            return wrapperQuestion;
        }).collect(Collectors.toList());


        return wrapperQuestions;
    }

    public List<WrapperQuestion> getWrapperQuestions2(String startDate, String endDate, Long unityId) throws ParseException {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        /*TODO ver outra forma de adicionar um à data final*/
        Date end = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime() + (1000 * 60 * 60 * 24));

        List<WrapperQuestion> wrapperQuestions = new ArrayList<>();

        System.out.println(start);
        System.out.println(end);

        List<DataToChartOptimized> wrappedData = responseCountRepository.getSumBetweenDatesByUnity(unityId, start, end);

        wrapperQuestions = wrappedData.stream()
                .map(dataToChartOptimized ->
                        WrapperQuestion.builder()
                                .questionId(dataToChartOptimized.getquestionId())
                                .questionDescription(dataToChartOptimized.getquestionDescription())
                                .build())
                .filter(distinctByKey(WrapperQuestion::getQuestionId)).collect(Collectors.toList());

        wrapperQuestions.forEach(wrapperQuestion -> {

            List<WrappedPredefinedResponse> wrappedPredefinedResponses = wrappedData.stream()
                    .filter(dataToChartOptimized -> dataToChartOptimized.getquestionId().equals(wrapperQuestion.getQuestionId()))
                    .map(dataToChartOptimized -> WrappedPredefinedResponse
                            .builder()
                            .predefinedId(dataToChartOptimized.getpredefinedId())
                            .predefinedResponseDescription(dataToChartOptimized.getpredefinedResponseDescription())
                            .sum(dataToChartOptimized.getsum())
                            .isPositiveResponse(dataToChartOptimized.getisPositiveResponse())
                            .build())
                    .collect(Collectors.toList());

            Long sum = wrappedPredefinedResponses.stream()
                    .mapToLong(item -> item.getSum())
                    .sum();
            wrapperQuestion.setWrappedPredefinedResponse(sum > 0 ? wrappedPredefinedResponses : new ArrayList<>());
        });

        return wrapperQuestions;
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
