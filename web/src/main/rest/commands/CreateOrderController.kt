package cqrs.ecommerce.api.web.rest.commands

import cqrs.ecommerce.api.application.order.commands.CreateOrderCommand
import cqrs.ecommerce.api.web.rest.models.requests.CreateOrderRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class CreateOrderController(val commandGateway: CommandGateway) {

    @PostMapping("/orders")
    fun create(@RequestBody request: CreateOrderRequest): ResponseEntity<UUID> {
        val command = CreateOrderCommand(UUID.fromString(request.customerId))
        val orderId = commandGateway.sendAndWait<UUID>(command)

        return ResponseEntity(orderId, HttpStatus.CREATED)
    }

}