package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PlannedModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PlannedModule(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new PlannedModule(invalidModuleCode));
    }

    @Test
    public void isValidPlannedModule() {
        // null module name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid module name
        assertFalse(PlannedModule.isValidModuleName("")); // empty string
        assertFalse(PlannedModule.isValidModuleName(" ")); // spaces only
        assertFalse(PlannedModule.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(PlannedModule.isValidModuleName("CS2103T*")); // contains non-alphanumeric characters

        // valid module name
        assertTrue(PlannedModule.isValidModuleName("CS")); // alphabets only
        assertTrue(PlannedModule.isValidModuleName("2103")); // numbers only
        assertTrue(PlannedModule.isValidModuleName("CS2103T")); // alphanumeric characters
        assertTrue(PlannedModule.isValidModuleName("cs2103T")); // with capital letters
    }
}
