# Known technical debts

We aren't perfect. Here's what we know.

## Controllers

### Dependency injection

As of right now, services aren't injected properly to controllers. There are created within the controller's contructor. This is know, this is horrid.

## Util

### Removal of "\[UTC]" in format

When parsing a date to UTC format, unwanted substring "\[UTC]" is added at the end of the date. We remove it using `String.replaceAll(...)` which is not a viable solution.

## Parsers

### Oxygen tank inventory parser

In the logic of `toDto`, the parser knows about oxygen categories, which it should not.

## Services

### Order service

If an error occurs during ordering operations (order of passes, oxygen tanks and shuttles), the ordering will not stop. This is a problem.

### Oxygen tank inventory service

Sadly, OXY has a major proble : ordering a second order via `POST /orders` launched an exception in our persistence manager. This was not solved in time for MEP 1.0.

The service is encapsulated in a try-catch, so that order service (ordering orders) does not crash because of oxygen tank inventory service.

## Repositories

### Oxygen tank inventory repository

Weirdly, when saving the lists of in use and not in use oxygen tanks, they are saved as the same. This is a major problem.

### Shuttle inventory repository

Same problem that oxygen tank inventory repository, but with arrival and departure shuttles lists.

## Tests

ACP use-case is very well tested. Though, it is not the same for OXY and TRANS. Both use-cases were rushed at the end of MEP 1.0
