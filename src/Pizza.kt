class Pizza(
    val name: String,
    val price: Double,
    val size: String
) {
    fun description(): String {
        return "Пицца: $name, Размер: $size, Цена: $price₽"
    }
}
