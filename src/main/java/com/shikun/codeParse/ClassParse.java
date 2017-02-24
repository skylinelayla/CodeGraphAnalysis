package com.shikun.codeParse;

import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;

/**parse jar file class
 * Created by shikun on 2017/2/24.
 */
public class ClassParse extends EmptyVisitor{
    private JavaClass clazz;
    private ConstantPoolGen constants;
    private String classReferenceFormat;

    public ClassParse(JavaClass jc) {
        clazz = jc;
        constants = new ConstantPoolGen(clazz.getConstantPool());
        classReferenceFormat = "C:" + clazz.getClassName() + " %s";
    }



}
