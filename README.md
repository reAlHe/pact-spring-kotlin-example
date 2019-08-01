# Pact-JVM-Spring-Example

An example repository consisting of 2 Microservices whose interfaces are tested with the PACT framework.

## Architecture

There are 2 microservices interacting with each other. The `pact-consumer-demo` service offers following REST endpoints:

- `/products/{id}` : retrieves information about a product, identified by its id.

The `pact-provider-demo` offers the following REST-endpoint:

- `/productDetails/{id}` : delivers the product details for the product identified by its id

When the `/products/{id}` endpoint is called `pact-consumer-demo` fetches the product details from the `pact-provider-demo` by calling
the `/productDetails/{id}` endpoint.

## infrastructure

A `docker-compose.yml` is provided containing the required infrastructure services, i.e. a pact-broker, that manages
the contracts and a mongo db acting as storage for the 2 microservices.

## pacts
After running teh consumer tests, a pact is generated, located under root with path `/target/pacts`. This pact can
be posted at the pact broker:

`POST http://{pact broker host}/pacts/provider/{provider name}/consumer/{consumer name}/version/{version}`

Please note that the consumer and provider names, provided in the path must match exactly the names within the posted pact json.

