public class TestMapper {
    public static void main(String[] args) {
        try {
            Mapeado m = new Mapeado();
            BeanMapper.guardar(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
