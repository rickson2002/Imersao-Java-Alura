import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // conexão HTTP e buscar top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_43s3ynuu";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // Extrair dados interessante: Titulo, poster e classificação
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);



        //manipular e exbir dados

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[37m \u001b[44mTitulo:\u001b[m " + filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println();
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrela = (int) classificacao;

            for (int n = 1; n <= numeroEstrela; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");

            
        }



    }

}
