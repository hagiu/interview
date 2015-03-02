import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Integer [] testInts = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        List<Integer> source = new ArrayList<>();
        source.addAll(Arrays.asList(testInts));
        List<Number> dest = new ArrayList<>();

        moveUnevenIndexesWithoutIterator(source, dest);

        source.stream().forEach(el -> System.out.print(el + " "));
        System.out.println();
        dest.stream().forEach(el -> System.out.print(el + " "));

    }

    /*
    FAILS at RunTime
    throws ConcurrentModificationException
     */
    public static void moveUnevenIndexesForEachLoopFails(List<Integer> source, List<? super Integer> dest){

        int count = 0;
        for (Integer el : source){
            if (count++ % 2 != 0){
                dest.add(el);
                source.remove(count);
            }
        }
    }

    /*
    CORRECT!
     */
    public static void moveUnevenIndexesIterator(List<Integer> source, List<? super Integer> dest){

        int count = 0;
        Iterator<Integer> it = source.iterator();
        while (it.hasNext()){
            Integer el = it.next();
            if (count++ % 2 != 0){
                dest.add(el);
                it.remove();
            }
        }
    }


    /*
    WITHOUT ITERATOR VERSION
     */
    static enum Mode {EVEN, UNEVEN}
    public static void moveUnevenIndexesWithoutIterator(List<Integer> source, List<? super Integer> dest){

        Mode mode = Mode.UNEVEN;
        for (int i = 0; i < source.size(); i++){
            if (checkMode(mode, i)){
                dest.add(source.get(i));
                source.remove(i);
                if (mode.equals(Mode.EVEN)){
                    mode = Mode.UNEVEN;
                } else {
                    mode = Mode.EVEN;
                }
            }
        }

    }

    private static boolean checkMode(Mode mode, int count){
        if (mode.equals(Mode.EVEN))
            return count % 2 == 0;
        else
            return count % 2 != 0;
    }
}
