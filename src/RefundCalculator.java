
import java.util.Arrays;

public class RefundCalculator {

    // Greedy Approach

    public static int greedy(int amount, int[] coins) {

        int count = 0;

        for (int coin : coins) {

            count += amount / coin;

            amount = amount % coin;
        }

        if (amount != 0) {
            return -1;
        }

        return count;
    }


    // Dynamic Programming

    public static int dynamicProgramming(int amount, int[] coins) {

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (coin <= i) {

                    dp[i] = Math.min(
                            dp[i],
                            dp[i - coin] + 1
                    );
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
