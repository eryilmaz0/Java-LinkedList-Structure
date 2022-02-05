package Base.Package.LinkedList;

public class LinkedListItem{
    private String value;
    private LinkedListItem previous;
    private LinkedListItem next;


    public LinkedListItem(String value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    public LinkedListItem(String value, LinkedListItem previousItem, LinkedListItem nextItem) {
        this.value = value;
        this.previous = previousItem;
        this.next = nextItem;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LinkedListItem getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedListItem previous) {
        this.previous = previous;
    }

    public LinkedListItem getNext() {
        return next;
    }

    public void setNext(LinkedListItem next) {
        this.next = next;
    }
}
