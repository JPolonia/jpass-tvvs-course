package jpass.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SringUtilTests {

    //Blackbox testing - boundary value analysis
    //1º Function
    @ParameterizedTest
    @MethodSource("TestStripStringBoundary")
    public void TestStripStringBoundary(String text, Integer length, String result, Integer expectedLenght) throws IOException {
        System.out.println(StringUtils.stripString(text, length).length());
        System.out.println(expectedLenght);
        System.out.println(result.contains("..."));

        System.out.println(StringUtils.stripString(text, length));

        assertEquals(StringUtils.stripString(text, length).length(), expectedLenght);
    }
    private static Stream<Arguments> TestStripStringBoundary() {
        return Stream.of(
                arguments("abcdefg", 3, "abc...", "abc...".length()), // 7 3 6
                arguments("abcdefg", 5, "abcde...", "abcde...".length()),//7 5 8
                arguments("abcdefg", 7, "abcdefg", "abcdefg".length()), // 7 7 7
                arguments("abcdefg", 0, "...", "...".length()) // 0 7 3
        );
    }


}
