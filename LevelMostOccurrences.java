import java.util.ArrayDeque;

public class LevelMostOccurrences {

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
