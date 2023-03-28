import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App 
{   
    public static void main(String[] args) throws Exception 
    {
        // Get the data of the json file, using the url
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requisition = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse <String> answer = client.send(requisition, BodyHandlers.ofString());
        String json = answer.body();

        // Extract some of the movies data, such as name, raking, image
        var jsonparser = new parser();
        List<Map<String, String>> movieslist = jsonparser.parse(json); 

        // Print the data and manipulate it
        for (Map<String,String> movie : movieslist)
        {
            String url_image = movie.get("image");
            String title = movie.get("title");
            String name_arq = title.replace(" ", "_").replace(":", "").toLowerCase() + ".png";

            String rating = movie.get("imDbRating");
            float float_rating = Float.parseFloat(rating);

            InputStream input_stream = new URL(url_image).openStream();

            var make = new StickersMakers();
            make.maker(input_stream, name_arq, float_rating);
        }

        
    }
}