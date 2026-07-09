package pe.edu.nova.java.starters.boot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Anotación de punto de entrada para aplicaciones basadas en el meta-framework
 * Nova Platform.
 * <p>
 * Reemplaza a {@link SpringBootApplication} como anotación principal de la clase
 * de arranque. Internamente hereda toda la funcionalidad de Spring Boot
 * ({@code @SpringBootApplication}), incluyendo auto-configuración, escaneo de
 * componentes y configuración de propiedades.
 * </p>
 *
 * <pre>
 * &#64;NovaSpringBootApplication
 * public class MiAplicacion {
 *     public static void main(String[] args) {
 *         SpringApplication.run(MiAplicacion.class, args);
 *     }
 * }
 * </pre>
 *
 * @author Nova Platform
 * @version 1.0.0
 * @see SpringBootApplication
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication
public @interface NovaSpringBootApplication {
}
