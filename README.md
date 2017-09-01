# Neo4J Sink Connector

Multi-purpose connector from [Kafka](https://kafka.apache.org/) to [Neo4J](https://neo4j.com/)

**This connector is still under development**

Done it scala for the fun of it.

# Development and testing

In order to have a full devopment environment : [Docker](https://www.docker.com/).
In the [docker](https://github.com/Gfeuillen/neo4j-sink/tree/master/docker) directory you will find the necessary components to have a Kafka + Neo4J installation.
Simply enter the directoy and type `docker-compose up`

## Dockers

Here is the list of dockers used to have a running architecture.

* [Zookeeper](https://hub.docker.com/r/wurstmeister/zookeeper/)
* [Kafka broker](https://github.com/wurstmeister/kafka-docker)
* [Kafka schema registry](https://hub.docker.com/r/confluent/schema-registry/~/dockerfile/)
* [Kafka connect](https://hub.docker.com/r/confluentinc/cp-kafka-connect/)
* [Neo4J](https://hub.docker.com/_/neo4j/)

# [Producer](https://github.com/Gfeuillen/neo4j-sink/tree/master/event-producer)

A Kafka producer was developped for the sole purpose of having messages to consume.

# [Schemas](https://github.com/Gfeuillen/neo4j-sink/tree/master/avro-schemas)

Where common schemas and methods are stored.

# [Connector](https://github.com/Gfeuillen/neo4j-sink/tree/master/sink)

The sink.


# Note to me

* [Check this](http://docs.confluent.io/2.0.0/connect/intro.html)