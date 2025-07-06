package Project;

public class Movie {
	public String title;
	public String studio;
	public int duration;
	public int likes;
	private Genre genre;
	private Rating rating;
	
	
	private static int totalMovies = 0;
	
	//Default Constructor
	public Movie() 
	{
		this.title = "UNKNOWN";
		this.studio = "UNKNOWN";
		this.duration = 90;
		this.likes = 0;
		this.rating = Rating.UNKNOWN;
		this.genre = Genre.UNKNOWN;
		totalMovies++; // Increment the static counter when a new Movie is created
    }
		
	
	//Parameterized constructor
	public Movie(String title, String studio, int duration, int likes, Rating rating, Genre genre) 
	{
		this.setTitle(title);
		this.setStudio(studio);
		this.setDuration(duration);
		this.setLikes(likes);
		this.rating = rating;
		this.genre = genre;
		totalMovies++;
	}
	
	//Accessor Methods (getters) - Retrieve values of instance variables
	public String getTitle() {
		return this.title;
	}
	public String getStudio() {
		return this.studio;
	}
	public int getDuration() {
		return this.duration;
	}
	public int getLikes()
	{
		return this.likes;
	}
	public Rating getRating() {
		return this.rating;
	}
	public Genre getGenre() {
		return this.genre;
	}
	
	
	//Mutator Methods(Setters) - Validate and assigned values to instance variables
	public void setTitle(String title) 
	{ //Ensure title has at least 3 characters
		if(title != null && title.length() >= 3)
		{
			this.title = title;
		}
		else
		{
			this.title = "UNKNOWN";
		}
	}
	public void setStudio(String studio)
	{
		if(studio != null)
		{
			this.studio = studio;
		}
		else
		{
			this.studio = "UNKOWN";
		}
	}
	public void setDuration(int duration)
	{
		if(duration >= 30 && duration <= 300)
		{
			this.duration = duration;
		}
		else
		{
			this.duration = 90;
		}
	}
	public void setLikes(int likes)
	{
		if(likes >= 0)
		{
			this.likes = likes;
		}
		else
		{
			this.likes = 0;
		}
	}
	public void setRating(Rating rating)
	{
		if(rating != null)
		{
			this.rating = rating;
		}
		else
		{
			this.rating = Rating.UNKNOWN;
		}
	}
	public void setGenre(Genre genre)
	{
		if(genre != null)
		{
			this.genre = genre;
		}
		else
		{
			this.genre = Genre.UNKNOWN;
		}
	}
	
	//Static Method: Creates and returns a new Movie instance with given values
	public static Movie createNewMovie(String title, String director, int duration, int likes, Rating rating, Genre genre) 
	{
		Movie newMovie = new Movie();
		newMovie.setTitle(title);
		newMovie.setStudio(director);
		newMovie.setDuration(duration);
		newMovie.setLikes(likes);
		newMovie.setRating(rating);
		newMovie.setGenre(genre);
		return newMovie;
	}
	
	//Static Method: Converts minutes to hours and minutes format
	public static String minuteConvert(int minutes)
	{
		int hours = minutes / 60;
		int remainderMinutes = minutes % 60;
		return hours + "h " + remainderMinutes + "m";
	}
	
	
	public String toString()
	{
		return "Title: " +this.title + ", Studio: " + this.studio + ", Duration: " + this.duration + ", Rating: " + this.rating + ", Genre: " + this.genre; 
	}
	
	//equals() method
	public boolean equals(Movie otherMovie)
	{
		return otherMovie != null && 
				this.title.equals(otherMovie.getTitle()) &&
				this.studio.equals(otherMovie.getStudio()) &&
				this.duration == otherMovie.getDuration() &&
				this.likes == otherMovie.getLikes() &&
				this.rating == otherMovie.getRating() &&
				this.genre == otherMovie.getGenre();
				
	}
	
	
}
