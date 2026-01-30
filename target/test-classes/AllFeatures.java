public class AllFeatures {
    private int x = 0;
    public static final String CONST = "CONST";
    public AllFeatures() { x = 1; }
    public void foo(int a) {
        if (a > 0) {
            for (int i = 0; i < a; i++) {
                x += i;
            }
        } else {
            while (x < 10) x++;
        }
    }
    public static void main(String[] args) {
        new AllFeatures().foo(5);
    }
}
