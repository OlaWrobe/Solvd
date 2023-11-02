package sorting;

public class SortingAlgorythms {
    static void bubbleSort(int array[]) {
        boolean swapped;
        int temp;

        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void selectionSort(int array[]) {
        int temp;
        int index;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    static void printArray(int array[]) {
        for (int a : array) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {
        int[] bubbleSortArray = {2, 1, 9, 3, 5, 0, 4};
        int[] selectionSortArray = {2, 1, 9, 3, 5, 0, 4};
        System.out.println("Before sorting:");
        printArray(bubbleSortArray);
        selectionSort(selectionSortArray);
        bubbleSort(bubbleSortArray);
        System.out.println("\nSelection sort:");
        printArray(selectionSortArray);
        System.out.println("\nBubble sort:");
        printArray(bubbleSortArray);
    }
}