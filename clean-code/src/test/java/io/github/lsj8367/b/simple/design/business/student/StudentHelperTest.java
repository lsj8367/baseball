package io.github.lsj8367.b.simple.design.business.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class StudentHelperTest {

    private static StudentHelper helper = new StudentHelper();

    private static Stream<Arguments> testIsGradeB() {
        return Stream.of(
            Arguments.of(helper.isGradeB(30, false), false),
            Arguments.of(helper.isGradeB(50, false), false),
            Arguments.of(helper.isGradeB(51, false), true),
            Arguments.of(helper.isGradeB(80, false), true),
            Arguments.of(helper.isGradeB(81, false), false),
            Arguments.of(helper.isGradeB(30, true), false),
            Arguments.of(helper.isGradeB(50, true), false),
            Arguments.of(helper.isGradeB(51, true), true),
            Arguments.of(helper.isGradeB(80, true), true),
            Arguments.of(helper.isGradeB(81, true), true),
            Arguments.of(helper.isGradeB(89, true), true),
            Arguments.of(helper.isGradeB(90, true), true),
            Arguments.of(helper.isGradeB(91, true), false)
        );
    }

    private static Stream<Arguments> testGetGrade() {
        return Stream.of(
            Arguments.of(helper.getGrade(99, false), "A"),
            Arguments.of(helper.getGrade(91, false), "A"),
            Arguments.of(helper.getGrade(85, false), "B"),
            Arguments.of(helper.getGrade(51, false), "B"),
            Arguments.of(helper.getGrade(50, false), "C"),
            Arguments.of(helper.getGrade(45, false), "C"),
            Arguments.of(helper.getGrade(99, true), "A"),
            Arguments.of(helper.getGrade(96, true), "A"),
            Arguments.of(helper.getGrade(89, true), "B"),
            Arguments.of(helper.getGrade(56, true), "B"),
            Arguments.of(helper.getGrade(55, true), "C"),
            Arguments.of(helper.getGrade(50, true), "C"),
            Arguments.of(helper.getGrade(45, true), "C"),
            Arguments.of(helper.getGrade(95, true), "B"),
            Arguments.of(helper.getGrade(90, false), "B")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("B등급 테스트")
    void testIsGradeB(final boolean actual, final boolean expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("성적 문자열 테스트")
    void testGetGrade(final String actual, final String expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testWillQualifyForQuiz() {
        assertEquals("NO", helper.willQualifyForQuiz(15, 25, false));
        assertEquals("NO", helper.willQualifyForQuiz(20, 20, false));
        assertEquals("MAYBE", helper.willQualifyForQuiz(21, 21, false));
        assertEquals("MAYBE", helper.willQualifyForQuiz(25, 25, false));
        assertEquals("MAYBE", helper.willQualifyForQuiz(79, 79, false));
        assertEquals("YES", helper.willQualifyForQuiz(80, 30, false));
        assertEquals("YES", helper.willQualifyForQuiz(85, 30, false));
        assertEquals("YES", helper.willQualifyForQuiz(30, 90, false));
        assertEquals("NO", helper.willQualifyForQuiz(15, 25, true));
        assertEquals("NO", helper.willQualifyForQuiz(20, 20, true));
        assertEquals("NO", helper.willQualifyForQuiz(21, 21, true));
        assertEquals("NO", helper.willQualifyForQuiz(25, 25, true));
        assertEquals("MAYBE", helper.willQualifyForQuiz(27, 34, true));
        assertEquals("MAYBE", helper.willQualifyForQuiz(79, 79, true));
        assertEquals("MAYBE", helper.willQualifyForQuiz(80, 30, true));
        assertEquals("MAYBE", helper.willQualifyForQuiz(80, 84, true));
        assertEquals("YES", helper.willQualifyForQuiz(85, 30, true));
        assertEquals("YES", helper.willQualifyForQuiz(30, 90, true));
    }

}
