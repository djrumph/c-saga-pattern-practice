# c-saga-pattern-practice



Experiment running the choreography saga pattern with rabbit mq

This uses a single que to preserve ordering and persists the messages to disk. 

Rabbitmq will use the que in memory and then persist everything to disk

Could horizontally scale by sharding what que gets written to using a hash algorithm and sharding the messages that can be sent to which que

Rabbit mq streams look interesting, could be a better way to do this. 

Seems like upgrading rabbitmq can be troublesome, may want to look into it. 


Since this is async it will need the first db to have a column for the status of the oder
This column will be updated when the last step in the async processes are finished. 

Need to try this out with an api and see if everything works with the queues that way

Also not sure how I feel about the switch statement handling the messages. Might be a better way. 

