package com.shikun.codeParse;


import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.MethodGen;

import java.util.ArrayList;
import java.util.List;

/**parse jar file class
 * Created by shikun on 2017/2/24.
 */
public class ClassVisitor extends EmptyVisitor{
    private JavaClass clazz;
    private ConstantPoolGen constants;
    private String classReferenceFormat;

    public ClassVisitor(JavaClass jc) {
        clazz = jc;
        constants = new ConstantPoolGen(clazz.getConstantPool());
        classReferenceFormat = "C:" + clazz.getClassName() + " %s";
    }

    public void visitJavaClass(JavaClass jc) {
        jc.getConstantPool().accept(this);
        Method[] methods = jc.getMethods();
        for (int i = 0; i < methods.length; i++)
            methods[i].accept(this);
    }

    public void visitConstantPool(ConstantPool constantPool) {
        for (int i = 0; i < constantPool.getLength(); i++) {
            Constant constant = constantPool.getConstant(i);
            if (constant == null)
                continue;
            if (constant.getTag() == 7) {
                String referencedClass =
                        constantPool.constantToString(constant);
//                System.out.println(String.format(classReferenceFormat,
//                        referencedClass));
            }
        }
    }

    public void visitMethod(Method method) {
        MethodGen mg = new MethodGen(method, clazz.getClassName(), constants);
        MethodVisitor visitor = new MethodVisitor(mg, clazz);
        visitor.start();
//        this.nodeInfo.add(visitor.getNodeMethod());
//        this.degreeInfo.add(visitor.getDegree());
//        System.out.println(visitor.getNodeMethod() + " CALL " + visitor.getDegree());
    }

    public void start() {
        visitJavaClass(clazz);
    }


}
