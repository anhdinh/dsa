package searching;

import java.util.ArrayList;
import java.util.List;

public class LinearSearch {
    public static void main(String[] args) {
        int[] number = new int[10];
        number[0] = 1;
        number[1] = 2;
        number[2] = 3;
        number[3] = 4;
        number[4] = 5;
        number[5] = 6;
        number[6] = 5;
        number[7] = 8;
        number[8] = 9;
        number[9] = 10;

        System.out.println(find(number,10));
        System.out.println(find(number,1));
        System.out.println(find(number,100));

        System.out.println(findAll(number,5));


    }

    public static int find(int[] number,int key){
        if(number==null){
            return -1;
        }
        var index = 0;
        for(int i: number){
            if(i==key){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static List<Integer> findAll(int[] number, int key){
        if(number==null){
            return List.of();
        }
        List<Integer> result =  new ArrayList<>();
        var index = 0;
        for(int i: number){
            if(i==key){
                result.add(index);
            }
            index++;
        }
        return result;
    }
}
