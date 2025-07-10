public class maxprofits {

        public static int maximumProfit(int[] arr) {
        int min = arr[0];
        int profit = 0;

        for(int i = 0; i < arr.length; i++) {
            int cost = arr[i] = min;
            profit = Math.max(profit, cost);
            min = Math.min(min, arr[i]);
        }

        return profit;
    }

    
    
}
