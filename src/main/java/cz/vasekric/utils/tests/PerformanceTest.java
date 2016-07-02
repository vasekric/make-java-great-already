package cz.vasekric.utils.tests;

import static cz.vasekric.utils.NullSafe.$resolve;

/**
 * Created by vasek on 02.07.2016.
 */
public class PerformanceTest {

    public static void main(String[] args) {
        final int instances = 100000;
        System.out.println("Testing "+instances+" instances");

        final Main.Foo[] foos = new Main.Foo[instances];
        for (int i = 0, l = foos.length; i < l; i++) {
            foos[i] = new Main.Foo();
        }

        final String[] ifsResult = new String[instances];
        long ifsStart = System.currentTimeMillis();
        for (int i = 0, l = foos.length; i < l; i++) {
            int finalI = i;
            final Main.Foo foo = foos[finalI];
            final Main.Baz baz= foo == null ? null : foo.getBaz();
            final String name = baz == null ? null : baz.getName();
            ifsResult[finalI] = name;
        }
        long ifsTime = System.currentTimeMillis()-ifsStart;
        System.out.println("if else: "+ifsTime);
        System.out.println("if else lastElement: "+ifsResult[ifsResult.length-1]);



        final String[] $Result = new String[instances];
        long resolveStart = System.currentTimeMillis();
        for (int i = 0, l = foos.length; i < l; i++) {
            int finalI = i;
            final String name = $resolve(() -> foos[finalI].getBaz().getName());
            $Result[finalI] = name;
        }
        long resolveTime = System.currentTimeMillis()-resolveStart;
        System.out.println("$resolve: "+resolveTime);
        System.out.println("$resolve lastElement: "+$Result[$Result.length-1]);



        final String[] aspectResult = new String[instances];
        long aspectStart = System.currentTimeMillis();
        for (int i = 0, l = foos.length; i < l; i++) {
            int finalI = i;
            final String name = foos[finalI].$getBaz().$getName();
            final int num = foos[finalI].$getNum();
            aspectResult[finalI] = name;
        }
        long aspectTime = System.currentTimeMillis()-aspectStart;
        System.out.println("aspect: "+aspectTime);
        System.out.println("aspect lastElement: "+aspectResult[aspectResult.length-1]);

    }
}
