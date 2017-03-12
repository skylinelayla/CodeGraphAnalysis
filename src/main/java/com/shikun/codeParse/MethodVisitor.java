package com.shikun.codeParse;

import com.shikun.graphOutput.MutilpleOutPut;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.ConstantPushInstruction;
import org.apache.bcel.generic.EmptyVisitor;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ReturnInstruction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** parse method in class and output call info
 * Created by shikun on 2017/2/24.
 */
public class MethodVisitor extends EmptyVisitor  {
    JavaClass visitedClass;
    private MethodGen mg;
    private ConstantPoolGen cp;
    private String format;
    private INVOKEVIRTUAL infoDegree;
    private String DegreeClass;
    private String DegreeMethod;
    private List<String> NodeStartMethod = new ArrayList<String>();
    private List<String> NodeEndMethod = new ArrayList<String>();


    public MethodVisitor(MethodGen m, JavaClass jc) {
        visitedClass = jc;
        mg = m;
        cp = mg.getConstantPool();
        format = "M:" + visitedClass.getClassName() + ":" + mg.getName()
                + " " + "(%s)%s:%s";
    }

    public void start() {
        if (mg.isAbstract() || mg.isNative())
            return;
        for (InstructionHandle ih = mg.getInstructionList().getStart();
             ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();

            if (!visitInstruction(i))
                i.accept(this);
        }
    }

    private boolean visitInstruction(Instruction i) {
        short opcode = i.getOpcode();

        return ((InstructionConstants.INSTRUCTIONS[opcode] != null)
                && !(i instanceof ConstantPushInstruction)
                && !(i instanceof ReturnInstruction));
    }

    @Override
    public void visitINVOKEVIRTUAL(INVOKEVIRTUAL i) {

//        System.out.println(String.format(format,"M",i.getReferenceType(cp),i.getMethodName(cp)));
        String formatInternal = "%s";
        this.DegreeClass = String.format(formatInternal,i.getReferenceType(cp));

        this.DegreeMethod = i.getMethodName(cp);
        //parse the method of class

        System.out.println(visitedClass.getClassName() + ":" + mg.getName() + " CALL " + this.DegreeClass + ":" + this.DegreeMethod);
    }

//    @Override
//    public void visitINVOKEINTERFACE(INVOKEINTERFACE i) {
//        System.out.println(String.format(format,"I",i.getReferenceType(cp),i.getMethodName(cp)));
//    }
//
//    @Override
//    public void visitINVOKESPECIAL(INVOKESPECIAL i) {
//        System.out.println(String.format(format,"O",i.getReferenceType(cp),i.getMethodName(cp)));
//    }
//
//    @Override
//    public void visitINVOKESTATIC(INVOKESTATIC i) {
//        System.out.println(String.format(format,"S",i.getReferenceType(cp),i.getMethodName(cp)));
//    }

    //only parse invoke vitual method call
    public String getNodeMethod() {
        String nodeMethodInfo = visitedClass.getClassName() + " " + mg.getName();
        return nodeMethodInfo;
    }

    public String getDegree(){
        return this.DegreeClass + ":" + this.DegreeMethod;

    }







}
