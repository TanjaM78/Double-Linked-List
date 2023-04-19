import java.util.NoSuchElementException;

public class DoubleLinkedList<E> {
    /* <E> bedeutet  die Klasse ist generisch  und  ein Typparameter E zur Laufzeit durch einen bestimmten Typ ersetzt wird.
    Der Typ E kann ein beliebiger Referenztyp sein, z.B. Integer, String, ein anderer Objekttyp oder sogar ein eigener Typ.
    Die DoublyLinkedList-Klasse besteht aus einer privaten inneren Klasse Node, die die einzelnen Knoten der Liste repräsentiert.
    Jeder Knoten hat einen Datenelement (data) vom Typ E, sowie Verweise auf den vorherigen (prev) und den nächsten (next) Knoten in der Liste.
    Die DoublyLinkedList-Klasse selbst enthält zwei private Instanzvariablen head und tail, die den ersten bzw. letzten Knoten in der Liste darstellen.*/

    private Node<E> head;
    private Node<E> tail;

    private int size;

    /*Die Methode fügt ein Element e am Ende der Liste hinzu.
      Zunächst wird geprüft, ob die Liste leer ist, d.h. ob head null ist. Wenn ja, wird ein neuer Knoten erstellt, der head und tail zugewiesen wird.
      Andernfalls wird ein neuer Knoten erstellt und an das Ende der Liste angehängt. Der tail-Zeiger wird auf den neuen Knoten aktualisiert.
      Die Methode gibt immer true zurück.*/
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
        return true;
    }

    /*Die Methode fügt ein Element an der gegebenen Position index in der Liste ein.
       Wenn index kleiner oder gleich 0 ist, wird das Element am Anfang der Liste eingefügt.
       Wenn index größer oder gleich der aktuellen Größe der Liste ist, wird das Element am Ende der Liste eingefügt.
       Andernfalls wird das Element an der Position index eingefügt.
       Die Methode gibt immer true zurück.*/
    public void add(int index, E element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else if (index == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(newNode);
            newNode.setPrev(current.getPrev());
            newNode.setNext(current);
            current.setPrev(newNode);
        }
        size++;
    }

    /*Die Methode gibt die Anzahl der Elemente in der Liste zurück.
      Zunächst wird geprüft, ob die Liste leer ist. Wenn ja, wird 0 zurückgegeben.
      Andernfalls wird eine Schleife durchlaufen, um die Anzahl der Knoten in der Liste zu zählen.*/
    public int size() {
        return size;
    }

    /*Die Methode gibt das Element an der gegebenen Position index in der Liste zurück.
      Wenn index kleiner oder gleich 0 ist, wird das erste Element der Liste zurückgegeben.
      Wenn index größer oder gleich der aktuellen Größe der Liste ist, wird das letzte Element der Liste zurückgegeben.
      Andernfalls wird das Element an der Position index zurückgegeben.
      Die Methode verwendet eine optimierte Methode zum Suchen des Elements von der "nächsten" Seite (vorne/hinten), um die Leistung zu verbessern.*/

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }

        return current.getData();
    }

    /*Die Methode durchläuft die gesamte Liste und gibt den Wert jedes Elements aus.
     Zunächst wird geprüft, ob die Liste leer ist. Wenn ja, wird "Liste ist leer" ausgegeben.
     Andernfalls wird eine Schleife durchlaufen, um den Wert jedes Knotens in der Liste auszugeben.*/
    public void printList() {
        Node current = head;
        System.out.print("null <-> ");
        while (current != null) {
            System.out.print(current.getData() + " <-> ");
            current = current.getNext();
        }
        System.out.print("null");
        System.out.println();
    }

    /*die Methode entfernt das Element an der gegebenen Position index aus der Liste und gibt es zurück.
    Wenn index kleiner oder gleich 0 ist, wird das erste Element der Liste entfernt und zurückgegeben.
    Wenn index größer oder gleich der aktuellen Größe der Liste ist, wird das letzte Element der Liste entfernt und zurückgegeben.
    Andernfalls wird das Element an der Position index entfernt und zurückgegeben.
    Die Methode verwendet eine optimierte Methode zum Suchen des Elements von der "nächsten" Seite (vorne/hinten), um die Leistung zu verbessern.*/
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        if (index == 0) {
            current = head;
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            current = tail;
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--;  // decrease the size variable by 1
        return current.getData();
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /*removeFirst() entfernt das erste Element der Liste und gibt dessen Wert zurück.
     Zu Beginn wird überprüft, ob die Liste leer ist. Falls dies der Fall ist, wird eine NoSuchElementException mit der Nachricht "List is empty" geworfen.
     Wenn die Liste mindestens ein Element enthält, wird das erste Element aus der Liste entfernt und dessen Wert in der Variable element gespeichert. Dazu wird die Referenz auf das aktuelle head-Element auf das nächste Element in der Liste aktualisiert und die Größe der Liste um 1 verringert.
     Falls das letzte Element aus der Liste entfernt wird, wird auch die Referenz auf das tail-Element auf null gesetzt. Andernfalls wird die Referenz auf das vorherige Element des neuen head-Elements auf null gesetzt.
     Am Ende wird der Wert des entfernten Elements zurückgegeben.*/

    public E removeFirst() {
        /*
        return remove(0);
         */
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        E element = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        size--;
        return element;
    }
    /*addFirst(E data) fügt ein neues Element am Anfang der Liste ein. Dazu wird zuerst ein neues Node-Objekt erstellt, das den übergebenen Wert data enthält.
     Wenn die Liste leer ist (head == null), wird das neue Node-Objekt sowohl head als auch tail der Liste. Andernfalls wird das neue Node-Objekt an den Anfang der Liste gesetzt.
     Dazu wird das next-Attribut des neuen Knotens auf den bisherigen Kopf der Liste gesetzt, und das prev-Attribut des alten Kopfs auf das neue Node-Objekt.
     Schließlich wird das head-Attribut der Liste auf das neue Node-Objekt gesetzt.
     Abschließend wird die Größe der Liste um 1 erhöht.*/

    public void addFirst(E data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

}



