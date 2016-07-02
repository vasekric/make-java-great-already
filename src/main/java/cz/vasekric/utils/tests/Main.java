package cz.vasekric.utils.tests;

/**
 * Created by vasek on 01.07.2016.
 */
public class Main {

    public static void main(String[] args) {

        final Foo foo = null;
        final String name = foo.$getBaz().$getName();
        System.out.println(name);

        final int num = foo.$getNum();
        System.out.println(num);
    }

    public static class Foo {
        private Baz baz;
        private int num;

        public int $getNum() {
            return num;
        }

        public Baz getBaz() {
            return baz;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Baz $getBaz() {
            return baz;
        }

        public void setBaz(Baz baz) {
            this.baz = baz;
        }
    }

    public static class Baz {
        private String name;

        public String $getName() {
            return name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
