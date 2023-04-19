
public class Main {
    public static void main(String[] args) {
        DoubleLinkedList <Integer> list = new DoubleLinkedList<>();

        //  add
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(4);
        list.printList();  //  "null <-> 1 <-> 2 <-> 5 <-> 4<-> null"

        //  get
        System.out.println("Element at index 0: " + list.get(0));
        list.printList();

        //  add  index
        list.add(0, 4);
        list.printList();

        // size
        System.out.println("Size of list: " + list.size());  //"Size of list: 4"
        list.printList();

        //  remove
        list.remove(3);
        list.printList();  // Expected output: "null <-> 4 <-> 1 <-> 2 <-> null"
    }

}
