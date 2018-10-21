/*
Shell Sort: h-sort array for decreasing sequence of values of h.
h-sort: Insertion sort, with stride length h.
Properties: O(1) extra space, O(n3/2) time as shown, O(n*logn) when nearly sorted
 */
public class ShellSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;

        // use 3x+1 sequence  1, 4, 13, 40, 121, 364, ...
        // start from h close to N/3 and decrease
        while(h < N/3)
            h = 3*h + 1;

        // h-sort the array
        while(h >=1){
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h)
                    swap(a, j, j-h);

            // decrease h
            h /= 3;
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
        ShellSort.sort(test);
        for(Integer i: test)
            System.out.println(i);
    }
}
