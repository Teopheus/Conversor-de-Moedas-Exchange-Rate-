import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoApi {
    
    // Substitua pela sua chave real!
    private final String API_KEY = "COLOQUE-SUA-CHAVE-AQUI"; 

    public double obterTaxa(String moedaBase, String moedaAlvo) {
        // Monta a URL: ex: .../pair/USD/BRL
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + moedaBase + "/" + moedaAlvo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Converte o JSON para nosso Record e retorna apenas o valor da taxa
            Moeda moeda = new Gson().fromJson(response.body(), Moeda.class);
            return moeda.conversion_rate();

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível obter a cotação.");
        }
    }
}