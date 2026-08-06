package dynamicProgramming;


public class HouseRobber {

    public static void main(String[] args) {
       int[] nums = {2,1,1,2,4,5,6,7,8,2,3,4,1,3,4,5,6};
        System.out.println(robber(nums));
    }
    public static int robber(int[] nums){
        if(nums==null){
            throw new IllegalArgumentException("Nums is not null!");
        }

        if(nums.length==0){
            throw new IllegalArgumentException("Nums is not empty!");
        }

        int dp_i_2 = 0;
        int dp_i_1 = 0;
        for (int num : nums) {
            int dpi = Math.max(num + dp_i_2, dp_i_1);
            dp_i_2 = dp_i_1;
            dp_i_1 = dpi;
        }

        return dp_i_1;
    }
}
