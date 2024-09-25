package thread.dielock;

/**
 * 吃饭
 *
 * @author SunKangChao
 * @date 2021/7/6 23:21
 */
public class EatRice {

    public static void main(String[] args) {

        Chopsticks chopsticks1 = new Chopsticks("筷子1");
        Chopsticks chopsticks2 = new Chopsticks("筷子2");
        Chopsticks chopsticks3 = new Chopsticks("筷子3");
        Chopsticks chopsticks4 = new Chopsticks("筷子4");

        Philosopher philosopher1 = new Philosopher("哲学家1号",chopsticks1,chopsticks2,0);
        Philosopher philosopher2 = new Philosopher("哲学家2号",chopsticks2,chopsticks3,1000);
        Philosopher philosopher3 = new Philosopher("哲学家3号",chopsticks3,chopsticks4,0);
        Philosopher philosopher4 = new Philosopher("哲学家4号",chopsticks4,chopsticks1,1000);

        new Thread(philosopher1).start();
        new Thread(philosopher2).start();
        new Thread(philosopher3).start();
        new Thread(philosopher4).start();

    }
}
