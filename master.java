import java.util.Arrays;

public class master {

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
                    left_up += dp[i - 1][j + 1];
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // now, solving the first 3d dp proble,, which is chocolate puckup by two
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// starting
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// positions
    public static int chocolatepickupUtil(int i, int j1, int j2, int n, int m, int[][] matrix, int[][][] dp) {
        // base cases
        if (i == n - 1) {
            if (j1 == j2) {
                return matrix[i][j1];
            } else {
                return matrix[i][j1] + matrix[i][j2];
            }
        }
        // writing edge cases
        if (j1 < 0 || j2 >= m || j2 < 0 || j1 >= m) {
            return (int) Math.pow(-10, 9);
        }
        int maxi = 0;
        // exploring all paths
        if (dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j1 == j2) {
                    maxi = Math.max(maxi, matrix[i][j1] + chocolatepickupUtil(i + 1, j1 + j, j2 + k, n, m, matrix, dp));
                } else {
                    maxi = Math.max(maxi, matrix[i][j1] + matrix[i][j2]
                            + chocolatepickupUtil(i + 1, j1 + j, j2 + k, n, m, matrix, dp));
                }
            }
        }
        return dp[i][j1][j2] = maxi;
    }

    public static int chocolatepickupTabulation(int[][] matrix) {
        // creating the dp
        int n = matrix.length;
        int m = matrix[0].length;
        int[][][] dp = new int[n][m][m];
        // writing the base cases
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    dp[n - 1][i][j] = matrix[n - 1][j];
                } else {
                    dp[n - 1][i][j] = matrix[n - 1][i] + matrix[n - 1][j];
                }
            }
        }
        // declaring the maxi variable
        // int maxi = 0;
        // iterating the loops. Condition 2
        for (int i = n - 1; i >= 0; i++) {
            for (int k = 0; k < m; k++) {
                for (int l = 0; l < m; l++) {
                    int maxi = 0;
                    for (int j1 = -1; j1 <= +1; j1++) {
                        for (int j2 = -1; j2 <= +1; j2++) {
                            int ans;
                            if (k == l) {
                                ans = matrix[i][k];
                                // ans = Math.max(maxi, matrix[i][k] + dp[i + 1][j1][j1]);
                            } else {
                                ans = matrix[i][k] + matrix[i][l];
                                // ans = Math.max(maxi, matrix[i][k] + matrix[i][j2] + dp[i + 1][j1][j2]);
                            }
                            // Check if the indices are valid
                            if ((k + j1 < 0 || k + j1 >= m) || (l + j2 < 0 || l + j2 >= m))
                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += dp[i + 1][k + j1][l + j2];
                            // Update maxi with the maximum result
                            maxi = Math.max(ans, maxi);

                        }
                    }
                    dp[i][k][l] = maxi;
                }
            }
        }
        return dp[0][0][m - 1];
    }

    public static int chocolatepickup(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][][] dp = new int[n][m][m];
        for (int[][] i : dp) {
            for (int[] j : i) {
                Arrays.fill(j, -1);
            }
        }

        return chocolatepickupUtil(0, 0, m - 1, n, m, matrix, dp);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // now we shall solve the famous problems on dp on stocks
    public static int maximumProfit(int[] arr) {
        int min = arr[0];
        int profit = 0;

        for (int i = 0; i < arr.length; i++) {
            int cost = arr[i] = min;
            profit = Math.max(profit, cost);
            min = Math.min(min, arr[i]);
        }

        return profit;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // now, gponnna attemp teh problem of MCM(Matrix Chain Multiplication)
    // first gonna write the recursion, then the multiplication
    public static int MatrixMultiplicationUtil(int i, int j, int[] arr, int[][] dp) {
        // writing the base case
        if (i == j) {
            return 0;
        }
        // writing the recurive cases
        // we simply have to iterate and give the miimum
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int mini = Integer.MIN_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int ans = arr[i - 1] * arr[k] * arr[j] + MatrixMultiplicationUtil(i, k, arr, dp);
            mini = Math.min(ans, mini);
        }

        return dp[i][j] =  mini;
    }


    public static int MatrixMultplicationTabulation(int[] arr, int N) {


        int[][] dp = new int[N][N];
        for (int[] o : dp) {
            Arrays.fill(dp, -1);
        }
        //writing the base case first
        for(int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }

        //now, loping what was changing in the memoiation approach 
        for(int i = ) {}
    }

    public static int MatrixMultiplication(int[] arr, int N) {
        int i = 1;
        int j = N - 1;
        int[][] dp = new int[N][N];
        for (int[] o : dp) {
            Arrays.fill(dp, -1);
        }
        return MatrixMultiplicationUtil(i, j, arr, dp);
    }

    public static void main(String[] args) {

    }

}
