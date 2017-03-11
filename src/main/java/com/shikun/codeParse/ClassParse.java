package com.shikun.codeParse;

import org.apache.bcel.classfile.*;
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
        classReferenceFormat = "ClassName:" + clazz.getClassName() + " %s";
    }

    public void ClassVisit(JavaClass javaClass){
        //get the information of class
        javaClass.getConstantPool().accept(this);
        Method[] methods = javaClass.getMethods();
        //parse method
        for(int i=0;i<methods.length;i++) {
            methods[i].accept(this);
        }

    }

    public void ConstantPoolVisit(ConstantPool constantPool) {
        for(int i=0;i<constantPool.getLength();i++) {
            Constant constant = constantPool.getConstant(i);
            if (constant == null) {
                continue;

            }
            if (constant.getTag() == 7) {
                String referencedClass = constantPool.constantToString(constant);
                System.out.println(String.format(classReferenceFormat, referencedClass));
            }
        }
    }

    public void start(){
        ClassVisit(clazz);

    }


}
