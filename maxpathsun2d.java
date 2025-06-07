import java.util.Arrays;

public class maxpathsun2d {


        // now, we shall solve the maximum or minimum path sum problem using recursiion
    // possible the last problem in the 2d dp series
    public static int getMaxUtil(int i, int j, int m, int[][] matrix, int[][] dp) {
        // writing the base cases
        if (i == 0) {
            return matrix[i][j];
        }
        if (j < 0 || j >= m) {
            return (int) Math.pow(-10, 9);
        }
        // writing the memoization case
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        // now, writing the up, right up and left up caes while traversing upwards
        int up = matrix[i][j] + getMaxUtil(i - 1, j, m, matrix, dp);
        int right_up = matrix[i][j] + getMaxUtil(i - 1, j - 1, m, matrix, dp);
        int left_up = matrix[i][j] + getMaxUtil(i - 1, j + 1, m, matrix, dp);
        // retunrbing the max and then adding the memoization case
        return dp[i][j] = Math.max(up, Math.max(right_up, left_up));
    }

    // new, lets write teh above function considering the tabulation apppiorach
    // for a tabulation approach, I will be using three basuc rules
    // 1. write the base cases
    // 2. write the loops to traverse opposite of recurssion
    // 2. copt the bases of traversal and chage the to the dp
    public static int getMaxPathSUmTabulisation(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[0][i] = matrix[0][i];
        }

        // iteration loop(step 2)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int up = matrix[i][j] + dp[i - 1][j];

                int left_up = matrix[i][j];
                if (j > 0) {
                    left_up +=  dp[i - 1][j + 1];
                } else {
                    left_up = (int) Math.pow(-10, 9);
                }
                int right_up = matrix[i][j];
                if (j <= m) {
                    right_up = matrix[i][j] + dp[i - 1][j + 1];
                } else {
                    right_up = (int) Math.pow(-10, 9);
                }
                dp[i][j] = Math.max(up, Math.max(left_up, right_up));

            }

        }
        int maxi = Integer.MIN_VALUE;
        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, dp[n - 1][j]);
        }
        return maxi;
    }

    static int getMaxPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        int maxi = Integer.MIN_VALUE;

        // For each starting column, find the maximum path sum and update maxi
        for (int j = 0; j < m; j++) {
            int ans = getMaxUtil(n - 1, j, m, matrix, dp);
            maxi = Math.max(maxi, ans);
        }

        return maxi;
    }
    
}
