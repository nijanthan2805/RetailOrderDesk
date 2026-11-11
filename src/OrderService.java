
import java.util.List;

public class OrderService {

    // Linear Search - O(n)
    public static Order linearSearch(List<Order> orders, String id) {
        for (Order order : orders) {
            if (order.getOrderId().equals(id)) {
                return order;
            }
        }
        return null;
    }


    // Binary Search - O(log n)
    public static Order binarySearch(List<Order> orders, String id) {
        int left = 0;
        int right = orders.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = orders.get(mid).getOrderId().compareTo(id);
            if (result == 0) {
                return orders.get(mid);
            }
            else if (result < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return null;
    }

    // Selection Sort - O(n²)
    public static void selectionSort(Order[] orders) {

        for (int i = 0; i < orders.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < orders.length; j++) {
                if (orders[j].getAmount()
                        .compareTo(orders[max].getAmount()) > 0) {

                    max = j;
                }
            }
            Order temp = orders[i];
            orders[i] = orders[max];
            orders[max] = temp;
        }
    }

    // Merge Sort - O(n log n)
    // Descending order

    public static void mergeSort(Order[] orders, int left, int right) {

        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(orders, left, mid);
            mergeSort(orders, mid + 1, right);
            merge(orders, left, mid, right);
        }
    }

    public static void merge(Order[] orders, int left, int mid, int right) {
        Order[] temp = new Order[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (orders[i].getAmount()
                    .compareTo(orders[j].getAmount()) >= 0) {
                temp[k++] = orders[i++];
            }
            else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            orders[left + x] = temp[x];
        }
    }
}
