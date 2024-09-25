package study.system.class14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 113. 返回最多会议室宣讲场次最多
 * No113_BestArrange
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 18, 2024</pre>
 */
public class No113_BestArrange {

    public int bestArrange(Program[] programs) {
        PriorityQueue<Program> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
        queue.addAll(Arrays.asList(programs));
        int times = 0;
        int timeLine = 0;
        while (!queue.isEmpty()) {
            Program polled = queue.poll();
            if (polled.start >= timeLine) {
                times++;
                timeLine = polled.end;
            }
        }
        return times;
    }

    public int bestArrange2(Program[] programs) {
        return bestArrange2(programs, 0, 0);
    }

    private int bestArrange2(Program[] programs, int timeLine, int done) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] nextProgram = removeTargetIndex(programs, i);
                int bested = bestArrange2(nextProgram, programs[i].end, done + 1);
                max = Math.max(max, bested);
            }
        }
        return max;
    }

    private Program[] removeTargetIndex(Program[] strings, int index) {
        Program[] result = new Program[strings.length - 1];
        int j = 0;
        for (int i = 0; i < strings.length; i++) {
            if (i != index) {
                result[j++] = strings[i];
            }
        }
        return result;
    }


    private static class Program {
        int start;
        int end;
    }

}
