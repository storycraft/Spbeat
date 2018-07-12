package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapLexer;
import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapLexer.Token;

class LegacyMapParserTest {
	
	private static final String TEST_FUMEN = "./test/resources/fumen.txt";

	@Test
	void test() {
		try {
			String rawMap = String.join(System.lineSeparator(), Files.readAllLines(Paths.get(TEST_FUMEN), Charset.forName("windows-932")));
			
			LegacyMapLexer lexer = new LegacyMapLexer();
			List<List<Token>> result = lexer.separateRawMap(rawMap);
			
			for (List<Token> lineList : result) {
				for (Token token : lineList) {
					System.out.print(token + ", ");
				}
				System.out.print(System.lineSeparator());
			}
		} catch (IOException e) {
			fail(e);
		}
	}

}
