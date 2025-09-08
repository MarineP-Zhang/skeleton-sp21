package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> rightAList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        rightAList.addLast(4);
        rightAList.addLast(5);
        rightAList.addLast(6);

        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);

        assertEquals(rightAList.size(),buggyAList.size());

        assertEquals(rightAList.removeLast(), buggyAList.removeLast());
        assertEquals(rightAList.removeLast(), buggyAList.removeLast());
        assertEquals(rightAList.removeLast(), buggyAList.removeLast());
    }


    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000; // 执行5000次随机操作

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = B.size();
                System.out.println("L.size: " + size1 + ", B.size: " + size2);
                assertEquals("Size should be equal", size1, size2);

            } else if (operationNumber == 2) {
                // getLast (只有在非空时才执行)
                if (L.size() > 0) {
                    Integer last1 = L.getLast();
                    Integer last2 = B.getLast();
                    System.out.println("L.getLast(): " + last1 + ", B.getLast(): " + last2);
                    assertEquals("getLast should return same value", last1, last2);
                }

            } else if (operationNumber == 3) {
                // removeLast (只有在非空时才执行)
                if (L.size() > 0) {
                    Integer removed1 = L.removeLast();
                    Integer removed2 = B.removeLast();
                    System.out.println("L.removeLast(): " + removed1 + ", B.removeLast(): " + removed2);
                    assertEquals("removeLast should return same value", removed1, removed2);
                }
            }
        }
    }

    @Test
    public void aggressiveRandomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 100000; // 更多测试
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);

            try {
                if (operationNumber == 0) {
                    // addLast - 40%概率
                    int randVal = StdRandom.uniform(0, 1000);
                    L.addLast(randVal);
                    B.addLast(randVal);

                } else if (operationNumber == 1) {
                    // size - 20%概率
                    int sizeL = L.size();
                    int sizeB = B.size();
                    assertEquals("Size mismatch at iteration " + i, sizeL, sizeB);

                } else if (operationNumber == 2) {
                    // getLast - 20%概率
                    if (L.size() > 0) {
                        Integer lastL = L.getLast();
                        Integer lastB = B.getLast();
                        assertEquals("getLast mismatch at iteration " + i, lastL, lastB);
                    }

                } else if (operationNumber == 3) {
                    // removeLast - 15%概率
                    if (L.size() > 0) {
                        Integer removedL = L.removeLast();
                        Integer removedB = B.removeLast();
                        assertEquals("removeLast mismatch at iteration " + i, removedL, removedB);
                    }

                } else if (operationNumber == 4) {
                    // get random index - 5%概率
                    if (L.size() > 0) {
                        int randomIndex = StdRandom.uniform(0, L.size());
                        Integer getL = L.get(randomIndex);
                        Integer getB = B.get(randomIndex);
                        assertEquals("get(" + randomIndex + ") mismatch at iteration " + i, getL, getB);
                    }
                }

                // 每1000次操作后做一次全面检查
                if (i % 1000 == 0) {
                    assertEquals("Size should always match at iteration " + i, L.size(), B.size());

                    // 检查所有元素是否一致
                    for (int j = 0; j < L.size(); j++) {
                        assertEquals("Element at index " + j + " should match at iteration " + i,
                                L.get(j), B.get(j));
                    }
                }

            } catch (Exception e) {
                System.out.println("🚨 发现Bug! 在第 " + i + " 次操作时出现异常:");
                System.out.println("操作类型: " + operationNumber);
                System.out.println("L.size(): " + L.size());
                System.out.println("B.size(): " + B.size());
                System.out.println("异常信息: " + e.getMessage());
                throw e;
            }
        }
    }
}
