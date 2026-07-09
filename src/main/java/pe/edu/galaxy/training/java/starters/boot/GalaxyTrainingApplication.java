package pe.edu.galaxy.training.java.starters.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Punto de entrada para aplicaciones basadas en el meta-framework Galaxy Training.
 * <p>
 * Envuelve {@link SpringApplication} proporcionando una API simplificada
 * que abstrae Spring Boot del código del equipo. Internamente configura
 * el arranque con los defaults del framework.
 * </p>
 *
 * <pre>
 * &#64;GalaxyTrainingSpringBootApplication
 * public class MiAplicacion {
 *     public static void main(String[] args) {
 *         GalaxyTrainingApplication.run(MiAplicacion.class, args);
 *     }
 * }
 * </pre>
 *
 * @author Galaxy Training
 * @version 1.0.0
 */
public final class GalaxyTrainingApplication {

    /** Constructor privado — clase utilitaria, no instanciable. */
    private GalaxyTrainingApplication() {
    }

    /**
     * Arranca la aplicación Galaxy Training.
     *
     * @param primarySource clase principal anotada con {@link GalaxyTrainingSpringBootApplication}
     * @param args          argumentos de línea de comandos
     * @return el contexto de la aplicación
     */
    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        SpringApplication app = new SpringApplication(primarySource);
        return app.run(args);
    }
}
