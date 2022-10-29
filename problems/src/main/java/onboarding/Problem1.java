package onboarding;

import java.util.ArrayList;
import java.util.List;

class Problem1 {
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (pobi.get(0) + 1 != pobi.get(1)) {
            return -1;
        }

        if (crong.get(0) + 1 != crong.get(1)) {
            return -1;
        }

        final int pobiMax = getMaxValue(pobi);

        final int crongMax = getMaxValue(crong);

        if (pobiMax > crongMax) {
            return 1;
        }

        if (pobiMax < crongMax) {
            return 2;
        }

        return 0;
    }

    private static int getMaxValue(final List<Integer> pobi) {
        int max = 0;
        for (Integer integer : pobi) {
            int number = integer;

            List<Integer> list = new ArrayList<>();

            // 각 자리수 구함
            while (number > 0) {
                list.add(number % 10);
                number /= 10;
            }

            // 합
            final int sum = list.stream().mapToInt(l -> l).sum();
            if (max < sum) {
                max = sum;
            }

            // 곱
            int multi = 1;
            for (Integer integer1 : list) {
                multi *= integer1;
            }

            if (max < multi) {
                max = multi;
            }

        }
        return max;
    }

}
