// Load orders from CSV file

public static List<Order> loadOrders(String fileName)
        throws IOException {

    List<Order> orders = new ArrayList<>();

    List<String> lines = Files.readAllLines(
            Path.of(fileName)
    );

    // Skip CSV header

    for (int i = 1; i < lines.size(); i++) {

        String[] data = lines.get(i).split(",");

        String orderId = data[0].trim();

        String customerId = data[1].trim();

        BigDecimal amount = new BigDecimal(data[2].trim());

        LocalDateTime createdAt = LocalDateTime.parse(
                data[3].trim().replace(" ", "T")
        );

        orders.add(new Order(
                orderId,
                customerId,
                amount,
                createdAt
        ));
    }

    return orders;
}


        void main(String[] args) throws IOException {

            String fileName = args.length > 0
                    ? args[0] : "orders.csv";

            List<Order> orders = loadOrders(fileName);

            IO.println("Total Orders: " + orders.size());


            // TASK 1: SEARCHING

            IO.println("\n--- TASK 1: SEARCHING ---");

            List<Order> sortedOrders = new ArrayList<>(orders);

            sortedOrders.sort(
                    Comparator.comparing(Order::getOrderId)
            );

            String targetId = orders.get(orders.size() - 1)
                    .getOrderId();


            long start = System.nanoTime();

            Order linearResult = null;

            for (int i = 0; i < 1000; i++) {

                linearResult = OrderService.linearSearch(
                        orders, targetId
                );
            }

            long linearTime = System.nanoTime() - start;


            start = System.nanoTime();

            Order binaryResult = null;

            for (int i = 0; i < 1000; i++) {

                binaryResult = OrderService.binarySearch(
                        sortedOrders, targetId
                );
            }

            long binaryTime = System.nanoTime() - start;


            IO.println("Linear Search: " + linearResult);

            IO.println("Binary Search: " + binaryResult);

            IO.println("Linear Search Time: "
                    + linearTime + " ns");

            IO.println("Binary Search Time: "
                    + binaryTime + " ns");


            // TASK 2: SORTING

            IO.println("\n--- TASK 2: SORTING ---");

            int n = Math.min(10000, orders.size());

            Order[] original = orders.subList(0, n)
                    .toArray(new Order[0]);


            // Selection Sort

            Order[] selection = original.clone();

            start = System.nanoTime();

            OrderService.selectionSort(selection);

            long selectionTime = System.nanoTime() - start;


            // Merge Sort

            Order[] merge = original.clone();

            start = System.nanoTime();

            OrderService.mergeSort(merge, 0, merge.length - 1);

            long mergeTime = System.nanoTime() - start;


            // Built-in Sort

            Order[] builtin = original.clone();

            start = System.nanoTime();

            Arrays.sort(
                    builtin,
                    Comparator.comparing(Order::getAmount).reversed()
            );

            long builtinTime = System.nanoTime() - start;


            IO.println("Selection Sort: "
                    + selectionTime + " ns");

            IO.println("Merge Sort: "
                    + mergeTime + " ns");

            IO.println("Built-in Sort: "
                    + builtinTime + " ns");


            IO.println("\nTop 3 Orders:");

            for (int i = 0; i < Math.min(3, builtin.length); i++) {

                IO.println(builtin[i]);
            }


            // TASK 3: DUPLICATE DETECTION

            IO.println("\n--- TASK 3: DUPLICATES ---");

            List<Order> duplicates =
                    DuplicateChecker.findDuplicates(orders);

            IO.println("Duplicate Orders: "
                    + duplicates.size());

            for (Order order : duplicates) {

                IO.println(order);
            }


            // TASK 4: STACK

            IO.println("\n--- TASK 4: STACK ---");

            MyStack<String> stack = new MyStack<>();

            stack.push("Address: Chennai");

            stack.push("Address: Bangalore");

            stack.push("Address: Coimbatore");

            IO.println("Undo Edit: " + stack.pop());


            // TASK 4: QUEUE

            IO.println("\n--- TASK 4: QUEUE ---");

            MyQueue<String> queue = new MyQueue<>();

            queue.enqueue("Order 101");

            queue.enqueue("Order 102");

            queue.enqueue("Order 103");


            while (!queue.isEmpty()) {

                IO.println("Dispatch: " + queue.dequeue());
            }


            // TASK 5: REFUND

            IO.println("\n--- TASK 5: REFUND ---");

            int[] coins = {4, 3, 1};

            int amount = 6;

            int greedyResult = RefundCalculator.greedy(
                    amount, coins
            );

            int dpResult = RefundCalculator.dynamicProgramming(
                    amount, coins
            );


            IO.println("Refund Amount: " + amount);

            IO.println("Greedy Result: "
                    + greedyResult + " coins");

            IO.println("DP Result: "
                    + dpResult + " coins");
        }
