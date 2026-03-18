package com.anderson.multilang.service;

import groovy.lang.GroovyClassLoader;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Serviço responsável pelo gerenciamento e execução de regras de negócio dinâmicas em Groovy.
 * <p>
 * Este serviço utiliza um {@link GroovyClassLoader} para carregar e compilar scripts em tempo de execução,
 * permitindo que as regras de bônus sejam alteradas sem a necessidade de reiniciar a aplicação.
 * Implementa um mecanismo de cache baseado no timestamp do arquivo para otimizar a performance.
 * </p>
 * * @author Anderson
 * @version 1.1
 */
@Service
public class RuleEngineService {

    /**
     * Loader responsável por transformar o código-fonte Groovy em classes Java (Bytecode).
     */
    private final GroovyClassLoader loader = new GroovyClassLoader();

    /**
     * Cache thread-safe que armazena a classe compilada e a data da última modificação do arquivo.
     * A chave é o caminho absoluto ou relativo do script.
     */
    private final Map<String, CachedRule> cache = new ConcurrentHashMap<>();

    /**
     * Executa a regra de cálculo de bônus a partir de um script Groovy externo.
     * <p>
     * O método verifica se o script no disco foi alterado desde a última execução. 
     * Se houve mudança, o script é recompilado; caso contrário, a versão em cache é utilizada.
     * </p>
     * * @param salario O valor base do salário para o cálculo.
     * @return O valor do bônus calculado pelo script Groovy.
     * @throws Exception Caso o arquivo não seja encontrado, contenha erros de sintaxe ou 
     * ocorram falhas durante a invocação reflexiva do método.
     */
    public double executeBonusRule(double salario) throws Exception {

        // TODO - Caminho do script (idealmente viria de um arquivo de configuração)
        // Ajustar futuramente para que o arquivo seja carregado via 
        // arquivo de configuracao ou variavel de ambiente
        String filePath = "src/main/groovy/com/anderson/multilang/rules/BonusCalculator.groovy";
        File scriptFile = new File(filePath);
        long lastModified = scriptFile.lastModified();

        CachedRule cached = cache.get(filePath);

        // Lógica de Cache: Só recompila se o arquivo mudou ou não existe no cache
        if (cached == null || cached.timestamp < lastModified) {
            // Log de depuração opcional: System.out.println("Recarregando script Groovy...");
            Class<?> clazz = loader.parseClass(scriptFile);
            cached = new CachedRule(clazz, lastModified);
            cache.put(filePath, cached);
        }

        // Invoca o método estático "calcularBonus" via Reflexão
        Object result = cached.clazz.getMethod("calcularBonus", double.class)
                .invoke(null, salario);

        return (double) result;
    }

    /**
     * Registro auxiliar (Record) para armazenar a classe compilada e seu respectivo timestamp.
     * * @param clazz A {@link Class} carregada na JVM.
     * @param timestamp O timestamp (milissegundos) da última modificação do arquivo no momento da carga.
     */
    private static record CachedRule(Class<?> clazz, long timestamp) {}

    /**
     * Limpa o cache de regras, forçando a recompilação dos scripts na próxima execução.
     * Útil para cenários onde as regras foram atualizadas e é necessário garantir que a versão mais recente seja utilizada.
     */
    public void clearCache() {
    cache.clear();
    }

    /**
    * Retorna o número de regras atualmente armazenadas no cache, útil para monitoramento e depuração.
    * Nota: Em um cenário real, o cache poderia armazenar múltiplas regras, e este método ajudaria a verificar quantas estão ativas.
    * @return O tamanho do cache de regras.
    */
    public int getCacheSize() {
    return cache.size();
    }
    
}