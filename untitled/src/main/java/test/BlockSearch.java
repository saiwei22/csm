package test;

public class BlockSearch {
    public static void main(String[] args) {
        int[] arr= {23,30,11,14,36,6,14,10,24,15, //0-9
                    47,54,38,57,48,58,39,43,68,50};//10-19
        Block b1=new Block(36,0,9);
        Block b2=new Block(68,10,19);
        Block[] blocks={b1,b2};
        int i=99;
        int index = getIndex(blocks, arr, i);
        System.out.println(index);
    }

    public static int getIndex(Block[] blocks,int[] arr,int i) {
        int index = findIndex(blocks, i);
        if (index<0)
            return index;
        int starIndex = blocks[index].getStarIndex();
        int endIndex = blocks[index].getEndIndex();
        for (int a=starIndex;a<=endIndex;a++){
            if(arr[a]==i){
                return a;
            }
        }
        return -1;
    }

    private static int findIndex(Block[] blocks, int i) {
        for (int a=0;a<blocks.length;a++){
            if (i<=blocks[a].getMax())
                return a;
        }
        return -1;
    }
}
class Block{    //创建分块类
    private int max;
    private int starIndex;
    private int endIndex;

    public int getMax() {
        return max;
    }

    public int getStarIndex() {
        return starIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public Block(int max, int starIndex, int endIndex) {
        this.max = max;
        this.starIndex = starIndex;
        this.endIndex = endIndex;
    }
}