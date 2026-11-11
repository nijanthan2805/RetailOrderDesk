
import java.util.*;

import java.time.temporal.ChronoUnit;

public class DuplicateChecker {

    public static List<Order> findDuplicates(List<Order> orders) {
        Set<String> seen = new HashSet<>();
        List<Order> duplicates = new ArrayList<>();
        for (Order order : orders) {

            String minute = order.createdAt
                    .truncatedTo(ChronoUnit.MINUTES).toString();

            String key = order.customerId + "|"
                    + order.amount.stripTrailingZeros().toPlainString()
                    + "|" + minute;
            if (!seen.add(key)) {

                duplicates.add(order);
            }
        }
        return duplicates;
    }
}
