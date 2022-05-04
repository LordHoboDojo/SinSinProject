import java.time.*;
import java.util.*;

public class QuickSorter
{
    private QuickSorter() { }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy) throws NullPointerException
    {
        if (list.isEmpty() || strategy == null)
        {
            throw new NullPointerException("Invalid Arguemnts");
        }
        long startTime = System.nanoTime();
        switch(strategy){
            case FIRST_ELEMENT:
                quickSortFirst(list, 0, list.size() - 1);
                break;
            case RANDOM_ELEMENT:
                quickSortRandom(list, 0, list.size() - 1);
                break;
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                quickSortMedianAndRandom(list, 0, list.size() - 1);
                break;
            case MEDIAN_OF_THREE_ELEMENTS:
                quickSortMedian(list, 0, list.size() - 1);
                break;
            default:
                break;
        }
        long finishTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime);
        return elapsedTime;
    }

    private static <E extends Comparable<E>> void quickSortFirst(ArrayList<E> list, int low, int high)
    {
        if (low >= high)
        {
            return;
        }
        swap(list, low, high);
        int pivot = quickSortBase(list, low, high);
        quickSortFirst(list, low, pivot - 1);
        quickSortFirst(list, pivot + 1, high);
    }

    private static <E extends Comparable<E>> void quickSortRandom(ArrayList<E> list, int low, int high)
    {
        if (low >= high) return;
        int randomPivot = rand(low, high);
        swap(list, randomPivot, high);
        int pivot = quickSortBase(list, low, high);
        quickSortRandom(list, low, pivot - 1);
        quickSortRandom(list, pivot + 1, high);
    }

    private static <E extends Comparable<E>> void quickSortMedianAndRandom(ArrayList<E> list, int low, int high)
    {
        if (low >= high) return;
        int randOne = rand(low, high);
        int randTwo = rand(low, high);
        int randThree = rand(low, high);
        int medianRandomPivot = median(list, randOne, randTwo, randThree);
        if (medianRandomPivot != high) swap(list, medianRandomPivot, high);
        int pivot = quickSortBase(list, low, high);
        quickSortMedianAndRandom(list, low, pivot - 1);
        quickSortMedianAndRandom(list, pivot + 1, high);
    }

    private static <E extends Comparable<E>> void quickSortMedian(ArrayList<E> list, int low, int high)
    {
        if (low >= high) return;
        int medianThreePivot = median(list, low, high, (low + high) / 2);
        if (medianThreePivot != high) swap(list, medianThreePivot, high);
        int pivot = quickSortBase(list, low, high);
        quickSortMedian(list, low, pivot - 1);
        quickSortMedian(list, pivot + 1, high);
    }

    private static int rand(int min, int max) throws IllegalArgumentException
    {
        return new Random().nextInt(max - min + 1) + min;
    }

    private static <E extends Comparable<E>> int median(ArrayList<E> list, int a, int b, int c)
    {
        if (list.get(a).compareTo(list.get(b)) > 0)
        {
            if (list.get(b).compareTo(list.get(c)) > 0)
            {
                return b;
            }
            else if (list.get(a).compareTo(list.get(c)) > 0)
            {
                return c;
            }
            else
            {
                return a;
            }
        }
        else
        {
            if (list.get(a).compareTo(list.get(c)) > 0)
            {
                return a;
            }
            else if (list.get(b).compareTo(list.get(c)) > 0)
            {
                return c;
            }
            else
            {
                return b;
            }
        }
    }

    private static <E extends Comparable<E>> int quickSortBase(ArrayList<E> list, int low, int high)
    {
        E pivotVal = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if (list.get(j).compareTo(pivotVal) <= 0)
                {
                    i++;
                    swap(list, i, j);
                }
        }
        swap(list,i+1, high);
        return i+1;
    }

    private static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j)
    {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static ArrayList<Integer> generateRandomList(int size)
    {
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < size; i++)
        {
            int num = random.nextInt();
            list.add(num);
        }
        return list;
    }
    public static ArrayList<Integer> generateAlmostSortedList(int size)
    {
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < size; i++)
        {
            int num = random.nextInt();
            list.add(num);
        }
        Collections.sort(list);
        for (int i=0; i< list.size()/10;i++){

        }
        return list;
    }

    public static enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }

}

