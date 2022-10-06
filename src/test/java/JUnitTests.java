import jpass.crypt.DecryptException;
import jpass.util.Configuration;
import jpass.util.DateUtils;
import jpass.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

}
