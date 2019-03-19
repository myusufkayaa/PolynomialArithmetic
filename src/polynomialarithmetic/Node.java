/*
 * @file PolynomialArithmetic
 * @description dosyadan okunan polinomları belli bir düzene koyarak toplama işlemi yapma
 * @assignment odev1
 * @date 10.03.2019
 * @author Muhammed Yusuf KAYA myusufka@gmail.com
 */
package polynomialarithmetic;


public class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node() {
    }

}
