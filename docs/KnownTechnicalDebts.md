# Known technical debts

We aren't perfect. Here's what we know.

## Controllers

### Dependency injection

As of right now, services aren't injected properly to controllers. There are created within the controller's contructor. This is know, this is horrid.

## Util

### Removal of "\[UTC]" in format

When parsing a date to UTC format, unwanted substring "\[UTC]" is added at the end of the date. We remove it using `String.replaceAll(...)` which is not a viable solution.

## Repository

### OxygenTankRepository

We store each oxygentTank as an entry instead of batching them in orders, with columns of requestDate, readyDate, quantity and category. We also give an id to each of them for no reason.
