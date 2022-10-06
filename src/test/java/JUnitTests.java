import jpass.crypt.DecryptException;
import jpass.util.ClipboardUtils;
import jpass.util.Configuration;
import jpass.util.DateUtils;
import jpass.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JUnitTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    //1º Function
    @ParameterizedTest
    @MethodSource("StringStripValues")
    public void TestStripString(String expected, String value, Integer lenght) throws IOException {
        System.out.println(StringUtils.stripString(value, lenght));

        assertEquals(expected, StringUtils.stripString(value, lenght));
    }
    private static Stream<Arguments> StringStripValues() {
        return Stream.of(
                arguments("abc...", "abcdefg", 3),
                arguments("abcde...", "abcdefg", 5),
                arguments("abcdefg", "abcdefg", 7)
        );
    }

    //2º Function
    @ParameterizedTest
    @MethodSource("StringClipboard")
    public void TestSetClipboardContent(String value) throws IOException {

        try {
            ClipboardUtils.setClipboardContent(value);
            String cliboardContent = ClipboardUtils.getClipboardContent();
            System.out.println(cliboardContent);
            assertEquals(value, cliboardContent);
            ClipboardUtils.clearClipboardContent();
        }
        catch(Exception e) {
            System.out.println(e);
            //  Block of code to handle errors
        }
    }
    private static Stream<Arguments> StringClipboard() {
        return Stream.of(
                arguments("valueRandomOne"),
                arguments("valueRandomSecond"),
                arguments("valueRandomThird")
        );
    }

    //3º Function


    //4º Function




    //5º Function



    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
