import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivos {

    public void salvarNoHistorico(String mensagem) {
        try {
            // O parâmetro 'true' indica que queremos adicionar (append) ao ficheiro existente
            FileWriter escrita = new FileWriter("historico.txt", true);
            escrita.write(mensagem + "\n"); // O \n serve para pular uma linha
            escrita.close();
        } catch (IOException e) {
            System.out.println("Erro ao guardar histórico: " + e.getMessage());
        }
    }
}