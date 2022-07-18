import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        
        List<Map<String, String>> original = new ArrayList<>();
        original.add(Map.of("nome", "João", "nascimento", "1985-12-11 12:10:33"));
        original.add(Map.of("nome", "Maria", "nascimento", "24-07-1988 23:02:41"));
        original.add(Map.of("nome", "Ana", "nascimento", "03:58:26 14-02-1983"));
        original.add(Map.of("nome", "Pedro", "nascimento", "08:03:07 1989-11-02"));

        List<Map<String, String>> listaPadronizada = new ArrayList<>();

        List<String> maisVelho = new ArrayList<>();
        maisVelho.add("nome");
        maisVelho.add("idade");
        List<String> maisNovo = new ArrayList<>();
        maisNovo.add("nome");
        maisNovo.add("idade");
        LocalDateTime dataNova = LocalDateTime.now();
        LocalDateTime dataAntiga = LocalDateTime.of(1800, 01, 01, 0, 0, 0);
        

        for(Map<String, String> map : original){
            
            String data = map.get("nascimento");

            // Passar qualquer um dos padrões e deixar no padrão específico HH:mm:ss dd-MM-yyyy
            LocalDateTime dateTime = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss][dd-MM-yyyy HH:mm:ss][HH:mm:ss yyyy-MM-dd][HH:mm:ss dd-MM-yyyy]"));
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

            // Pessoa mais velha
            if (dateTime.isBefore(dataNova)) {
                maisVelho.set(0, map.get("nome"));
                maisVelho.set(1, dateTime.format(formatador));

                dataNova = dateTime;
            }

            // Pessoa mais nova
            if (dateTime.isAfter(dataAntiga)) {
                maisNovo.set(0, map.get("nome"));
                maisNovo.set(1, dateTime.format(formatador));

                dataAntiga = dateTime;
            }
            
            String dataFormatada = dateTime.format(formatador);
            String nomePadronizado = map.get("nome");

            listaPadronizada.add(Map.of("nome", nomePadronizado, "nascimento", dataFormatada));
        
        }

        for (Map<String,String> map : listaPadronizada) {
            System.out.println(map);
            
        }
        
        System.out.println(maisVelho.get(0) + " é o/a mais velho/a, e sua data de nascimento é: " + maisVelho.get(1));
        System.out.println(maisNovo.get(0) + " é o/a mais novo/a, e sua data de nascimento é: " + maisNovo.get(1));
        
    }
}
