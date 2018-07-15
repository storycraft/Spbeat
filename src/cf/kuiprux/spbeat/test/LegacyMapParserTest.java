package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapParser;
import org.junit.jupiter.api.Test;

import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapLexer;
import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapLexer.Token;

class LegacyMapParserTest {
	
	private static final String TEST_FUMEN = "./test/resources/fumen.txt";

	@Test
	void test() {
		try {
			String rawMap = String.join(System.lineSeparator(), Files.readAllLines(Paths.get(TEST_FUMEN), Charset.forName("windows-932")));

			LegacyMapParser parser = new LegacyMapParser();
			Beatmap result = parser.parseRawMap(rawMap);
			
			System.out.print(result);
		} catch (Exception e) {
			fail(e);
		}
	}

}
