class PizzaCity(
    val cityName: String, // Публичное свойство
    val offersCoffee: Boolean = false, // Теперь публичное
    val offersDiscount: Boolean = false, // Теперь публичное
    val offersSauces: Boolean = false // Теперь публичное
) {
    private val orders: MutableList<Order> = mutableListOf()
    private var totalDiscounts = 0.0
    private var totalCoffeeRevenue = 0.0
    private var sauceStatistics: MutableMap<String, Int> = mutableMapOf()

    fun createOrder(customerName: String): Order {
        val newOrder = Order(customerName)
        orders.add(newOrder)
        return newOrder
    }

    fun processOrder(order: Order, photoCheck: Boolean, discountRate: Double = 0.1) {
        order.photoCheck = photoCheck
        val discount = if (offersDiscount && photoCheck) discountRate else 0.0
        totalDiscounts += order.totalPrice() * discount
        totalCoffeeRevenue += order.coffeeCount * 150.0
        if (offersSauces) {
            order.getSauces().forEach { (sauce, price) ->
                sauceStatistics[sauce] = sauceStatistics.getOrDefault(sauce, 0) + price
            }
        }
    }

    fun totalRevenue(): Double {
        return orders.sumOf { it.totalPrice() } - totalDiscounts + totalCoffeeRevenue + sauceStatistics.values.sum()
    }

    fun statistics() {
        val totalOrders = orders.size
        val photoCheckCount = orders.count { it.photoCheck }
        val coffeeOrders = orders.count { it.coffeeCount > 0 }

        println("Статистика по пиццерии в городе $cityName:")
        println("Процент показанных чеков: ${photoCheckCount * 100 / totalOrders}%")
        if (offersCoffee) {
            println("Процент заказов с кофе: ${coffeeOrders * 100 / totalOrders}%")
        }
        if (offersSauces) {
            println("Продажа соусов:")
            sauceStatistics.forEach { (sauce, revenue) ->
                println("  $sauce: $revenue₽")
            }
        }
    }
}
