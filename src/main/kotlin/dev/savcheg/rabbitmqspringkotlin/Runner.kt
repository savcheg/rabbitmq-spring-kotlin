package dev.savcheg.rabbitmqspringkotlin

import dev.savcheg.rabbitmqspringkotlin.RabbitConfig.Companion.topicExchangeName
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Runner(private val receiver: Receiver, private val rabbitTemplate: RabbitTemplate) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        println("Sending message...")
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!")
        receiver.latch.await(10000, TimeUnit.MILLISECONDS)
    }
}