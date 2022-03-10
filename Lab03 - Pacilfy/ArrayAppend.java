import java.util.Arrays;

class ArrayAppend {
    public static void main( String args[] ) {
        int[] arr = { 10, 20, 30, 40 };
        // System.out.println(Arrays.toString(arr));
      
        // arr[arr.length - 1] = 40; // Assign 40 to the last element
        // System.out.println(Arrays.toString(arr));

        int[][] arr2 = new int[1][4];
        arr2[0] = arr;

        System.out.println(Arrays.toString(arr2[0]));

        arr2 = Arrays.copyOf(arr2, arr2.length + 1);
        arr2[1] = arr;

        System.out.println(Arrays.toString(arr2[1]));
    }
}