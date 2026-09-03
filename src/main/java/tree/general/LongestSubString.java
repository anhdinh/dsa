package tree.general;


import java.util.ArrayList;
import java.util.List;

public class LongestSubString {



    public static void main(String[] args) {
        String s = "abcabcbb";
    }


    public static int lengthOfLongestSubstring(String s) {
        int maxSubStringLength = 0;
        char[] data = s.toCharArray();
        for(int i = 0;i < data.length;i++){
            List<Character> temp =  new ArrayList<>();
            for(int j = i;j < data.length;j++){
                if(temp.contains(data[j])){
                    break;
                }else{
                    temp.add(data[j]);
                }
            }
            if (temp.size() > maxSubStringLength) {
                maxSubStringLength = temp.size();
            }
        }
       return maxSubStringLength;
    }
}
