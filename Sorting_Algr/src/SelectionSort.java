/*
Selection Sort: In iteration i, find index min of smallest remaining entry.
・Swap a[i] and a[min].
Properties: O(n2) compares, O(n) swaps, O(1) extra space.
 */
public class SelectionSort {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            int min = i;
            for (int j = i+1; j < a.length; j++){
                if (less(a[j], a[min]))
                    min = j;
            }
            swap(a, i, min);
        }
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
        Integer[] test = {14, 2, 5, 3, 7, 9, 0};
        SelectionSort.sort(test);
        for(Integer i: test)
            System.out.println(i);
    }
}
