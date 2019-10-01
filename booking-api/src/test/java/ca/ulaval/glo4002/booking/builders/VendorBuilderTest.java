package ca.ulaval.glo4002.booking.builders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainobjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VendorBuilderTest {

    private VendorBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_CODE = "An invalid code";

    @BeforeEach
    public void setUp() {
        subject = new VendorBuilder();
    }

    @Test
    public void buildById_shouldThrowVendorNotFoundException_whenVendorDoesNotExist() {
        VendorNotFoundException thrown = assertThrows(
                VendorNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.Vendor.NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectVendor_whenVendorIsTeam() {
        Vendor vendor = subject.buildById(VendorConstants.TEAM_VENDOR_ID);

        validateVendor(
                vendor,
                VendorConstants.TEAM_VENDOR_ID,
                VendorConstants.TEAM_VENDOR_CODE
        );
    }

    @Test
    public void buildByCode_shouldThrowVendorNotFoundException_whenVendorDoesNotExist() {
        VendorNotFoundException thrown = assertThrows(
                VendorNotFoundException.class,
                () -> subject.buildByCode(AN_INVALID_CODE)
        );

        assertEquals(ExceptionConstants.Vendor.NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByCode_shouldReturnCorrectVendor_whenVendorIsTeam() {
        Vendor vendor = subject.buildByCode(VendorConstants.TEAM_VENDOR_CODE);

        validateVendor(
                vendor,
                VendorConstants.TEAM_VENDOR_ID,
                VendorConstants.TEAM_VENDOR_CODE
        );
    }

    @Test
    public void buildByName_shouldThrowVendorNotFoundException() {
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> subject.buildByName(VendorConstants.TEAM_VENDOR_CODE)
        );

        assertEquals(ExceptionConstants.UNUSED_METHOD_MESSAGE, thrown.getMessage());
    }


    private void validateVendor(Vendor vendor, Long id, String code) {
        assertNotNull(vendor);
        assertEquals(vendor.getId(), id);
        assertEquals(vendor.getCode(), code);
    }
}
