// O record facilita pegar apenas o campo que nos interessa do JSON
public record Moeda(String base_code, String target_code, double conversion_rate) {
}