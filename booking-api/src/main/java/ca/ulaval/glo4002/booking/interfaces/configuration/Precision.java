//Taken from : https://stackoverflow.com/questions/44110624/need-jackson-serializer-for-double-and-need-to-specify-precision-at-runtime

package ca.ulaval.glo4002.booking.interfaces.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Precision {
    int precision();
}