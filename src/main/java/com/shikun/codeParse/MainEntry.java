package com.shikun.codeParse;

import com.shikun.graphOutput.MutilpleOutPut;
import org.apache.bcel.classfile.ClassParser;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Enumeration;

/**
 * Created by shikun on 2017/2/24.
 */
public class MainEntry {
    public static void main(String[] args) throws FileNotFoundException {
        ClassParser cp;
        try {
//            System.out.println(new File(".").getAbsoluteFile());
            File f = new File("C://crowdsourcing-mart-0.0.1.jar");


            if (!f.exists()) {
                System.err.println("Jar file " + "crowdsourcing-mart-0.0.1.jar" + " does not exist");
            }

            JarFile jar = new JarFile(f);

            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory())
                    continue;

                if (!entry.getName().endsWith(".class"))
                    continue;

                cp = new ClassParser("C://crowdsourcing-mart-0.0.1.jar", entry.getName());
                ClassVisitor visitor = new ClassVisitor(cp.parse());
                visitor.start();

            }


        } catch (IOException e) {
            System.err.println("Error while processing jar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
