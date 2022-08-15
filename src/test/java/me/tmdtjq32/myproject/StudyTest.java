package me.tmdtjq32.myproject;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Study_Test {

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo){
        Study study = Study.builder()
                .random(new Random())
                .build();

        assertThat(study)
                .as("Only Study Instance")
                .isInstanceOf(Study.class);

        System.out.println(study.getRandom().nextInt() % 10);
    }

    @DisplayName("인자 테스트")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(ints = {10,20,40})
    void singleParameterizedTest(@ConvertWith(StudyConverter.class) Study study){
        assertThat(study)
                .as("Is converted Study Instance")
                .isInstanceOf(Study.class);
        System.out.println(study.toString());
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter{ // Study로 convert
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException{
            return Study.builder()
                    .limit(Integer.parseInt(source.toString()))
                    .build();
        }
    }

    @DisplayName("인자 테스트")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, 'JUnit", "20, 'AssertJ'", "40, 'Hamcrest"})
    void severalParameterizedTest(@AggregateWith(StudyAggregator.class) Study study){
        assertThat(study)
                .as("Is converted Study Instance")
                .isInstanceOf(Study.class);
        System.out.println(study.toString());
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext){
            return Study.builder()
                    .limit(argumentsAccessor.getInteger(0))
                    .name(argumentsAccessor.getString(1))
                    .build();
        }
    }

    @DisplayName("인자 테스트")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ArgumentsSource(StudyProvider.class)
    void parameterizedTest(Study study){
        assertThat(study)
                .as("Is converted Study Instance")
                .isInstanceOf(Study.class);
        System.out.println(study.toString());
    }

    public static class StudyProvider implements ArgumentsProvider{
        private final List<Study> studyInstances = Arrays.asList(
                Study.builder()
                        .limit(10)
                        .name("Junit")
                        .build(),

                Study.builder()
                        .limit(20)
                        .name("AssertJ")
                        .build(),

                Study.builder()
                        .limit(40)
                        .name("Hamcrest")
                        .build()
        );

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context)  {
            return studyInstances.stream().map(Arguments::of);
        }
    }


    @FastTest
    @DisplayName("fast test")
    void get_Status(){
        Study study = Study.builder()
                .status(StudyStatus.DRAFT)
                .build();

        assertThat(study.getStatus())
                .as(()-> "chk StudyStatus")
                .isEqualTo(StudyStatus.DRAFT);
    }

    @SlowTest
    @DisplayName("slow test")
    void create_new_study() {
        Study study = Study.builder()
                .status(StudyStatus.DRAFT)
                .limit(1)
                .build();

        assertAll(
                () -> assertThat(study)
                        .as(() -> "study is not null")
                        .isNotNull(),
                () -> assertThat(study.getStatus())
                .as(() -> "status is not DRAFT")
                .isEqualTo(StudyStatus.DRAFT),
                () -> assertThat(study.getLimit())
                .as(() -> "limit is not over 10")
                .isLessThan(10)
        );
    }

}