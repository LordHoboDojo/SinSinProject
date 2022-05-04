import java.util.*;
import java.io.*;
import java.time.*;
public class Main {

    public static void main(String[] args)
    {
        if (args.length == 4)
        {
            try
            {
                File report = new File(args[1]);
                File unsorted = new File(args[2]);
                File sorted = new File(args[3]);
                report.createNewFile();
                unsorted.createNewFile();
                sorted.createNewFile();
                PrintWriter reportFile = new PrintWriter(report);
                PrintWriter unsortedFile = new PrintWriter(unsorted);
                PrintWriter sortedFile = new PrintWriter(sorted);
                int size = Integer.parseInt(args[0]);
                ArrayList<Integer> list = QuickSorter.generateRandomList(size);
                unsortedFile.println(list);
                unsortedFile.close();

                ArrayList<Integer> copy1 = new ArrayList<>(list);
                ArrayList<Integer> copy2 = new ArrayList<>(list);
                ArrayList<Integer> copy3 = new ArrayList<>(list);
                ArrayList<Integer> copy4 = new ArrayList<>(list);

                Duration first, random, median, medianAndRandom;
                first = QuickSorter.timedQuickSort(copy1, QuickSorter.PivotStrategy.FIRST_ELEMENT);
                random = QuickSorter.timedQuickSort(copy2, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
                medianAndRandom = QuickSorter.timedQuickSort(copy3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
                median = QuickSorter.timedQuickSort(copy4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);

                sortedFile.println(copy1);
                sortedFile.close();
                reportFile.println("Array Size = " + size);
                reportFile.println("FIRST_ELEMENT : " + first);
                reportFile.println("RANDOM_ELEMENT : " + random);
                reportFile.println("MEDIAN_OF_THREE_RANDOM _ELEMENTS : " + medianAndRandom);
                reportFile.println("MEDIAN_OF_THREE_ELEMENTS : " + median);
                reportFile.close();


            }
            catch(Exception ex)
            {
                System.out.println("Unable to create file");
            }
        }
        else
        {
            System.out.println("Please Input Valid Arguments in Command Lines");
        }
    }
}