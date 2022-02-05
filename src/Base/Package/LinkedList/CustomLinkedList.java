package Base.Package.LinkedList;

public class CustomLinkedList implements LinkedList {

    private LinkedListItem[] listItems;
    private boolean listHasLimit = false;
    private int listLimit = 0;

    public CustomLinkedList(){
        this.listItems = new LinkedListItem[0];

    }

    public CustomLinkedList(int listLimit){
        this();
        this.listHasLimit = true;
        this.listLimit = listLimit;
    }


    private int getListLength(){
        return this.listItems.length;
    }


    @Override
    public void clear() {
        this.listItems = (LinkedListItem[]) new Object[0];
        System.out.println("Linked List Cleared.");
    }



    @Override
    public void printLinkedListInfo() {
        String listCapacityText = this.listHasLimit ? Integer.toString(this.listLimit) : "Dynamic";
        System.out.println("************ LINKED LIST INFO ************");
        System.out.println("List Capacity : " +listCapacityText);
        System.out.println("List Count : " +this.listItems.length);
        System.out.println("List Members :");

        LinkedListItem firstItem = this.listItems[0];
        LinkedListItem lastItem = this.listItems[getListLength() - 1];

        System.out.println("Previous : null , Value :" +firstItem.getValue() +", Next : " +firstItem.getNext().getValue());

        for(int i = 1; i < this.listItems.length -1; i++){
            System.out.println("Previous :" +this.listItems[i].getPrevious().getValue()
                               + ", Value : " +this.listItems[i].getValue()
                               +", Next : " +this.listItems[i].getNext().getValue());
        }

        System.out.println("Previous :" +lastItem.getPrevious().getValue() +", Value :" +lastItem.getValue() +", Next : null");
    }



    @Override
    public boolean pop(LinkedListItem value) {

        if(this.checkListCapacityIsFull()){
            System.out.println("List Capacity is Full.");
            return false;
        }

        LinkedListItem[] tempList = this.listItems;
        this.listItems = new LinkedListItem[tempList.length + 1];

        for(int i = 0; i < tempList.length; i++){
            this.listItems[i] = tempList[i];
        }

        //Add new item
        if(this.getListLength() == 1){
            this.listItems[0] = value; //previous-next are null
        }
        else{
            //Link them eact other
            this.listItems[getListLength() - 1] = value;
            LinkedListItem previousItem =  this.listItems[getListLength() - 2];
            previousItem.setNext(value);
            value.setPrevious(previousItem);

        }
        return true;
    }



    @Override
    public void push() {
        if(getListLength() == 0) return;

        LinkedListItem[] tempList = this.listItems;
        this.listItems = new LinkedListItem[tempList.length - 1];

        LinkedListItem pushedValue = tempList[this.getListLength()];

        for(int i = 0; i < this.listItems.length; i++){
            this.listItems[i] = tempList[i];
        }

        if(this.getListLength() != 0){
            //Delete link on the last item
            this.listItems[this.getListLength() - 1].setNext(null);
        }

        System.out.println(pushedValue.getValue() +"Pushed From List.");
    }



    @Override
    public boolean add(int orderNumber, String value) {
        int index = orderNumber - 1;
        boolean indexSliderEnabled = false;
        boolean setObjectReferences = false;

        if(checkListCapacityIsFull()){
            System.out.println("List is at capacity.");
            return false;
        }

        //Is List Empty?
        if(getListLength() == 0){
            LinkedListItem newItem = new LinkedListItem(value);
            this.listItems = new LinkedListItem[1];
            this.listItems[0] = newItem;
            return true;
        }

        //Do we want to add element into the first index?
        if(orderNumber == 1){
            LinkedListItem newItem = new LinkedListItem(value);
            boolean result = this.addElementIntoFirstIndex(newItem);
            return result;
        }

        //Do we want to add element into the last index?
        if(orderNumber == getListLength()){
            LinkedListItem newItem = new LinkedListItem(value);
            boolean result = this.addElementIntoLastIndex(newItem);
            return result;
        }

        //Add value into the list
        LinkedListItem newItem = new LinkedListItem(value);

        LinkedListItem[] tempArray = this.listItems;
        this.listItems = new LinkedListItem[tempArray.length + 1];

        for(int i = 0; i < this.listItems.length; i++){


            if(i == index){
                LinkedListItem previousItem = this.listItems[i - 1];
                previousItem.setNext(newItem);
                newItem.setPrevious(previousItem);
                this.listItems[i] = newItem;
                indexSliderEnabled = true;
                setObjectReferences = true;
                continue;
            }

            if(indexSliderEnabled){
                this.listItems[i] = tempArray[i - 1];

                if(setObjectReferences){
                    //We should set object references wiring
                    LinkedListItem addedItem = this.listItems[i - 1];
                    addedItem.setNext(this.listItems[i]);
                    this.listItems[i].setPrevious(addedItem);
                    setObjectReferences = false;
                }

                continue;
            }

            this.listItems[i] = tempArray[i];
        }

        return true;
    }



    @Override
    public boolean replace(int orderNumber, String value) {
        int index = orderNumber - 1;

        if(getListLength() == 0){
            System.out.println("List is empty.");
            return false;
        }

        if(checkListCapacityIsFull()){
            System.out.println("List is at capacity.");
            return false;
        }

        if(index == 0){
            LinkedListItem nextItem = this.listItems[index + 1];
            LinkedListItem newItem = new LinkedListItem(value);
            this.listItems[index] = newItem;

            //Wiring object references
            newItem.setNext(nextItem);
            nextItem.setPrevious(newItem);
            return true;
        }

        else if(index == getListLength() - 1){
            LinkedListItem previousItem = this.listItems[index - 1];
            LinkedListItem newItem = new LinkedListItem(value);
            this.listItems[index] = newItem;

            //Wiring object references
            newItem.setPrevious(previousItem);
            previousItem.setNext(newItem);
            return true;
        }

        //We are getting items for wiring object references
        LinkedListItem previousItem = this.listItems[index - 1];
        LinkedListItem nextItem = this.listItems[index + 1];

        LinkedListItem newItem = new LinkedListItem(value, previousItem, nextItem);
        this.listItems[index] = newItem;
        previousItem.setNext(newItem);
        nextItem.setPrevious(newItem);

        return true;
    }



    private boolean checkListCapacityIsFull(){
        if(!this.listHasLimit)
            return false;

        if(this.getListLength() < this.listLimit)
            return false;

        return true;
    }



    private boolean addElementIntoFirstIndex(LinkedListItem item){
        LinkedListItem[] tempArray = this.listItems;
        this.listItems = new LinkedListItem[tempArray.length + 1];

        for(int i = 0; i < tempArray.length; i++){
            this.listItems[i + 1] = tempArray[i];
        }

        LinkedListItem nextItem = this.listItems[1];
        nextItem.setPrevious(item);
        item.setNext(nextItem);

        this.listItems[0] = item;
        return true;
    }



    private boolean addElementIntoLastIndex(LinkedListItem item){
        LinkedListItem[] tempArray = this.listItems; //0,1,2
        this.listItems = new LinkedListItem[tempArray.length + 1];  //0,1,2,3

        for(int i = 0; i < tempArray.length; i++){
            this.listItems[i] = tempArray[i];
        }

        LinkedListItem previousItem = this.listItems[tempArray.length - 1];
        previousItem.setNext(item);
        item.setPrevious(previousItem);
        this.listItems[tempArray.length] = item;
        return true;
    }
}
