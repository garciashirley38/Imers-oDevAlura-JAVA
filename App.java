import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            // int starNumber = (int) classificacao;
            if (classificacao > 6) {
                System.out.println("\u001b[1m \u001b[30m \u001b[42m Titulo:\u001b[m " + filme.get("title"));
                System.out.println("\u001b[1m \u001b[30m \u001b[42m URL da Imagem:\u001b[m " + filme.get("image"));
            } else {
                System.out.println("\u001b[1m \u001b[37m \u001b[41m Titulo:\u001b[m " + filme.get("title"));
                System.out.println("\u001b[1m \u001b[37m \u001b[41m URL da Imagem:\u001b[m " + filme.get("image"));
            }

            // for (int n = 1; n <= starNumber; n++) {
            // if (starNumber > 6) {
            // System.out.print("\u2B50");
            // } else {
            // System.out.print("\u1F345");
            // }
            // }

            System.out.println("\n");
        }
    }
}