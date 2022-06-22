package io.github.lsj8367.design.business.student;

public class StudentHelper {

    private static final int GRADE_B_LOWER_LIMIT = 51;
    private static final int GRADE_B_UPPER_LIMIT = 80;
    private static final int ADD_GRADE_A = 10;
    public static final int GRADE_A = 91;

    /* PROBLEM 1 */

    /**
     * You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
     */
    public boolean isGradeB(int marks, boolean isMaths) {

        int addNumber = isMaths ? ADD_GRADE_A : 0;

        int upperLimit = GRADE_B_UPPER_LIMIT + addNumber;

        return marks >= GRADE_B_LOWER_LIMIT && marks <= upperLimit;
    }

    /* PROBLEM 2 */

    /**
     * You are awarded a grade based on your marks. Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
     */
    public String getGrade(int mark, boolean isMath) {
        int mathScore = isMath ? 5 : 0;

        if (mark >= GRADE_A + mathScore) {
            return "A";
        }

        if (mark >= GRADE_B_LOWER_LIMIT + mathScore) {
            return "B";
        }

        return "C";
    }

    /*  PROBLEM 3
     * 문제 3 귀하와 귀하의 친구는 주제 퀴즈에 참가할 계획입니다.
     * 그러나 자격을 얻으려면 획득해야 하는 점수 요건이 있습니다.
     * 반환 값은 YES, NO 또는 MAYBE일 수 있습니다.
     * 둘 중 하나가 해당 과목에 매우 능숙하면(80점 이상) YES입니다.
     * 다만, 둘 중 어느 하나라도 해당 과목에서 좋지 않은 경우(20점 이하)인 경우는 아니오(NO)로 한다.
     * 다른 모든 조건에서는 MAYBE를 반환합니다.
     * 다만, 교과목이 수학일 경우 좋음과 좋지 않음의 정의가 5점 더 높다.
     * mark1 - 귀하의 점수 mark2 - 친구 표시
     */

    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {

        if (isNotGood(marks1, isMaths) || isNotGood(marks2, isMaths)) {
            return "NO";
        }

        if (isGood(marks1, isMaths) || (isGood(marks2, isMaths))) {
            return "YES";
        }
        return "MAYBE";
    }

    private boolean isGood(final int marks, final boolean isMaths) {
        int extraLimit = isMaths ? 5 : 0;
        return marks >= 80 + extraLimit;
    }

    private boolean isNotGood(final int marks, final boolean isMaths) {
        int extraLimit = isMaths ? 5 : 0;
        return marks <= 20 + extraLimit;
    }

}