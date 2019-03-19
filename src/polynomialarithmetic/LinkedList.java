/*
 * @file PolynomialArithmetic
 * @description dosyadan okunan polinomları belli bir düzene koyarak toplama işlemi yapma
 * @assignment odev1
 * @date 10.03.2019
 * @author Muhammed Yusuf KAYA myusufka@gmail.com
 */
package polynomialarithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LinkedList<T> {

    Node<T> head;

    void addFirst(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    void addFirst(T data) {
        addFirst(new Node<>(data));
    }

    void addLast(Node data) {
        Node<T> newNode = data;

        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }
    }

    void insertAfter(Node data, Node search) {
        Node<T> temp = head;

        while (temp != null && temp != search) {
            temp = temp.next;
        }

        if (temp != null) {
            Node<T> newNode = data;
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    boolean remove(T data) {
        if (head == null) {
            System.out.println("empty list !");
        } else {
            if (head.data.equals(data)) {
                head = head.next;
                return true;
            } else {
                Node<T> temp = head.next;
                Node<T> prev = head;

                while (temp != null && !temp.data.equals(data)) {
                    prev = temp;
                    temp = temp.next;
                }

                if (temp != null) {
                    prev.next = temp.next;
                    return true;
                } else {
                    System.out.println(data + " not found !");
                }
            }
        }
        return false;
    }

    void print() { // Bu metodda oluşan polinomumuzu yazdırıyoruz
       
        Node <Node<Term>> temp= (Node <Node<Term>>) head;
        
        while (temp != null) { //Tabiri caizse iki boyutlu bir liste olduğu için ilk önce dışarıdaki node sonrasında da içerideki node için döneceğiz
            System.out.print("["); 
            Node<Term> nTemp=temp.data;
            while (nTemp != null) {
                System.out.print("("+nTemp.data.coeff + "x^" + nTemp.data.exp + ")+"); //Aşağıya dallanan node'lar belli olsun diye aynı parantez içinde yazdırıyoruz
                nTemp = nTemp.next;
            }
            System.out.print("]+");
            temp = temp.next;
        }
    }
  


    int size() {
        Node<T> temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }

    void ayniKuvvetEkle(Node<Term> search, Node<Term> data) {
        search.next = data;

    }
    int topla(File file) throws FileNotFoundException, IOException{ //Print metodunda yaptığımız işlemi bu sefer toplamak için yapıyoruz.
        print();
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();
        int x=Integer.valueOf(line);
        int toplam=0;
        Node<Node<Term>> temp = new Node<Node<Term>>();
        temp=(Node<Node<Term>>) this.head;
        while (temp != null) {
            Node<Term> nTemp=temp.data;
            while (nTemp != null) {
                toplam+=Math.pow(x, nTemp.data.exp)*nTemp.data.coeff;
                nTemp = nTemp.next;
            }
            temp = temp.next;
        }
        return toplam;
    }

    

   void polinomOlustur(File file) throws FileNotFoundException, IOException {

         LinkedList<Node<Term>> liste =new LinkedList<>();  
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        line = br.readLine();
        int  counter=0;

        while ((line = br.readLine()) != null) { //Dosyayı sonuna kadar okuyoruz ve bu döngünün içinde işlemleri yapıyoruz
            
            if (line.equals("********")) { //Eğer polinom ayrımına gelirsek sayaçımızı arttırıyoruz ve sonraki ssatıra geçiyoruz
                counter++;
                continue;
            }
            String satir[] = line.split(", "); // Virgül ve boşluğa göre satırı bölüyoruz.
            Term t = new Term(Integer.valueOf(satir[0]), Integer.valueOf(satir[1])); // Bu değerleri Term adını verdiğim polinom elemanı objesine atıyoruz.
            Node<Term> n = new Node<>(t); //İç içe node oluşturuyoruz. Dışarıdaki Node sağını gösterirken, içerideki node aşağısını gösterecek.
            Node<Node<Term>> newNode = new Node<>(n);
            Node<Node<Term>> temp = (Node<Node<Term>>) this.head;
            if (this.head == null || temp.data.data.exp < newNode.data.data.exp) { //Eğer listemiz boşsa ya da okuduğumuz değer headden büyükse başa ekliyor.
                this.addFirst(newNode);
                continue;
            } else {
                while (temp != null) {
                    if (temp.data.data.exp > newNode.data.data.exp &&temp.next!=null && temp.next.data.data.exp < newNode.data.data.exp ) { //Burada da araya ekleyeceğimiz elemanları tespit edip ekliyoruz.
                        this.insertAfter(newNode, temp);
                        break;
                    } else if (temp.next == null && temp.data.data.exp > newNode.data.data.exp) { //Burada da sona ekliyoruz.
                        this.addLast(newNode);
                        break;
                    } else if (temp.data.data.exp == newNode.data.data.exp) { //Eğer kuvvetimiz eşitse içeride tutulan node'un nextine yani aşağısına eklemiş oluyoruz.
                        newNode.data.next=temp.data.next;
                        temp.data.next=newNode.data;
                       
                        break;
                    }
                    temp = temp.next;
                }

            }

        }
        System.out.println(counter +" tane polinom birleştirildi"); //Sonunda da kaç tane polinom okuduğumuzu yazdırıyoruz
        
    }
}
