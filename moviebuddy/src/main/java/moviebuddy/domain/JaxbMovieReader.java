package moviebuddy.domain;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import moviebuddy.ApplicationException;

public class JaxbMovieReader implements MovieReader{

	@Override
	public List<Movie> loadMovies() {
		
		//JAXBContext 객체 생성(moviemetadata.class를 매핑해야하니까 넘겨주면 된다.)
		try {
			final JAXBContext jaxb = JAXBContext.newInstance(Moviemetadata.class);
			final Unmarshaller unmarshaller =  jaxb.createUnmarshaller();
			
			final InputStream content =  ClassLoader.getSystemResourceAsStream("movie_metadata.xml");
			final Source source = new StreamSource(content);			
			final Moviemetadata metadata = (Moviemetadata) unmarshaller.unmarshal(source);
		
			return metadata.toMovie();
		
		} catch (JAXBException error) {
			throw new ApplicationException("failed to load movies data", error);
		}
	}

	//@XmlRootElement=> MovieMetaData에게 루트 엘리먼트가 어떤건지 알려주는 것
	@XmlRootElement(name = "moviemetadata")
	public static class Moviemetadata {
		
		private List<MovieData> movies;

		public List<MovieData> getMovies() {
			return movies;
		}

		public void setMovies(List<MovieData> movies) {
			this.movies = movies;
		}
		
		public List<Movie> toMovie(){
			return movies.stream().map(MovieData::toMovie).collect(Collectors.toList());
		}
		
		
	}
	
	public static class MovieData {
		
		private String title;
		private List<String>genres;
		private String language;
		private String country;
		private int releaseYear;
		private String director;
		private List<String> actors;
		private URL imdblink;
		private String watchedDate;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<String> getGenres() {
			return genres;
		}
		public void setGenres(List<String> genres) {
			this.genres = genres;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public int getReleaseYear() {
			return releaseYear;
		}
		public void setReleaseYear(int releaseYear) {
			this.releaseYear = releaseYear;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public List<String> getActors() {
			return actors;
		}
		public void setActors(List<String> actors) {
			this.actors = actors;
		}
		public URL getImdblink() {
			return imdblink;
		}
		public void setImdblink(URL imdblink) {
			this.imdblink = imdblink;
		}
		public String getWatchedDate() {
			return watchedDate;
		}
		public void setWatchedDate(String watchedDate) {
			this.watchedDate = watchedDate;
		}
		
		public Movie toMovie() {
			return Movie.of(title, genres, language, country, releaseYear, director, actors, imdblink, watchedDate);
		}
	}
}
