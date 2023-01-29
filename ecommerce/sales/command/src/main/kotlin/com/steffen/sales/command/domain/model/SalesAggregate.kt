package com.steffen.sales.command.domain.model

import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class SalesAggregate {

    @AggregateIdentifier
    var orderId: String? = null

}