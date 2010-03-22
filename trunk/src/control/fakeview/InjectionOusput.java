package src.control.fakeview;

/**
 *
 * @author kagioglu
 */
public class InjectionOusput {
    private boolean expecting;
    private String context;
    private String[] directory;
    private String[] element;
    private String selection;
    public InjectionOusput() { this.expecting = false; }
    public void predict(
        String context,
        String[] directory,
        String[] element,
        String selection
    ) {
        if(this.expecting) { throw new RuntimeException("already expecting"); }
        else {
            this.context = context;
            this.directory = directory;
            this.element = element;
            this.selection = selection;
            this.expecting = true;
        }
    }
    public void test(
        String context,
        String[] directory,
        String[] element,
        String selection
    ) {
        if(this.expecting) {
            InjectionOusput.sameString(this.context, context);
            InjectionOusput.sameArray(this.directory, directory);
            InjectionOusput.sameArray(this.element, element);
            InjectionOusput.sameString(this.selection, selection);
            this.expecting = false;
        }
        else { throw new RuntimeException("not expecting!"); }
    }
    private static void sameString(String a, String b) {
        if(a.compareTo(b) != 0) {
            throw new RuntimeException("wrong prediction string");
        }
        else { /*DO NOTHING*/ }
    }
    private static void sameArray(String[] a, String[] b) {
        if(a.length != b.length) {
            throw new RuntimeException("wrong prediction length");
        }
        else {
            for(int i = 0; i < a.length; i++) {
                InjectionOusput.sameString(a[i], b[i]);
            }
        }
    }
}
