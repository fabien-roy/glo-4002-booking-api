# Known technical debts

**This file must be updated before MEP 3.0.**

We aren't perfect. Here's what we know.

## DEV

DEV is the only use-case we have not finished. The only missing thing is ordering oxygen tanks for activities.

## EventRepository

InMemoryEventRepository should always override the list events. This was not done.

## ArtistClient

ArtistClient is static. This makes some parts of our testing unmockable.

## EventDate

Start and end event dates are instances LocalDate. They should be instanced of EventDate.

## ExceptionMapper

ExceptionMapper should be the one to create ErrorDto and get the correct HttpStatus. Those are currently done by the exceptions.

## Tests

### Missing tests

OXY and CALC were slightly rushed at the end of the project. Because of this, their tests are not perfect.

### Integration tests

Our integration tests are not excluded by maven. They should be.
