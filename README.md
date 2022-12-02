# axon-ecommerce
Demo eCommerce platfrom using Axon Framework



## Modules

### inventory-app
Handles items and stock.

Modules:
- assembly: contains main function to start.
- command: contains aggregates and command handlers
- coreapi: contains all command, events, queries
- query: contains a rest api and query handler

Controller:
localhost:8000/swagger-ui/index.html

### sales-app
Queries for Available products and stock to create orders. 



- shipment-app
- payment-app