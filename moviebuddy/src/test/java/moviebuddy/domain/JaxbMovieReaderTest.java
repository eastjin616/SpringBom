package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JaxbMovieReaderTest {

//	public static void main(String[] args) {
	
	@Test
	void NotEmpty_LoadedMovies() {
		
		JaxbMovieReader moiveReader = new JaxbMovieReader();

		List<Movie> movies = moiveReader.loadMovies();
		Assertions.assertEquals(1375, movies.size());
		
		//movies.size() 가 XML문서에 등록된 영화의 수와 동일한가? 를 확인
//		MovieFinderTest.assertEquals(1375, movies.size());
	}
	

}
