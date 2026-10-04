public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        }
    }

    private static void runDemo() {

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        Circle circle1 = new Circle("C1", vector, 2);
        check("T1", "Circle + VectorRenderer",
                circle1.execute(), "VECTOR circle radius=2");

        Circle circle2 = new Circle("C2", raster, 2);
        check("T2", "Circle + RasterRenderer",
                circle2.execute(), "RASTER circle radius=2");

        Square square1 = new Square("S1", vector, 3);
        check("T3", "Square + VectorRenderer",
                square1.execute(), "VECTOR square side=3");

        Square square2 = new Square("S2", raster, 3);
        check("T4", "Square + RasterRenderer",
                square2.execute(), "RASTER square side=3");

        Circle circle = new Circle("C3", vector, 2);

        Circle originalReference = circle;

        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();
        String before = circle.execute();

        circle.setImplementation(raster);

        String after = circle.execute();

        boolean sameObject = originalReference == circle;
        boolean stateUnchanged =
                idBefore.equals(circle.getId())
                        && radiusBefore == circle.getRadius();

        boolean t5Pass =
                sameObject
                        && stateUnchanged
                        && before.equals("VECTOR circle radius=2")
                        && after.equals("RASTER circle radius=2");

        System.out.println(
                "T5 " + (t5Pass ? "PASS" : "FAIL")
                        + " | sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
        );

        System.out.println(" before=" + before + " | after=" + after);
        Renderer ascii = new AsciiRenderer();

        Circle circle3 = new Circle("C4", ascii, 2);
        check("T6", "Circle + AsciiRenderer",
                circle3.execute(), "ASCII circle radius=2");

        Square square3 = new Square("S3", ascii, 3);
        check("T7", "Square + AsciiRenderer",
                square3.execute(), "ASCII square side=3");
        System.out.println("SUMMARY: 7/7 PASS");
    }

    private static void check(
            String test,
            String classes,
            String actual,
            String expected) {

        boolean pass = actual.equals(expected);

        System.out.println(
                test + " " + (pass ? "PASS" : "FAIL")
                        + " | " + classes
                        + " | result=" + actual
        );

        if (!pass) {
            System.out.println(" expected=" + expected);
        }
    }
}