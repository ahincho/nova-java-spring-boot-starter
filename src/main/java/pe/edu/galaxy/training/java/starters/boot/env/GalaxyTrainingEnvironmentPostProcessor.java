package pe.edu.galaxy.training.java.starters.boot.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Post-procesador de entorno que valida las versiones de Java y Spring Boot
 * durante la fase temprana del arranque de la aplicación.
 *
 * <p>Se ejecuta antes de la creación del {@code ApplicationContext}, lo que permite
 * detener el arranque de forma inmediata si las versiones no cumplen los
 * requisitos mínimos de la organización.</p>
 *
 * <p>Validaciones realizadas:</p>
 * <ul>
 *   <li>Versión de Java: debe ser 25 o superior.</li>
 *   <li>Versión de Spring Boot: debe ser 4.x (major version 4, cualquier minor).</li>
 * </ul>
 *
 * <p>Si la validación de Java falla, la validación de Spring Boot no se ejecuta.
 * Cuando ambas validaciones son exitosas, se registra un mensaje informativo
 * con las versiones detectadas.</p>
 *
 * @author Galaxy Training
 * @version 1.0.0
 */
public class GalaxyTrainingEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(GalaxyTrainingEnvironmentPostProcessor.class);

    /**
     * Crea una nueva instancia del post-procesador de entorno.
     *
     * <p>Este constructor es invocado automáticamente por el mecanismo de
     * carga de Spring Boot a través de {@code spring.factories}.</p>
     */
    public GalaxyTrainingEnvironmentPostProcessor() {
        // Constructor por defecto requerido por Spring Boot
    }

    /** Versión mínima requerida de Java. */
    static final int JAVA_VERSION_MINIMA = 25;

    /** Major version requerida de Spring Boot (acepta cualquier minor). */
    static final int SPRING_BOOT_MAJOR_REQUERIDA = 4;

    /**
     * Valida las versiones de Java y Spring Boot durante el arranque.
     *
     * <p>Primero valida la versión de Java; si falla, no ejecuta la validación
     * de Spring Boot. Cuando ambas validaciones son exitosas, registra un
     * mensaje informativo con las versiones detectadas.</p>
     *
     * @param environment el entorno configurable de Spring
     * @param application la aplicación Spring Boot en proceso de arranque
     * @throws IllegalStateException si la versión de Java o Spring Boot no cumple los requisitos
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        int versionJava = Runtime.version().feature();
        validarVersionJava(versionJava);
        String versionSpringBoot = SpringBootVersion.getVersion();
        validarVersionSpringBoot(versionSpringBoot);
        logger.info("[Galaxy Training] Validación exitosa — Java: {}, Spring Boot: {}", versionJava, versionSpringBoot);
    }

    /**
     * Valida que la versión de Java sea igual o superior a {@value #JAVA_VERSION_MINIMA}.
     *
     * @param versionActual la versión feature de Java detectada en el entorno de ejecución
     * @throws IllegalStateException si la versión es inferior a la requerida
     */
    void validarVersionJava(int versionActual) {
        if (versionActual < JAVA_VERSION_MINIMA) {
            throw new IllegalStateException(
                    "[Galaxy Training] Error: Se requiere Java " + JAVA_VERSION_MINIMA + " o superior. Versión detectada: "
                            + versionActual + ". Por favor, actualice su JDK.");
        }
    }

    /**
     * Valida que la versión major de Spring Boot sea {@value #SPRING_BOOT_MAJOR_REQUERIDA}
     * (acepta cualquier minor: 4.0.x, 4.1.x, 4.2.x, etc.).
     *
     * <p>Si la versión proporcionada es {@code null}, se lanza una excepción indicando
     * que no se pudo determinar la versión del framework.</p>
     *
     * @param versionSpringBoot la cadena de versión de Spring Boot detectada (por ejemplo, "4.0.5")
     * @throws IllegalStateException si la versión major no es 4 o si la versión es {@code null}
     */
    void validarVersionSpringBoot(String versionSpringBoot) {
        if (versionSpringBoot == null) {
            throw new IllegalStateException(
                    "[Galaxy Training] Error: No se pudo determinar la versión de Spring Boot. "
                            + "Verifique que el framework esté correctamente configurado.");
        }

        int majorVersion;
        try {
            majorVersion = Integer.parseInt(versionSpringBoot.split("\\.")[0]);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "[Galaxy Training] Error: Se requiere Spring Boot 4.x (major version 4). Versión detectada: "
                            + versionSpringBoot + ". Por favor, verifique la versión del framework.");
        }

        if (majorVersion != SPRING_BOOT_MAJOR_REQUERIDA) {
            throw new IllegalStateException(
                    "[Galaxy Training] Error: Se requiere Spring Boot 4.x (major version 4). Versión detectada: "
                            + versionSpringBoot + ". Por favor, verifique la versión del framework.");
        }
    }
}
