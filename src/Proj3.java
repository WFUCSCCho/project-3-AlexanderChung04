/*
        ∗ @file: Proj3.java
∗ @description: This program implements bubble sort, merge sort, quick sort, heap sort, and odd-even transposition sort algorithms. Performs the algorithms on already sorted, shuffled, and reversed datasets lists as input, times the performance of different set of inputs
        ∗ @author: Alexander Chung
∗ @date: November 14, 2025
                                              */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.*;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort, divide and conquer approach that splits array in half, recursively sorts each half, and merges the two
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me

        if(left < right){
            int mid = left + (right - left) / 2;

            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);

            merge(a,left,mid,right);
        }
    }

    // helper method for mergeSort that merges the sorted subarrays into a single sorted array
    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me

        int one = mid - left + 1;
        int two = right - mid;

        ArrayList<T> L = new ArrayList<>(one);
        ArrayList<T> R = new ArrayList<>(two);

        for(int i = 0; i < one; i++){
            L.add(a.get(left+i));
        }

        for(int j = 0; j < two; j++){
            R.add(a.get(mid + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = left;
        while(i < one && j < two){
            if(L.get(i).compareTo(R.get(j)) <= 0){
                a.set(k,L.get(i));
                i++;
            }
            else{
                a.set(k,R.get(j));
                j++;
            }
            k++;
        }

        while(i < one){
            a.set(k, L.get(i));
            i++;
            k++;
        }
        while(j < two){
            a.set(k, R.get(j));
            j++;
            k++;
        }
    }

    // Quick Sort, divide and conquer, picks a pivot and partitions into two groups
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right){

            int part = partition(a,left,right);

            quickSort(a,left,part -1);
            quickSort(a, part+1,right);
        }

    }

    //helper method for quickSort, partitioning algorithm, gets pivot out of way by swapping with last element, i starts ad first element and moves forward, j starts at last element and moves backwards
    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me

        T pivot = a.get(left);
        swap(a,left,right);

        int i = left;
        int j = right -1;

        while (i < j) {
            while(i < j && a.get(i).compareTo(pivot) <= 0){
                i++;
            }

            while(i < j && a.get(j).compareTo(pivot) >= 0){
                j--;
            }

            if(i < j){
                swap(a, i, j);
            }
        }

        if(a.get(i).compareTo(pivot) >= 0){
            swap(a,i,right);
            return i;
        }
        else {
            swap(a,i+1, right);
            return i+1;
        }

    }

    //swaps two elements in an arrayList, i is the index of first element, j is index of second element
    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort, uses binary heap to sort, heapifies array by starting at middle and working towards the root percolating down at each step
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me

        int p = right - left + 1;

        for(int i = left + p/2 - 1; i >= left; i--){

            heapify(a,i,right);
        }

        for(int j = right; j > left; j--){

            swap(a,left,j);

            heapify(a,left,j-1);
        }
    }

    //helper method for heapSort, maintains maxheap property recursively checks if parents are larger than children
    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me

        int big = left;
        int l = 2 * left + 1;
        int r = 2 * left + 2;

        if(l <= right && a.get(l).compareTo(a.get(big)) > 0){
            big = l;
        }

        if(r <= right && a.get(r).compareTo(a.get(big)) > 0){
            big = r;
        }

        if(big != left){
            swap(a, left, big);

            heapify(a, big, right);
        }
    }

    // Bubble Sort, returns comparison count, compares neighboring elements, swaps if out of order, stops when a whole pass completes without any swaps
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me

        int comparisons =0;
        boolean swapped;

        for(int i = 0; i < size - 1; i++){
            swapped = false;

            for(int j = 0; j < size - i - 1; j++){
                comparisons++;

                if(a.get(j).compareTo(a.get(j+1)) > 0){

                    swap(a,j,j+1);
                    swapped = true;

                }
            }

            if(swapped == false){
                break;
            }
        }

        return comparisons;

    }

    // Odd-Even Transposition Sort, compares all odd-even indexed pairs of adjacent elements in a list and switches them  is they are out of order, repeats for even-odd indexed pairs and keeps alternating until list is sorted
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me

        boolean isSorted = false;
        int comparisons = 0;

        while (!isSorted) {

            isSorted = true;


        for(int i = 0; i < size - 1; i += 2) {
            comparisons++;

            if(a.get(i).compareTo(a.get(i + 1)) > 0) {
                swap(a, i, i + 1);

                isSorted = false;

            }
        }

        for(int j = 1; j< size - 1; j+=2){
            comparisons++;

            if(a.get(j).compareTo(a.get(j+1)) > 0){
                swap(a, j, j+1);
                isSorted = false;
            }
        }
    }
        return comparisons;
    }

    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...
        if (args.length != 3) {
            System.err.println("Usage:  <input file> <algorithm type> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String algorithmType = args[1];
        int numLines = Integer.parseInt(args[2]);
        long startTime;
        long endTime;
        int comparisons = 0;

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME

        ArrayList<Ramen> list = new ArrayList<>();
        int count = 0;
        while(inputFileNameScanner.hasNextLine() && count < numLines){
            String ln = inputFileNameScanner.nextLine().trim();
            if(!ln.isEmpty()){
                String[] data = ln.split(",");

                int reviewNum = Integer.parseInt(data[0].trim());
                String brand = data[1].trim();
                String variety = data[2].trim();
                String style = data[3].trim();
                String country = data[4].trim();
                double stars = Double.parseDouble(data[5].trim());

                Ramen newRamen = new Ramen(reviewNum, brand,variety, style,country, stars);
                list.add(newRamen);
                count++;
            }
        }
        inputFileNameScanner.close();
        inputFileNameStream.close();


        ArrayList<Ramen> sorted = new ArrayList<>(list);
        ArrayList<Ramen> shuffled = new ArrayList<>(list);
        ArrayList<Ramen> reversed = new ArrayList<>(list);

        Collections.shuffle(shuffled);
        Collections.sort(sorted);
        Collections.sort(reversed, Collections.reverseOrder());

        FileWriter analysisWrite = new FileWriter("analysis.txt", true);
        FileWriter sortWriter = new FileWriter("sorted.txt", false);

        switch(algorithmType){

            case "merge" ->{

                ArrayList<Ramen> mSorted = new ArrayList<>(sorted);
                startTime = System.nanoTime();
                mergeSort(mSorted, 0, mSorted.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Sorted mergeSort lines: " + numLines + " time: " +  (endTime - startTime));
                analysisWrite.write("Sorted mergeSort " + numLines + ", " + (endTime - startTime) + "\n");
                sortWriter.write("Sorted mergeSort " + "\n");
                for(Ramen ramen : mSorted){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> mShuffled = new ArrayList<>(shuffled);
                startTime = System.nanoTime();
                mergeSort(mShuffled, 0, mShuffled.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Shuffled mergeSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Shuffled mergeSort " + numLines + ", " + (endTime - startTime) + "\n");
                sortWriter.write("Shuffled mergeSort " + "\n");
                for(Ramen ramen : mShuffled){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> mReversed = new ArrayList<>(reversed);
                startTime = System.nanoTime();
                mergeSort(mReversed, 0, mReversed.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Reversed mergeSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Reversed mergeSort " + numLines + ", " + (endTime - startTime));
                sortWriter.write("Reversed mergeSort " + "\n");
                for(Ramen ramen : mReversed){
                    sortWriter.write(ramen.toString() + "\n");
                }
            }


            case "quick" ->{

                ArrayList<Ramen> qSorted = new ArrayList<>(sorted);
                startTime = System.nanoTime();
                quickSort(qSorted, 0, qSorted.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Sorted quickSort lines: " + numLines + " time: " + (endTime-startTime));
                analysisWrite.write("Sorted quickSort " + numLines + ", " + (endTime - startTime));
                sortWriter.write("Sorted quickSort " + "\n");
                for(Ramen ramen : qSorted){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> qShuffled = new ArrayList<>(shuffled);
                startTime = System.nanoTime();
                quickSort(qShuffled, 0, qShuffled.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Shuffled quickSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Shuffled quickSort " + numLines + ", " + (endTime - startTime) + "\n");
                sortWriter.write("Shuffled quickSort " + "\n");
                for(Ramen ramen : qShuffled){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> qReversed = new ArrayList<>(reversed);
                startTime = System.nanoTime();
                quickSort(qReversed, 0, qReversed.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Reversed quickSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Reversed quickSort " + numLines + ", " + (endTime - startTime));
                sortWriter.write("Reversed quickSort " + "\n");
                for(Ramen ramen : qReversed){
                    sortWriter.write(ramen.toString() + "\n");
                }
            }

            case "heap" ->{
                ArrayList<Ramen> hSorted = new ArrayList<>(sorted);
                startTime = System.nanoTime();
                heapSort(hSorted, 0, hSorted.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Sorted heapSort lines: " + numLines + " time: " + (endTime-startTime));
                analysisWrite.write("Sorted heapSort " + numLines + ", " + (endTime - startTime));
                sortWriter.write("Sorted heapSort " + "\n");
                for(Ramen ramen : hSorted){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> hShuffled = new ArrayList<>(shuffled);
                startTime = System.nanoTime();
                heapSort(hShuffled, 0, hShuffled.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Shuffled heapSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Shuffled heapSort " + numLines + ", " + (endTime - startTime) + "\n");
                sortWriter.write("Shuffled heapSort " + "\n");
                for(Ramen ramen : hShuffled){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> hReversed = new ArrayList<>(reversed);
                startTime = System.nanoTime();
                heapSort(hReversed, 0, hReversed.size() - 1);
                endTime = System.nanoTime();

                System.out.println("Reversed heapSort lines: " + numLines + " time: " + (endTime - startTime));
                analysisWrite.write("Reversed heapSort " + numLines + ", " + (endTime - startTime));
                sortWriter.write("Reversed heapSort " + "\n");
                for(Ramen ramen : hReversed){
                    sortWriter.write(ramen.toString() + "\n");
                }

            }

            case "bubble" ->{

                ArrayList<Ramen> bSorted = new ArrayList<>(sorted);
                startTime = System.nanoTime();
                comparisons = bubbleSort(bSorted, bSorted.size());
                endTime = System.nanoTime();

                System.out.println("Sorted bubbleSort lines: " + numLines + " time: " + (endTime - startTime) + " comparisons: " + comparisons);
                analysisWrite.write("Sorted bubbleSort " + numLines + ", " + (endTime - startTime) + ", " + comparisons + "\n");
                sortWriter.write("Sorted bubbleSort " + "\n");
                for(Ramen ramen : bSorted){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> bShuffled = new ArrayList<>(shuffled);
                startTime = System.nanoTime();
                comparisons = bubbleSort(bShuffled, bShuffled.size());
                endTime = System.nanoTime();

                System.out.println("Shuffled bubbleSort lines: " + numLines + " time: " + (endTime - startTime) + " comparisons: " + comparisons);
                analysisWrite.write("Shuffled bubbleSort " + numLines + ", " + (endTime - startTime) + ", " + comparisons + "\n");
                sortWriter.write("Shuffled bubbleSort " + "\n");
                for(Ramen ramen : bShuffled){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> bReversed =  new ArrayList<>(reversed);
                startTime = System.nanoTime();
                comparisons = bubbleSort(bReversed, bReversed.size());
                endTime = System.nanoTime();

                System.out.println("Reversed bubbleSort lines: " + numLines + " time: " + (endTime - startTime) + " comparisons: " + comparisons);
                analysisWrite.write("Reversed bubbleSort " + numLines + ", " + (endTime - startTime) + ", " + comparisons);
                sortWriter.write("Reversed bubbleSort " + "\n");
                for(Ramen ramen : bReversed){
                    sortWriter.write(ramen.toString() + "\n");
                }
            }

            case "transposition" ->{

                ArrayList<Ramen> tSorted = new ArrayList<>(sorted);
                comparisons = transpositionSort(tSorted, tSorted.size());

                System.out.println("Sorted transpositionSort lines: " + numLines + " comparisons: " + comparisons);
                analysisWrite.write("Sorted transpositionSort " + numLines + ", " + comparisons + "\n");
                sortWriter.write("Sorted transpositionSort " + "\n");
                for(Ramen ramen : tSorted){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> tShuffled = new ArrayList<>(shuffled);
                comparisons = transpositionSort(tShuffled, tShuffled.size());

                System.out.println("Shuffled transpositionSort lines: " + numLines + " comparisons: " + comparisons);
                analysisWrite.write("Shuffled transpositionSort " + numLines + ", " +  comparisons + "\n");
                sortWriter.write("Shuffled transpositionSort " + "\n");
                for(Ramen ramen : tShuffled){
                    sortWriter.write(ramen.toString() + "\n");
                }

                ArrayList<Ramen> tReversed =  new ArrayList<>(reversed);
                comparisons = transpositionSort(tReversed, tReversed.size());

                System.out.println("Reversed transpositionSort lines: " + numLines + " comparisons: " + comparisons);
                analysisWrite.write("Reversed transpositionSort " + numLines + ", " + comparisons);
                sortWriter.write("Reversed transpositionSort " + "\n");
                for(Ramen ramen : tReversed){
                    sortWriter.write(ramen.toString() + "\n");
                }
            }

            default -> System.err.println("not real algorithm");

        }

        analysisWrite.close();
        sortWriter.close();
    }
}
