import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConexaoApi conexao = new ConexaoApi();
        GeradorDeArquivos gerador = new GeradorDeArquivos(); // Instancia a nossa classe de ficheiros

        String menu = """
                ************************************************
                CONVERSOR DE MOEDAS E CRIPTOMOEDAS
                
                1) Dólar (USD) => Real Brasileiro (BRL)
                2) Real Brasileiro (BRL) => Dólar (USD)
                3) Euro (EUR) => Real Brasileiro (BRL)
                4) Real Brasileiro (BRL) => Euro (EUR)
                5) Bitcoin (BTC) => Real Brasileiro (BRL)  <-- NOVO
                6) Real Brasileiro (BRL) => Bitcoin (BTC)  <-- NOVO
                7) Sair
                
                Escolha uma opção válida:
                ************************************************
                """;

        int opcao = 0;

        while (opcao != 7) {
            System.out.println(menu);
            opcao = leitura.nextInt();

            if (opcao == 7) {
                System.out.println("A finalizar a aplicação...");
                break;
            }

            String base = "";
            String alvo = "";

            switch (opcao) {
                case 1: base = "USD"; alvo = "BRL"; break;
                case 2: base = "BRL"; alvo = "USD"; break;
                case 3: base = "EUR"; alvo = "BRL"; break;
                case 4: base = "BRL"; alvo = "EUR"; break;
                case 5: base = "BTC"; alvo = "BRL"; break;
                case 6: base = "BRL"; alvo = "BTC"; break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            System.out.println("Digite o valor que deseja converter:");
            double valorParaConverter = leitura.nextDouble();

            try {
                double taxa = conexao.obterTaxa(base, alvo);
                double valorFinal = valorParaConverter * taxa;

                // 1. Mostra no ecrã
                // Se for BTC, usamos mais casas decimais (%.8f), se for dinheiro normal, usamos 2 (%.2f)
                String resultadoFormatado;
                if (alvo.equals("BTC")) {
                    resultadoFormatado = String.format("Valor %.2f [%s] = %.8f [%s]", valorParaConverter, base, valorFinal, alvo);
                } else {
                    resultadoFormatado = String.format("Valor %.2f [%s] = %.2f [%s]", valorParaConverter, base, valorFinal, alvo);
                }
                
                System.out.println(resultadoFormatado);

                // 2. Prepara o registo para o histórico com Data e Hora
                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String dataFormatada = agora.format(formatador);

                String log = dataFormatada + " - " + resultadoFormatado;

                // 3. Guarda no ficheiro
                gerador.salvarNoHistorico(log);
                System.out.println("-> Conversão registada no histórico.");

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}