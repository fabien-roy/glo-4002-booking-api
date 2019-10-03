// Taken from : https://stackoverflow.com/questions/44110624/need-jackson-serializer-for-double-and-need-to-specify-precision-at-runtime

package ca.ulaval.glo4002.booking.interfaces.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DoubleContextualSerializer extends JsonSerializer<Double> implements ContextualSerializer {

    private int precision = 0;

    public DoubleContextualSerializer (int precision) {
        this.precision = precision;
    }

    public DoubleContextualSerializer () {

    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (precision == 0) {
            gen.writeNumber(value.doubleValue());
        } else {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#.00", otherSymbols);
            gen.writeNumber(df.format(value));
        }

    }
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Precision precision = property.getAnnotation(Precision.class);
        if (precision != null) {
            return new DoubleContextualSerializer (precision.precision());
        }
        return this;
    }
}
