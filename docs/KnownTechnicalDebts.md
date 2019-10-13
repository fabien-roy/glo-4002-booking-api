# Known technical debts

We aren't perfect. Here's what we know.

## Controllers

### Dependency injection

As of right now, services aren't properly injected to controllers. There are created within the controller's contructor. This is know, this is horrid.

## Domain objects

### Inventory

`OxygenTankInventory` and `ShuttleInventory` share a lot in common. They should be merged, somehow. `extends Inventory<OxygenTank>`?

## Parsers

### Oxygen tank inventory parser

In the logic of `toDto`, the parser knows about oxygen categories, which it should not.

## Services

### Order service

If an error occurs during ordering operations (order of passes, oxygen tanks and shuttles), the ordering will not stop. This is a problem.

### Oxygen tank inventory service (error in save/get)

Sadly, OXY has a major problem : ordering a second order via `POST /orders` launches an exception in our persistence manager. It is most likely a problem of OneToMany and ManyToOne entity assignation (in services, see `PassService` and `OrderService`, where this was correctly done). This was not solved in time for MEP 1.0.

The service is encapsulated in a try-catch (eh.), so that `OrderService.order(...)` (ordering orders) does not crash because of `OxygenTankInventoryService`.

### Order tank inventory service (code quality)

`OrderTankInventoryService` was done fast. Fast. `.order(...)` is has more spaghetti than an italian restaurant. It has to be refactored.

## Repositories

### Oxygen tank inventory repository

Weirdly, when saving the lists of in use and not in use oxygen tanks, they are saved as the same. This is a major problem. This is probably due to the fact that `OxygenTanKInventoryEntity.inUseOxygenTanks` and `OxygenTanKInventoryEntity.notInUseOxygenTanks` are both `List<OxygenTankEntity>`. This could be solved using `InUseOxygenTankEntity extends OxygenTankEntity` and `NotInUseOxygenTankEntity extends OxygenTankEntity`.

### Shuttle inventory repository

Same problem that oxygen tank inventory repository, but with `ShuttleInventoryRepositoryEntity.arrivalShuttles` and `ShuttleInventoryRepositoryEntity.departureShuttles`.

## Entities

### Leaked private keys

Currently, `OrderDto.orderNumber` and `PassDto.passNumber` are litteraly `OrderEntity.id` and `PassEntity.id`. Private keys should never be leaked.

## Util

### Removal of "\[UTC]" in date format

When parsing a date to UTC format, unwanted substring "\[UTC]" is added at the end of the date. We remove it using `String.replaceAll(...)` which is not a viable solution.

## Tests

ACP use-case is very well tested. Though, it is not the same for OXY and TRANS. Both use-cases were rushed at the end of MEP 1.0
