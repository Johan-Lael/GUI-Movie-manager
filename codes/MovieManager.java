package Project;

public class MovieManager {
    private Movie[] movies;
    private int movieCount;

    public MovieManager() {
        this.movies = new Movie[100]; // Initial capacity
        this.movieCount = 0;
    }
    
    public MovieManager(int size) {
    	if(size>0) {
	        this.movies = new Movie[size]; // Initial capacity
	        this.movieCount = 0;
    	}else {
            this.movies = new Movie[100]; // Initial capacity
            this.movieCount = 0;
    	}
    }

    public void addMovie(Movie movie) {
        if (movieCount < movies.length) {
            movies[movieCount] = movie;
            movieCount++;
        }
    }

    public void removeMovie(String title) {
        for (int i = 0; i < movieCount; i++) {
            if (movies[i].getTitle().equals(title)) {
                movies[i] = movies[movieCount - 1];
                movies[movieCount - 1] = null;
                movieCount--;
                break;
            }
        }
    }

    public Movie searchMovie(String title) {
        for (int i = 0; i < movieCount; i++) {
            if (movies[i].getTitle().equals(title)) {
                return movies[i];
            }
        }
        return null;
    }

    public Movie[] filterMoviesByGenre(Genre genre) {
        Movie[] filteredMovies = new Movie[movieCount];
        int count = 0;
        for (int i = 0; i < movieCount; i++) {
            if (movies[i].getGenre() == genre) {
                filteredMovies[count] = movies[i];
                count++;
            }
        }
        printMovies(trimArray(filteredMovies, count));
        return trimArray(filteredMovies, count);
    }

    public Movie[] sortMoviesByName() {
        Movie[] sortedMovies = new Movie[movieCount];
        System.arraycopy(movies, 0, sortedMovies, 0, movieCount);
        for (int i = 0; i < sortedMovies.length - 1; i++) {
            for (int j = i + 1; j < sortedMovies.length; j++) {
                if (sortedMovies[i].getTitle().compareTo(sortedMovies[j].getTitle()) > 0) {
                    Movie temp = sortedMovies[i];
                    sortedMovies[i] = sortedMovies[j];
                    sortedMovies[j] = temp;
                }
            }
        }
        return sortedMovies;
    }

    private Movie[] trimArray(Movie[] array, int newSize) {
        Movie[] trimmedArray = new Movie[newSize];
        System.arraycopy(array, 0, trimmedArray, 0, newSize);
        return trimmedArray;
    }

    public Movie[] getMovies() {
        return trimArray(movies, movieCount);
    }

    public void printMovies(Movie[] moviesToPrint) {
        for (Movie movie : moviesToPrint) {
            if (movie != null) {
                System.out.println(movie.toString()+"\n");
            }
        }
    }
}