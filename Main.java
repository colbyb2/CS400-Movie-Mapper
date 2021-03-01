import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// --== CS400 File Header Information ==--
// Name: Colby Brown
// Email: csbrown7@wisc.edu 
// Team: CC Red
// Role: Frontend Developer
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader:
public class Main {

        private static String mode;
        private static Backend backend;
        private static Scanner scanner;

        public static void main(String [] args) {
                backend = new Backend(new String[0]);
                mode = "base";
                scanner = new Scanner(System.in);
                boolean running = true;
                while (running) {
                        if (mode == "base") {
                          baseMode();
                        } else if (mode == "genre") {
                          genreMode();
                        } else if (mode == "rating") {
                          ratingMode();
                        } else if (mode == "end") {
                          System.out.println("Goodbye.");
                          running = false;
                        }
                }
        }
        
        private static String getMovieInfo(MovieInterface movie) {
          String title = movie.getTitle();
          String description = movie.getDescription();
          String director = movie.getDirector();
          String year = "" + movie.getYear();
          String rating = "" + movie.getAvgVote();
          
          return (title + "\n" + description + "\n" + director + " | " + year + " | " + rating);
        }

        private static void baseMode() {
                while (mode == "base") {
                  System.out.println("Hello! Welcome to The Movie Mapper! Enter 'x' to exit the program, 'g' to "
                      + "enter genre mode, or 'r' to enter rating mode!");
                  
                  System.out.println("\nTop 3 Movies:");
                  
                  List<MovieInterface> movies = backend.getThreeMovies(0);
                  int index = 1;
                  for (MovieInterface movie : movies) {
                    System.out.println(index + ") " + getMovieInfo(movie));
                    index++;
                  }
                  
                  System.out.println("\nEnter command: ");
                  String command = scanner.nextLine();
                  if (command.equals("g"))
                    mode = "genre";
                  if (command.equals("r"))
                    mode = "rating";
                  if (command.equals("x"))
                    mode = "end";
                }
        }

        private static  void genreMode() {
          
                while (mode == "genre") {
                  System.out.println("Welcome to genre mode. Select the number next to a genre below to select or "
                      + "deselect that genre in the filter.");
                  
                  List<String> genres = backend.getAllGenres();
                  for (int i = 0; i < genres.size(); i++) {
                    String selected = "";
                    if (backend.getGenres().contains(genres.get(i)))
                      selected = "(Selected)";
                    System.out.println(i + ") " + genres.get(i) + " " + selected);
                  }
                  
                  System.out.println("\nMovies:");
                  List<MovieInterface> movies = backend.getThreeMovies(0);
                  int index = 1;
                  for (MovieInterface movie : movies) {
                    System.out.println(index + ") " + getMovieInfo(movie));
                    index++;
                  }
                  
                  
                  System.out.println("\nEnter 'x' to return to base mode.");
                  
                  System.out.println("\nEnter command: ");
                  String command = scanner.nextLine();
                  if (command.equals("x"))
                    mode = "base";
                  for (int i = 0; i < genres.size(); i++) {
                    if (command.equals(""+i)) {
                      if (backend.getGenres().contains(genres.get(i))) {
                        backend.removeGenre(genres.get(i));
                      } else {
                        backend.addGenre(genres.get(i));
                      }
                    }
                  }
                }
        }

        private static void ratingMode() {
          
          for (int i = 1; i < 11; i++) {
            backend.addAvgRating(""+i);
          }
          
                while (mode == "rating") {
                  System.out.println("Welcome to rating mode. Enter a number from below to filter by rating. Entering the "
                      + "number will either select or deselect that rating.");
                  
                  for (int i = 1; i < 11; i++) {
                    String selected = "";
                    if (backend.getAvgRatings().contains(""+i))
                      selected = "(Selected)";
                    System.out.println(i + " " + selected);
                  }
                  
                  System.out.println("\nMovies:");
                  List<MovieInterface> movies = backend.getThreeMovies(0);
                  int index = 1;
                  for (MovieInterface movie : movies) {
                    System.out.println(index + ") " + getMovieInfo(movie));
                    index++;
                  }
                  
                  
                  System.out.println("\nEnter 'x' to return to base mode.");
                  
                  System.out.println("\nEnter command: ");
                  String command = scanner.nextLine();
                  if (command.equals("x"))
                    mode = "base";
                  for (int i = 1; i < 11; i++) {
                    if (command.equals(""+i)) {
                      if (backend.getAvgRatings().contains(""+i)) {
                        backend.removeAvgRating(""+i);;
                      } else {
                        backend.addAvgRating(""+i);
                      }
                    }
                  }
                }
        }
}
