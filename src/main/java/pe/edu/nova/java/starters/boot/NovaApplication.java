package pe.edu.nova.java.starters.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Punto de entrada para aplicaciones basadas en el meta-framework Nova Platform.
 * <p>
 * Envuelve {@link SpringApplication} proporcionando una API simplificada
 * que abstrae Spring Boot del código del equipo. Internamente configura
 * el arranque con los defaults del framework.
 * </p>
 *
 * <pre>
 * &#64;NovaSpringBootApplication
 * public class MiAplicacion {
 *     public static void main(String[] args) {
 *         NovaApplication.run(MiAplicacion.class, args);
 *     }
 * }
 * </pre>
 *
 * @author Nova Platform
 * @version 1.0.0
 */
public final class NovaApplication {

    /** Constructor privado — clase utilitaria, no instanciable. */
    private NovaApplication() {
    }

    /**
     * Arranca la aplicación Nova Platform.
     *
     * @param primarySource clase principal anotada con {@link NovaSpringBootApplication}
     * @param args          argumentos de línea de comandos
     * @return el contexto de la aplicación
     */
    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        SpringApplication app = new SpringApplication(primarySource);
        return app.run(args);
    }
}
