import java.util.ArrayDeque;

/**
 * This class is a one-function-class who is responsible for finding the level in a binary tree with the most
 * occurrences of a given number.
 */
public class LevelMostOccurrences {


    /**
     * This function takes the root of a binary tree of integers and an integer, and return the
     * level number in the tree where the number obtained appears most often. If there are several
     * levels with the most occurrences, the function will return the number of the first level of them.
     * If the number does not appear at all the function will return -1.
     * @param num (int) the number to search for.
     * @param node (BinNode<Integer>) the root of the binary tree.
     * @return (int) the level with the most occurrences.
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        int max = 0, rowNum = -1, occurrences;
        BinNode<Integer> b;
        ArrayDeque<BinNode<Integer>> parentsQueue = new ArrayDeque<>(), childrenQueue;
        ArrayDeque<Integer> numOfOccurrencesInLevels = new ArrayDeque<>();
        parentsQueue.addFirst(node);

        while(!parentsQueue.isEmpty() ){
            occurrences = 0;
            childrenQueue = new ArrayDeque<>();
            while (!parentsQueue.isEmpty()){
                 b = parentsQueue.pollFirst();
                 if (b.getData() == num) occurrences++;
                 if (b.getRight() != null) childrenQueue.addLast(b.getRight());
                 if (b.getLeft() != null) childrenQueue.addLast(b.getLeft());
            }
            parentsQueue = childrenQueue;
            numOfOccurrencesInLevels.addLast(occurrences);
        }

        for (int i = 0; !numOfOccurrencesInLevels.isEmpty(); ++i) {
            occurrences = numOfOccurrencesInLevels.pollFirst();
            if (occurrences > max){
                max = occurrences;
                rowNum = i;
            }
        }
        return rowNum;
    }
}
