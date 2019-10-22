package ca.ulaval.glo4002.booking.domain.orders;

class OrderTest {

    // TODO : Make new tests, since most of this logic has to be moved

    /*
    @Test
    void getPrice_shouldReturnPrice() {

    }

    // TODO : missing package option in order
    @Test
    void givenAnOrderForASuperNovaPackage_whenPriceIsRequested_ShouldReturn700000() {

    }

    // TODO : missing package option in order
    @Test
    void givenAnOrderForASuperGiantPackage_whenPriceIsRequested_shouldReturn500000() {

    }

    // TODO : missing package option in order
    @Test
    void givenAnOrderForNebulaPackage_whenPriceIsRequested_shouldReturn250000() {

    }

    @Test
    void givenAnOrderForSupernovaOrder_whenPriceIsRequested_shouldReturnNumberOfPassesMultiplyBy150000 () {
        Pass pass = new Pass(LocalDate.now(), new Money(new BigDecimal(150000)));
        List<Pass> listOfPasses = Arrays.asList(pass, pass);
        Order order = new Order(0L, listOfPasses);
        Money expected = new Money(new BigDecimal(150000 * listOfPasses.size()));

        assertTrue(order.getPrice().compareTo(expected) == 0);
    }

    @Test
    void givenAnOrderForASingleSuperGiantSinglePass_whenPriceIsRequested_shouldReturn100000() {
        Pass pass = new Pass(LocalDate.now(), new Money(new BigDecimal(100000)));
        List<Pass> listOfPasses = Arrays.asList(pass);
        Order order = new Order(1L, listOfPasses);
        Money expected = new Money(new BigDecimal(100000));

        assertTrue(order.getPrice().compareTo(expected) == 0);
    }

    @Test
    void givenAnOrderForASingleNebulaSinglePass_whenPriceIsRequested_shouldReturn50000() {
        Pass pass = new Pass(LocalDate.now(), new Money(new BigDecimal(50000)));
        List<Pass> listOfPasses = Arrays.asList(pass);
        Order order = new Order(2L, listOfPasses);
        Money expected = new Money(new BigDecimal(50000));

        assertTrue(order.getPrice().compareTo(expected) == 0);
    }

    @Test
    void givenAnOrderFiveOrMoreSinglePassSupergiant_whenPriceIsRequested_shouldReturnNumberOfPassesMultiplyBy90000() {
        Pass pass = new Pass(LocalDate.now(), new Money(new BigDecimal(100000)));
        List<Pass> listOfPasses = Arrays.asList(pass, pass, pass, pass, pass);
        Order order = new Order(1L, listOfPasses);
        Money expected = new Money(new BigDecimal(90000 * listOfPasses.size()));

        assertTrue(order.getPrice().compareTo(expected) == 0);
    }

    @Test
    void givenAnOrderForMoreThanThreeSinglePassNebula_whenPriceIsRequested_shouldApplyATenPercentDiscount() {
        Money expected = new Money(new BigDecimal(180000)); // TODO : Right way ??????
        Pass pass = new Pass(LocalDate.now(), new Money(new BigDecimal(50000)));
        List<Pass> ListofPasses = Arrays.asList(pass, pass, pass, pass);
        Order order = new Order(2L, ListofPasses);

        assertTrue(order.getPrice().compareTo(expected) == 0);
    }
    */
}