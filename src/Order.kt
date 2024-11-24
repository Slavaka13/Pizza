class Order(
    val customerName: String,
    private val pizzas: MutableList<Pizza> = mutableListOf(),
    var coffeeCount: Int = 0,
    var photoCheck: Boolean = false
) {
    private val sauces: MutableMap<String, Int> = mutableMapOf()

    fun addPizza(pizza: Pizza) {
        pizzas.add(pizza)
    }

    fun addCoffee(count: Int) {
        coffeeCount += count
    }

    fun addSauce(sauce: String, price: Int) {
        sauces[sauce] = sauces.getOrDefault(sauce, 0) + price
    }

    fun getSauces(): Map<String, Int> {
        return sauces.toMap()
    }

    fun totalPrice(discount: Double = 0.0): Double {
        val pizzaTotal = pizzas.sumOf { it.price }
        val coffeeTotal = coffeeCount * 150.0
        val sauceTotal = sauces.values.sum()
        val total = pizzaTotal + coffeeTotal + sauceTotal
        return total - (total * discount)
    }

    fun orderSummary(discount: Double = 0.0): String {
        val pizzaDetails = pizzas.joinToString("\n") { it.description() }
        val coffeeDetails = if (coffeeCount > 0) "Кофе: $coffeeCount шт." else "Кофе: нет"
        val sauceDetails = if (sauces.isNotEmpty()) {
            sauces.entries.joinToString("\n") { "Соус: ${it.key}, Цена: ${it.value}₽" }
        } else {
            "Соусы: нет"
        }
        return """
            Сводка заказа для $customerName:
            $pizzaDetails
            $coffeeDetails
            $sauceDetails
            Итого со скидкой: ${totalPrice(discount)}₽
        """.trimIndent()
    }
}
