# Known technical debts

## TRANS

### Next available trip

While TRANS was mostly refactored following given comments, one part was left behind to invest time elsewhere. Right now, trip repository is in charge of adding a passenger to a trip for a certain shuttle category (or adding to a new one if all are full / none are there).

This should actually be the job a service. The service would try to add the passenger to a trip. If the shuttle associated to this trip is full, the service would make a new one, add in the passenger and send it to the repository. Otherwise, the service would simply add the passenger to the last avaible trip and update.

## OXY

OXY was the most left behind use-case of the whole refactor. The producer, inventory and service all need to be refactored completly to be clearer and more stable. 

## Configuration

### Dependency injection of service layer

`BookingBinder.java` inject dependencies throughout the app using HK2. Normally, we should only inject dependencies for the infrastructure and rest layers. As of right now, both services and domain layer also get their dependencies injected that way. Those should be injected manually.
