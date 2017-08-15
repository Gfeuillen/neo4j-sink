# Neo4J Sink Connector

This is currently under development

Done it scala for the fun of it.

# Dockers

* [Kafka broker/registry](https://hub.docker.com/r/confluent/kafka/)
* [Neo4J](https://hub.docker.com/_/neo4j/)
* [Kafka connect](https://hub.docker.com/r/confluentinc/cp-kafka-connect/)

# Architecture

**Producer** --> **Kafka Broker** --> **Kafka Connect (Sink)** --> **Neo4J**

All of that using the **Schema Registry**

# Note to me

* [Check this](http://docs.confluent.io/2.0.0/connect/intro.html)