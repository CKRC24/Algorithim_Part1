/*
Insertion Sort: In iteration i, swap a[i] with each larger entry to its left.
Properties: O(1) extra space, O(n2) comparisons and swaps, O(n) time when nearly sorted
 */
public class InsertionSort {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++)
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                swap(a, j, j-1);
    }

    // check if comparable objects x, y match the condition: x < y
    private static boolean less(Comparable x, Comparable y){
        return x.compareTo(y) < 0;
    }

    // swap the objects in the array
    private static void swap(Comparable[] a, int indx1, int indx2){
        Comparable tmp = a[indx1];
        a[indx1] = a[indx2];
        a[indx2] = tmp;
    }

    // test
    public static void main(String[] args) {
        Integer[] test = {9, 7, 5, 3, 1};
        InsertionSort.sort(test);
        for(Integer i: test)
            System.out.println(i);
    }
}
