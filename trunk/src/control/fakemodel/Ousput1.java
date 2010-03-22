package src.control.fakemodel;

/**
 *
 * @author kagioglu
 */
public class Ousput1 {
    private boolean expecting;
    private String data;
    public Ousput1() { this.expecting = false; }
    public void predict(String ous) {
        if(this.expecting) { throw new RuntimeException("already expecting"); }
        else {
            this.data = ous;
            this.expecting = true;
        }
    }
    public void test(String put) {
        if(this.expecting) {
            if(this.data.compareTo(put) != 0) {
                throw new RuntimeException("wrong prediction");
            }
            else { this.expecting = false; }
        }
        else { throw new RuntimeException("not expecting!"); }
    }
}
