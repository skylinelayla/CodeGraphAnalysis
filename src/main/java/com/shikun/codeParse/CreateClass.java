package com.shikun.codeParse;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.*;

/**
 * Created by shikun on 2017/3/10.
 */
public class CreateClass {
    public static void main(String[] args) {
        System.out.println("Creating Class");
        ClassGen classGen = new ClassGen("com.shikuncodeParse.shikuntest", "java.lang.Object", "shikuntest.java", Constants.ACC_PUBLIC, null);
        ConstantPoolGen constantPoolGen = classGen.getConstantPool();
        InstructionList instructionList = new InstructionList();
        instructionList.append(new GETSTATIC(constantPoolGen.addFieldref("java.lang.System","out","")));
        instructionList.append(new LDC(constantPoolGen.addString("You are a real geek")));

    }
}
