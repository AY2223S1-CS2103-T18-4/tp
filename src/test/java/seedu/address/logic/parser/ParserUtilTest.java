package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MODULE = "CS2103T*";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_MODULE_1 = "CS2103T";
    private static final String VALID_MODULE_2 = "CS2101";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseCurrentModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCurrentModule(null));
    }

    @Test
    public void parseCurrentModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCurrentModule(INVALID_MODULE));
    }

    @Test
    public void parseCurrentModule_validValueWithoutWhitespace_returnsCurrentModule() throws Exception {
        CurrentModule expectedPreviousModule = new CurrentModule(VALID_MODULE_1);
        assertEquals(expectedPreviousModule, ParserUtil.parsePreviousModule(VALID_MODULE_1));
    }

    @Test
    public void parseCurrentModule_validValueWithWhitespace_returnsTrimmedCurrentModule() throws Exception {
        String currentModuleWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        CurrentModule expectedCurrentModule = new CurrentModule(VALID_MODULE_1);
        assertEquals(expectedCurrentModule, ParserUtil.parseCurrentModule(currentModuleWithWhitespace));
    }

    @Test
    public void parseCurrentModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCurrentModules(null));
    }

    @Test
    public void parseCurrentModules_collectionWithInvalidCurrentModules_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCurrentModules(Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parseCurrentModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCurrentModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCurrentModules_collectionWithValidCurrentModules_returnsCurrentModulesSet() throws Exception {
        Set<CurrentModule> actualCurrentModuleSet = ParserUtil.parseCurrentModules(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<CurrentModule> expectedCurrentModuleSet = new HashSet<CurrentModule>(Arrays.asList(new CurrentModule(VALID_MODULE_1), new CurrentModule(VALID_MODULE_2)));

        assertEquals(expectedCurrentModuleSet, actualCurrentModuleSet);
    }

    @Test
    public void parsePreviousModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreviousModule(null));
    }

    @Test
    public void parsePreviousModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreviousModule(INVALID_MODULE));
    }

    @Test
    public void parsePreviousModule_validValueWithoutWhitespace_returnsPreviousModule() throws Exception {
        PreviousModule expectedPreviousModule = new PreviousModule(VALID_MODULE_1);
        assertEquals(expectedPreviousModule, ParserUtil.parsePreviousModule(VALID_MODULE_1));
    }

    @Test
    public void parsePreviousModule_validValueWithWhitespace_returnsTrimmedPreviousModule() throws Exception {
        String currentModuleWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        PreviousModule expectedCurrentModule = new PreviousModule(VALID_MODULE_1);
        assertEquals(expectedCurrentModule, ParserUtil.parsePreviousModule(currentModuleWithWhitespace));
    }

    @Test
    public void parsePreviousModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreviousModules(null));
    }

    @Test
    public void parsePreviousModules_collectionWithInvalidPreviousModules_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreviousModules(Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parsePreviousModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePreviousModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePreviousModules_collectionWithValidPreviousModules_returnsPreviousModulesSet() throws Exception {
        Set<PreviousModule> actualPreviousModuleSet = ParserUtil.parsePreviousModules(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<PreviousModule> expectedPreviousModuleSet = new HashSet<PreviousModule>(Arrays.asList(new PreviousModule(VALID_MODULE_1), new PreviousModule(VALID_MODULE_2)));

        assertEquals(expectedPreviousModuleSet, actualPreviousModuleSet);
    }

    @Test
    public void parsePlannedModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePlannedModule(null));
    }

    @Test
    public void parsePlannedModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePlannedModule(INVALID_MODULE));
    }

    @Test
    public void parsePlannedModule_validValueWithoutWhitespace_returnsPlannedModule() throws Exception {
        PlannedModule expectedPlannedModule = new PlannedModule(VALID_MODULE_1);
        assertEquals(expectedPlannedModule, ParserUtil.parsePlannedModule(VALID_MODULE_1));
    }

    @Test
    public void parsePlannedModule_validValueWithWhitespace_returnsTrimmedPlannedModule() throws Exception {
        String plannedModuleWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        PlannedModule expectedPlannedModule = new PlannedModule(VALID_MODULE_1);
        assertEquals(expectedPlannedModule, ParserUtil.parsePlannedModule(plannedModuleWithWhitespace));
    }

    @Test
    public void parsePlannedModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePlannedModules(null));
    }

    @Test
    public void parsePlannedModules_collectionWithInvalidCurrentModules_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCurrentModules(Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parsePlannedModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePlannedModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePlannedModules_collectionWithValidPlannedModules_returnsPlannedModulesSet() throws Exception {
        Set<PlannedModule> actualPlannedModuleSet = ParserUtil.parsePlannedModules(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<PlannedModule> expectedPlannedModuleSet = new HashSet<PlannedModule>(Arrays.asList(new PlannedModule(VALID_MODULE_1), new PlannedModule(VALID_MODULE_2)));

        assertEquals(expectedPlannedModuleSet, actualPlannedModuleSet);
    }

}
