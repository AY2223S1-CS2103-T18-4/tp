package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentModule(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new CurrentModule(invalidModuleCode));
    }

    @Test
    public void isValidCurrentModule() {
        // null module name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid module name
        assertFalse(CurrentModule.isValidModuleName("")); // empty string
        assertFalse(CurrentModule.isValidModuleName(" ")); // spaces only
        assertFalse(CurrentModule.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(CurrentModule.isValidModuleName("CS2103T*")); // contains non-alphanumeric characters

        // valid module name
        assertTrue(CurrentModule.isValidModuleName("CS")); // alphabets only
        assertTrue(CurrentModule.isValidModuleName("2103")); // numbers only
        assertTrue(CurrentModule.isValidModuleName("CS2103T")); // alphanumeric characters
        assertTrue(CurrentModule.isValidModuleName("cs2103T")); // with capital letters
    }

}
