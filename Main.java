import java.util.*;
import java.io.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("Invalid number of arguments");
            System.out.println("Usage: Java FileHandler [input_file_1] [input_file_2] [output_file_1] [output_file_2]");
        }

        //Parse command line arguments and create the files
        int arraySize = Integer.parseInt(args[0]);
        String reportFilename = args[1];
        String unsortedFilename = args[2];
        String sortedFilename = args[3];

        //generate a random list of size arraySize
        ArrayList<Integer> list = QuickSorter.randomListGenerator(arraySize);

        //save unsorted list to file
        try {
            saveArrayToFile(list, unsortedFilename);
        }
        catch(Exception e) {
            System.out.println("Unable to create file");
        }

        //create copies of the list to past into each quicksort function
        ArrayList<Integer> firstSortList = new ArrayList<Integer>(list);
        ArrayList<Integer> randomSortList = new ArrayList<Integer>(list);
        ArrayList<Integer> threeRandomSortList = new ArrayList<Integer>(list);
        ArrayList<Integer> threeMedianSortList = new ArrayList<Integer>(list);

        //create the time variables
        Duration first_pivot_time, random_pivot_time, median_three_random_pivot_time, median_three_pivot_time;
        
        //Apply sort functions on each
        first_pivot_time = QuickSorter.timedQuickSort(firstSortList, QuickSorter.PivotStrategy.FIRST_ELEMENT);
        random_pivot_time = QuickSorter.timedQuickSort(randomSortList, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
        median_three_random_pivot_time = QuickSorter.timedQuickSort(threeRandomSortList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        median_three_pivot_time = QuickSorter.timedQuickSort(threeMedianSortList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);

        //save sorted list to sorted file 
        try {
            saveArrayToFile(firstSortList, sortedFilename);
        }
        catch(Exception e) {
            System.out.println("Unable to create file");
        }

        //save report to report file
        try(PrintWriter writer = new PrintWriter(new File(reportFilename))) {
            writer.printf("Array Size = %d\n", arraySize);
            writer.printf("FIRST_ELEMENT : %s\n", first_pivot_time);
            writer.printf("RANDOM_ELEMENT : %s\n", random_pivot_time);
            writer.printf("MEDIAN_OF_THREE_RANDOM_ELEMENTS : %s\n", median_three_random_pivot_time);
            writer.printf("MEDIAN_OF_THREE_ELEMENTS : %s\n", median_three_pivot_time);
        }
        catch(FileNotFoundException e) {
            System.out.println("Unable to create file" + reportFilename);
            System.out.println(e.getMessage());
        }
        System.out.println("Sorting completed successfully");
    }

    private static void saveArrayToFile(ArrayList<Integer> list, String filename) throws FileNotFoundException {
        try(PrintWriter writer = new PrintWriter(new File(filename))) {
            for(int i = 0; i < list.size(); i++) {
                writer.println(list.get(i));
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: Failed to save array to file" + filename + " ");
            System.out.println(e.getMessage());
        }
    }

}
