package Base.Package;

import Base.Package.LinkedList.CustomLinkedList;
import Base.Package.LinkedList.LinkedList;
import Base.Package.LinkedList.LinkedListItem;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new CustomLinkedList();
        System.out.println(linkedList.pop(new LinkedListItem("Value 1")));
        System.out.println(linkedList.pop(new LinkedListItem("Value 2")));
        System.out.println( linkedList.pop(new LinkedListItem("Value 3")));
        System.out.println(linkedList.pop(new LinkedListItem("Value 4")));
        System.out.println(linkedList.pop(new LinkedListItem("Value 5")));

        linkedList.printLinkedListInfo();

        /*linkedList.push();
        linkedList.printLinkedListInfo();

        linkedList.push();
        linkedList.printLinkedListInfo();

        linkedList.push();
        linkedList.printLinkedListInfo();

        linkedList.push();
        linkedList.printLinkedListInfo();*/

        /*linkedList.replace(1, "Replaced Value 1");
        linkedList.printLinkedListInfo();

        linkedList.replace(5, "Replaced Value 5");
        linkedList.printLinkedListInfo();

       linkedList.replace(2, "Replaced Value 2");
       linkedList.printLinkedListInfo();*/

        linkedList.add(1, "Added Value 1");
        linkedList.printLinkedListInfo();

        linkedList.add(6, "Added Value 6");
        linkedList.printLinkedListInfo();

        linkedList.add(3, "Added Value 7");
        linkedList.printLinkedListInfo();

    }
}
