import java.util.*;
import java.lang.Comparable;
import java.lang.System;
import java.time.Duration;

public class QuickSorter {

    private QuickSorter() {

    }

    public enum PivotStrategy {
        FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrat)throws NullPointerException {
        if(list.isEmpty()) {
            throw new NullPointerException("The list is empty: Please generate a new list");
        }
        if(pivotStrat == null) {
            throw new NullPointerException("There is no pivot strategy, please pick a strategy");
        }
        Long timer = System.nanoTime();
        PivotStrategy pivot = pivotStrat;
        switch(pivot) {
            case FIRST_ELEMENT:
                firstElementQuickSort(list, 0, list.size()-1);
                break;
            case RANDOM_ELEMENT:
                randomQuickSort(list, 0, list.size()-1);
                break;
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                medianOfThreeRandomElementsQuickSort(list, 0, list.size()-1);
                break;
            case MEDIAN_OF_THREE_ELEMENTS:
                medianOfThreeElementsQuickSort(list, 0, list.size()-1);
                break;
            default:
                standardQuickSort(list, 0, list.size()-1);
        }
        Long endTimer = System.nanoTime();
        Duration time = Duration.ofNanos(endTimer - timer);
        return time;
    }

    //if our pivot strategy is FIRST_ELEMENT
    public static <E extends Comparable<E>> void firstElementQuickSort(ArrayList<E> list, int low, int high) throws IllegalArgumentException {
        if(low >= high) {
            return;
        }
        else {
            swap(list, low, high);
            int newPivot = standardQuickSort(list, low, high);
            firstElementQuickSort(list, low, newPivot - 1);
            firstElementQuickSort(list, newPivot + 1, high);
        }
    }

    //if our pivot strategy is RANDOM_ELEMENT
    public static <E extends Comparable<E>> void randomQuickSort(ArrayList<E> list, int low, int high) {
        if(low >= high) {
            return;
        }
        else {
            int randomPivot = randomNumGenerator(low, high);
            swap(list, randomPivot, high);
            int newPivot = standardQuickSort(list, low, high);
            firstElementQuickSort(list, low, newPivot - 1);
            firstElementQuickSort(list, newPivot + 1, high);
        }
    }

    //if our pivot strategy is MEDIAN_OF_TWO_ELEMENTS
    public static <E extends Comparable<E>> void medianOfThreeRandomElementsQuickSort(ArrayList<E> list, int low, int high) {
        int randomOne = randomNumGenerator(low, high);
        int randomTwo = randomNumGenerator(low, high);
        int randomThree = randomNumGenerator(low, high);
        if(low >= high) {
            return;
        }
        else {
            int medianPivot = findMedian(list, randomOne, randomTwo, randomThree);
            if(medianPivot != high) {
                swap(list, medianPivot, high);
            }
            int newPivot = standardQuickSort(list, low, high);
            medianOfThreeRandomElementsQuickSort(list, low, newPivot - 1);
            medianOfThreeRandomElementsQuickSort(list, newPivot + 1, high);
        }
    }

    //if our pivot strategy is MEDIAN_OF_THREE_ELEMENTS
    public static <E extends Comparable<E>> void medianOfThreeElementsQuickSort(ArrayList<E> list, int low, int high) {
        if(low >= high) {
            return;
        }
        else {
            int medianPivot = findMedian(list, low, high, (low + high)/2);
            if(medianPivot != high) {
                swap(list, medianPivot, high);
            }
            int newPivot = standardQuickSort(list, low, high);
            medianOfThreeElementsQuickSort(list, low, newPivot - 1);
            medianOfThreeElementsQuickSort(list, newPivot + 1, high);
        }
    }

    public static <E extends Comparable<E>> int standardQuickSort(ArrayList<E> list, int low, int high) {
        E pivot = list.get(high);
        int i = low - 1;
        for(int x = low; x < high; x++) {
            if(list.get(x).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, x);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    //helper methods
    public static int randomNumGenerator(int min, int max) throws IllegalArgumentException {
        if(min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("The range is not valid");
        }

        return new Random().nextInt(max - min + 1) + min;
    }

    public static ArrayList<Integer> randomListGenerator(int size) {
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            int rand = random.nextInt();
            list.add(rand);
        }
        return list;
    }

    private static <E extends Comparable<E>> int findMedian(ArrayList<E> list, int x, int y, int z) {
        if ((x < y && y < z) || (z < y && y < x)) {
            return y;
        }
        else if((y < x && x < z) || (z < x && x < y)) {
            return x;
        }
        else {
            return z;
        }
    }

    public static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }



}