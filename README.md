This project repsesents an excersise to demonstrate fanout pattern for elicite information from databases with the same schema distributed in different locations. 

Exposed method whcih can be called from Postman is high-value-german, it is supposed to return orders made from Germany that exceeds EUR 900. 

This project depends on few environment variables:
SPRING_PROFILES_ACTIVE=postgres - If set it will activate application-postgres.yml configuration
SHARD_DB_PASSWORD=*****; - Password which will be used by the shard user******
SHARD_DB_USERNAME=shard_user; - Shard database user.
The shard hosts were parametrised in the yml file as follows:
SHARD_ALPHA_HOST: shard_alpha
SHARD_BETA_HOST: shard_beta
SHARD_GAMMA_HOST: shard_gamma

The 3 databases are configured to be on the same for local, but are designed to be on
3 different images when run on docker. 