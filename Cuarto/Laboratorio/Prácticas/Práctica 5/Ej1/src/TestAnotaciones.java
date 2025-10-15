public class TestAnotaciones {
    public static void main(String arg[]) throws Exception {
        new TestAnotaciones().testearYa();
    }
    @SuppressWarnings({"deprecation"})
    public void testearYa() {
        TestDeprecated t2 = new TestDeprecated();
        t2.hacer();
    }
}
