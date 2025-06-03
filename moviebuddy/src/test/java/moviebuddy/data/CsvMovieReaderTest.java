package moviebuddy.data;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

public class CsvMovieReaderTest {
	
	@Test
	//정상적인 메타데이터 일 시
	void Valid_Metadata() throws Exception {
		CsvMovieReader movieReader = new CsvMovieReader();
		movieReader.setMetadata("movie_metadata.csv");
		movieReader.setResourceLoader(new DefaultResourceLoader());
		
		movieReader.afterPropertiesSet();
	}
	
	@Test
	//정상적이지 않은 메타데이터 일 시
	void Invalid_Metadata() {
		CsvMovieReader movieReader = new CsvMovieReader();
		movieReader.setResourceLoader(new DefaultResourceLoader());
		
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			movieReader.setMetadata("invalid");
			movieReader.afterPropertiesSet();
		});
	}

}
