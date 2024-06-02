package test;

/**
 *
 * 二分查找
 *
 */
public class Test {
    public static void main(String[] args) {
        int[] a ={1,2,3,4,5,6,7,8,9,11,22,33,44,55,56,67,678,990,9088};
        int sq = sq(a, 44);
        System.out.println(sq);

    }
    public static int sq(int[] i,int a){
        int min=0;
        int max=i.length-1;
        int mid;
        while (true){
            mid=(max+min)/2;
            if(min>mid){
                return -1;
            }
            if (a>i[mid]){
                min=mid+1;
            }else if (a<i[mid]){
                max=mid-1;
            }else {
                return mid;
            }
        }
    }
}
