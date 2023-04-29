import java.util.*;
import java.lang.Comparable;
import java.lang.System;
import java.time.Duration;

public class QuickSorter {

    private QuickSorter() {

    }

    public enum PivotStrategy {
        FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_TWO_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrat)throws NullPointerException {
        
        return null;
    }

    //if our pivot strategy is FIRST_ELEMENT
    public static <E extends Comparable<E>> void firstElementQuickSort(ArrayList<E> list, int low, int high) throws IllegalArgumentException {

    }

    //if our pivot strategy is RANDOM_ELEMENT
    public static <E extends Comparable<E>> void randomQuickSort(ArrayList<E> list, int low, int high) {

    }

    //if our pivot strategy is MEDIAN_OF_TWO_ELEMENTS
    public static <E extends Comparable<E>> void medianOfTwoElementsQuickSort(ArrayList<E> list, int low, int high) {

    }

    //if our pivot strategy is MEDIAN_OF_THREE_ELEMENTS
    public static <E extends Comparable<E>> void medianOfThreeElementsQuickSort(ArrayList<E> list, int low, int high) {

    }

    public static <E extends Comparable<E>> int standardQuickSort(ArrayList<E> list, int low, int high) {
        return -1;
    }

    //helper methods
    public static int randomNumGenerator(int min, int max) throws IllegalArgumentException {
        return -1;
    }

    private static <E extends Comparable<E>> int findMedian(ArrayList<E> list, int x, int y, int z) {
        return -1;
    }



}