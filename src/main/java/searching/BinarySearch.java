package searching;

public class BinarySearch {

    public static void main(String[] args) {
        int[] number = {1,3,4,6,44,55,56,75,76,81,83,85,89,100};

        System.out.println(binarySearch(number,55));
        System.out.println(binarySearchV2(number,55));


    }

   public static int binarySearch(int[] number,int key){
        if(number==null || number.length == 0){
            return -1;
        }
        return binarySearch(number,key,0,number.length-1);
   }

    private static int binarySearch(int[] number,int key,int left, int right){
      if(left>right){
          return -1;
      }
      int mid = left+(right-left)/2;
      if(number[mid]==key){
          return mid;
      }
      if(key>number[mid]){
          return binarySearch(number,key,mid+1,right);
      }

      return binarySearch(number,key,left,mid-1);
    }

    public static int binarySearchV2(int[] number,int key){
        if(number==null || number.length == 0){
            return -1;
        }
        int left  = 0;
        int right =  number.length-1;

        while (left<=right){

            int mid = left + (right-left)/2;

            if(number[mid]==key){
                return mid;
            }
            if(key>number[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1;
    }


}
