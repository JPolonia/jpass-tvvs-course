import jpass.crypt.DecryptException;
import jpass.util.CryptUtils;
import jpass.util.StringUtils;
import jpass.util.ClipboardUtils;
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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            ClipboardUtils.clearClipboardContent(); //colocar num after
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
    @ParameterizedTest
    @MethodSource("StringNonValidXMLCharacters")
    public void TestStripNonValidXMLCharacters(String value) throws IOException {
        System.out.println(StringUtils.stripNonValidXMLCharacters(value));
        assertEquals("?", StringUtils.stripNonValidXMLCharacters(value));
    }
    private static Stream<Arguments> StringNonValidXMLCharacters() {
        return Stream.of(
                arguments("\0"),
                arguments("noErrorString")
        );
    }

    //4º Function
    @ParameterizedTest
    @MethodSource("StringToHashValues")
    public void TestGetSha256Hash(String value, String expected) throws Exception {
        if (value == null) {
            NullPointerException exception = assertThrows(NullPointerException.class, ()->{
                char[] bla = value.toCharArray();
            });
            assertEquals("Cannot invoke \"String.toCharArray()\" because \"value\" is null", exception.getMessage());

        } else {
            byte[] result = CryptUtils.getSha256Hash(value.toCharArray());
            String sha256hex = byteArrayToHex(result);
            assertEquals(expected, sha256hex);
        }
    }
    private static Stream<Arguments> StringToHashValues() {
        return Stream.of(
                arguments(null, ""),
                arguments("", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
                arguments("abcdefg", "7d1a54127b222502f5b79b5fb0803061152a44f92b37e23c6527baf665d4da9a")
        );
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }



    //5º Function



    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
