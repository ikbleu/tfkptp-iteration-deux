package src.control;

import src.control.interfaces.ContextTranslator;
import src.control.interfaces.ConfigurableTranslator;
import java.util.Hashtable;
import java.util.Map;

/**
 * This is not thread safe. It was never intended to be.
 * If you want a thread safe version then go and implement a different class.
 * Do not make this one thread safe.
 *
 * @author kagioglu
 */
class HashtableTranslator implements ContextTranslator, ConfigurableTranslator {
    private Hashtable<String, String> function;
    HashtableTranslator() {
        this.function = new Hashtable<String,String>();
    }
    public String translate(String input) {
        if(this.function.containsKey(input)) {
            return this.function.get(input);
        }
        else { throw new RuntimeException("unknown input context"); }
    }
    public void register(String input, String output) {
        this.function.put(input, output);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for(Map.Entry entry : this.function.entrySet()) {
            sb.append('{');
            sb.append(entry.getKey());
            sb.append(':');
            sb.append(entry.getValue());
            sb.append('}');
        }
        sb.append('}');
        return sb.toString();
    }
}
