/*
 * @file PolynomialArithmetic
 * @description dosyadan okunan polinomları belli bir düzene koyarak toplama işlemi yapma
 * @assignment odev1
 * @date 10.03.2019
 * @author Muhammed Yusuf KAYA myusufka@gmail.com
 */
package polynomialarithmetic;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author myusu
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Scanner s=new Scanner(System.in);
        System.out.println("Lütfen dosya uzantınızı giriniz. Dosyalar arasını çift ters slash olarak belirleyin. Veri.txt doyanızda da satırlarda kuvvet ve katsayı arasını bir virgül ve bir boşluk (, ) şeklinde hazırlayınız.");
        String dosya=s.next();
        File file=new File(dosya);
         LinkedList<Node<Term>> liste=new LinkedList<>();
         liste.polinomOlustur(file);
         int toplam= liste.topla(file);
         System.out.println("");
         System.out.println(toplam);
       
    }
}
