package ii_collections

fun example8() {
    val numbers = listOf(1, 3, -4, 2, -11)

    // The details (how multi-assignment works) will be explained later in the 'Conventions' task
    val (positive, negative) = numbers.partition { it > 0 }

    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
    // Return customers who have more undelivered orders than delivered
    // todoCollectionTask()
    val customer_more_undelivered = this.customers.partition {
        val (order_delivered, order_undelivered) = it.orders.partition(Order::isDelivered)

        order_undelivered.size > order_delivered.size
    }.first
    return customer_more_undelivered.toSet()
}
