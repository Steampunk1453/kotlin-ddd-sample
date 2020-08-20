package cqrs.ecommerce.api.domain.order

import cqrs.ecommerce.api.domain.order.customer.Address
import cqrs.ecommerce.api.domain.order.customer.Customer
import org.amshove.kluent.shouldBe
import org.javamoney.moneta.Money
import org.junit.Test
import java.util.UUID
import javax.money.Monetary

class OrderTest {
    private val address = Address(UUID.randomUUID(),"Foo", 123, "LA", "US")
    private val customer = Customer(UUID.randomUUID(), "Jhon", address)

    @Test
    fun `Must to add a product to an order successfully`() {
        val order = Order(UUID.randomUUID(), customer)
        val product = Product(UUID.randomUUID(), "Book", Money.of(30.50, Monetary.getCurrency("USD")))

        order.addProduct(product, 10)

        order.items().count() shouldBe 1
    }
}