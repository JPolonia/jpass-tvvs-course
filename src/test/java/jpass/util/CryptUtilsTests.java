package jpass.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CryptUtilsTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }


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

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
