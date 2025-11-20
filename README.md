# Conversor de Moedas e Criptomoedas

Aplicação desenvolvida em Java que realiza a conversão de valores monetários em tempo real, consumindo dados atualizados de uma API externa. O projeto também possui funcionalidade de registro histórico das operações em arquivo de texto.

## Funcionalidades

* **Interação via Console:** Menu de opções intuitivo para seleção de pares de moedas.
* **Consumo de API:** Integração com a *ExchangeRate-API* para obtenção de taxas de câmbio em tempo real.
* **Suporte a Múltiplas Moedas:** Conversões entre Dólar (USD), Real Brasileiro (BRL), Euro (EUR) e Bitcoin (BTC).
* **Persistência de Dados:** Geração automática de um arquivo de log (`historico.txt`) contendo a data, hora e detalhes de cada conversão realizada.
* **Tratamento de Erros:** Gestão de exceções para falhas de conexão ou entradas inválidas.

## Tecnologias Utilizadas

* **Java (JDK 17+):** Uso de `Record` para DTOs (Data Transfer Objects) e `HttpClient` para requisições web.
* **Gson:** Biblioteca do Google para desserialização do JSON retornado pela API.
* **Java IO:** Manipulação de arquivos para escrita do histórico.
* **Java Time:** Utilização de `LocalDateTime` e `DateTimeFormatter` para registro temporal.

## Configuração Necessária

Para executar este projeto, você precisará de uma chave de API gratuita da [ExchangeRate-API](https://www.exchangerate-api.com/).

1. Obtenha sua chave no site mencionado.
2. Abra a classe `ConexaoApi.java`.
3. Localize a variável `API_KEY` e insira sua chave:
   ```java
   private final String API_KEY = "SUA-CHAVE-AQUI";
