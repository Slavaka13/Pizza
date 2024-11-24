fun main() {
    val moscowPizzaCity = PizzaCity("Москва", offersCoffee = true, offersDiscount = true)
    val spbPizzaCity = PizzaCity("Санкт-Петербург", offersCoffee = true, offersDiscount = true, offersSauces = true)

    val cities = mapOf(
        "Москва" to moscowPizzaCity,
        "Санкт-Петербург" to spbPizzaCity
    )

    println("Выберите город:")
    cities.keys.forEachIndexed { index, city -> println("${index + 1}. $city") }

    val cityChoice = readLine()?.toIntOrNull() ?: 0
    val selectedCity = cities.values.elementAtOrNull(cityChoice - 1)

    if (selectedCity == null) {
        println("Неверный выбор города!")
        return
    }

    println("Вы выбрали пиццерию в городе: ${selectedCity.cityName}")

    val order = selectedCity.createOrder("Клиент")
    order.addPizza(Pizza("Маргарита", 850.0, "Средний"))
    order.addCoffee(2)

    if (selectedCity.offersSauces) {
        order.addSauce("Кисло-сладкий", 50)
    }

    selectedCity.processOrder(order, photoCheck = true)

    println("Сводка заказа:")
    println(order.orderSummary())

    println("Общая выручка: ${selectedCity.totalRevenue()}₽")
    selectedCity.statistics()
}
