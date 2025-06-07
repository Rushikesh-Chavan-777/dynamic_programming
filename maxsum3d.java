import java.util.Arrays;

public class maxsum3d {


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
    
}
