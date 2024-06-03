package test;

/**
 *
 * 快速排序
 *
 *
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr= {6,1,2,7,9,3,4,5,10,8};//10-19
        for(int i=0;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
//        quick(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+",");
        }
    }
    public static void quick(int[] arr,int i,int j) {
        int start=i;
        int end=j;
        if(start>end){
            return;
        }
        //基准数
        int number=arr[i];
        while (start!=end){
            int temp=0;
            //end后往前找比基准数小的
            while (true){
                if(end<=start||arr[end]<number){
                    break;
                }
                end--;
            }
            //start从前往后找比基准数大的
            while (true){
                if (end<=start||arr[start]>number){
                    break;
                }
                start++;
            }
        //找到之后交换start与end的位置,直到start等于end
            temp=arr[end];
            arr[end]=arr[start];
            arr[start]=temp;
        }
        //start等于end是基准数的位置让基准数归位,基准数左边比基准数大,右边比基准数小
        int a=arr[start];
        arr[start]=arr[i];
        arr[i]=a;
        //递归重复该方法排序
        quick(arr,i,start-1);
        quick(arr,start+1,j);
    }

}
