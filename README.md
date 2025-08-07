This project repsesents an excersise to demonstrate fanout pattern for elicite information from databases with the same schema distributed in different locations. 

Exposed method whcih can be called from Postman is high-value-german, it is supposed to return orders made from Germany that exceeds EUR 900. 

This project depends on few environment variables:
SPRING_PROFILES_ACTIVE=postgres - If set it will activate application-postgres.yml configuration
SHARD_DB_PASSWORD=*****; - Password which will be used by the shard user
SHARD_DB_USERNAME=*****; - Shard database use. 

For now, the 3 database shards are on the same database, but this will evolve int he future. 
