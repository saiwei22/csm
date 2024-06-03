package test;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr= {6,1,2,7,9,3,4,5,10,8};//10-19
        quick(arr,0,arr.length-1);
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
        int number=arr[i];
        while (start!=end){
            int temp=0;
            while (true){
                if(end<=start||arr[end]<number){
                    break;
                }
                end--;
            }
            while (true){
                if (end<=start||arr[start]>number){
                    break;
                }
                start++;
            }

            temp=arr[end];
            arr[end]=arr[start];
            arr[start]=temp;
        }
        int a=arr[start];
        arr[start]=arr[i];
        arr[i]=a;
        quick(arr,i,start-1);
        quick(arr,start+1,j);
    }

}
