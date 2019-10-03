# Known technical debts

We aren't perfect. Here's what we know.

## Controllers

### Dependency injection

As of right now, services aren't injected properly to controllers. There are created within the controller's contructor. This is know, this is horrid.
