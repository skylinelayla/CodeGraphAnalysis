package com.shikun.codeParse;


import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.io.IOException;
import java.util.Arrays;

/**Display detail information of class
 * Created by shikun on 2017/3/10.
 */
public class Test
{
    private static int sampleField;

    public static void main(String[] args) throws IOException{
        ClassParser parser=new ClassParser(Test.class.getResourceAsStream("Test.class"),"Test.class");
        JavaClass javaClass=parser.parse();
        System.out.print("*****constant pool****");
        System.out.println(javaClass.getConstantPool());

        System.out.println("******Fields****");
        System.out.println(Arrays.toString(javaClass.getFields()));

        System.out.println("****Methods*****");
        System.out.println(Arrays.toString(javaClass.getMethods()));

        for (Method method : javaClass.getMethods()) {
            System.out.println(method);
            System.out.println(method.getCode());

        }
//        System.out.println( Test.class.getResourceAsStream("Test.class"));

    }

}
