package com.shikun.codeTest;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shikun on 2017/5/19.
 */
public class Test {
    public int solve(int n, int k) {
        ArrayList arrayList = new ArrayList();
        int min, max = 0;
        if (n < k) {
            min = n;
            max = k;
        } else {
            min = k;
            max = n;
        }

        for (int i = min; i <= max; i++) {
            arrayList.add(i);
        }

        int numberofInversions = 0;
        for (int i = 0; i < arrayList.size() - 1; i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                if ((Integer) arrayList.get(i) > (Integer) arrayList.get(j)) {
                    numberofInversions++;

                }
            }
        }
        return numberofInversions;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

    }
}

//    public String sovle2(String a, String b) {
//        String templist[]=b.split(" ");
//        String mnlist[]=a.split(" ");
//        int list[]=new int[templist.length];
//        for(int i=0;i<templist.length;i++){
//            list[i]=Integer.parseInt(templist[i]);
//        }
//        int m=Integer.parseInt(mnlist[1]);
//        int sum=0;
//        for(int i=0;i<list.length;i++){
//
//
//        }
//
//    }
//}
