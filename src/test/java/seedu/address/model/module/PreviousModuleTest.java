package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

public class PreviousModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PreviousModule(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new PreviousModule(invalidModuleCode));
    }

    @Test
    public void isValidCurrentModule() {
        // null module name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid module name
        assertFalse(PreviousModule.isValidModuleName("")); // empty string
        assertFalse(PreviousModule.isValidModuleName(" ")); // spaces only
        assertFalse(PreviousModule.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(PreviousModule.isValidModuleName("CS2103T*")); // contains non-alphanumeric characters

        // valid module name
        assertTrue(PreviousModule.isValidModuleName("CS")); // alphabets only
        assertTrue(PreviousModule.isValidModuleName("2103")); // numbers only
        assertTrue(PreviousModule.isValidModuleName("CS2103T")); // alphanumeric characters
        assertTrue(PreviousModule.isValidModuleName("cs2103T")); // with capital letters
    }
}
