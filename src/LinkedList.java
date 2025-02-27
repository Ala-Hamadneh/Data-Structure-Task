public class LinkedList {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public LinkedList(Node head) {
        super();
        this.head = head;
    }

    public void insert(int value) {
        if(this.head==null) {
            head = new Node(value);
            return;
        }
        Node temp = head;
        while(temp.getNext()!=null) {
            temp = temp.getNext();
        }
        temp.setNext(new Node(value));
    }

    public void insertAtBeginning(int value) {
        Node temp = new Node(value);
        temp.setNext(head);
        head = temp;
    }

    public void delete(int value) {
        if (head ==null) {
            System.out.println("LinkedList is Empty ");
            return;
        }
        if(head.getValue()==value) {
            head=head.getNext();
            return;
        }
        Node temp = head;
        while(temp.getNext()!=null && temp.getNext().getValue()!=value) {
            temp=temp.getNext();
        }
        if(temp.getNext()==null) {
            System.out.println("Node not found ");
            return;
        }

        temp.setNext(temp.getNext().getNext());


    }

    public void display() {
        System.out.println("Here is the LinkedList :");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getValue());
            if(temp.getNext()!=null) {
                System.out.print( " -> ");
            }
            temp = temp.getNext();
        }
        System.out.println("");
    }

    public void reverse() {
        Node tempP=null,tempC=head,tempN=null;

        while (tempC != null) {
            tempN = tempC.getNext();  // Store the next node
            tempC.setNext(tempP);  // Reverse the current node's pointer
            tempP = tempC;       // Move prev to the current node
            tempC = tempN;       // Move to the next node
        }
        head = tempP;  // Update the head to the new first node

    }

    public static int findMiddle(LinkedList list) {
        Node slow = list.getHead();
        Node fast = list.getHead();

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow.getValue();
    }


    public static Node detectCycle(LinkedList list) {
        Node slow = list.getHead();
        Node fast = list.getHead();

        // Detect the cycle
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow == fast) {
                // Find the starting node of the cycle
                slow = list.getHead();
                while (slow != fast) {
                    slow = slow.getNext();
                    fast = fast.getNext();
                }
                return slow;
            }
        }

        return null; // No cycle
    }

    public static int findThirdFromEnd(LinkedList list) {
        Node first = list.getHead();
        Node second = list.getHead();

        // Move the first pointer n steps ahead
        for (int i = 0; i < 3; i++) {
            if (first == null) {
                return -1; // List is shorter than n
            }
            first = first.getNext();
        }

        // Move both pointers until the first pointer reaches the end
        while (first != null) {
            first = first.getNext();
            second = second.getNext();
        }

        return second.getValue();
    }

    public static void main(String[] args) {
        System.out.println("===== LinkedList Testing =====");

        // Create a new empty list
        System.out.println("\n1. Creating a new empty LinkedList");
        LinkedList list = new LinkedList(null);
        list.display();

        // Test insert method
        System.out.println("\n2. Testing insert() method");
        System.out.println("Adding elements: 5, 10, 15, 20, 25");
        list.insert(5);
        list.insert(10);
        list.insert(15);
        list.insert(20);
        list.insert(25);
        list.display();

        // Test insertAtBeginning method
        System.out.println("\n3. Testing insertAtBeginning() method");
        System.out.println("Adding 1 at the beginning");
        list.insertAtBeginning(1);
        list.display();

        // Test delete method
        System.out.println("\n4. Testing delete() method");

        // Delete a middle element
        System.out.println("Deleting element with value 15");
        list.delete(15);
        list.display();

        // Delete first element
        System.out.println("Deleting first element (value 1)");
        list.delete(1);
        list.display();

        // Delete last element
        System.out.println("Deleting last element (value 25)");
        list.delete(25);
        list.display();

        // Delete non-existent element
        System.out.println("Attempting to delete non-existent element (value 100)");
        list.delete(100);
        list.display();

        // Test findMiddle method
        System.out.println("\n5. Testing findMiddle() method");
        System.out.println("Current list:");
        list.display();
        System.out.println("Middle value: " + findMiddle(list));

        // Add one more element to test middle with even number of nodes
        System.out.println("Adding another element 30");
        list.insert(30);
        list.display();
        System.out.println("New middle value: " + findMiddle(list));

        // Test findThirdFromEnd method
        System.out.println("\n6. Testing findThirdFromEnd() method");
        list.display();
        System.out.println("Third node from end: " + findThirdFromEnd(list));

        // Test reverse method
        System.out.println("\n7. Testing reverse() method");
        System.out.println("Original list:");
        list.display();
        list.reverse();
        System.out.println("Reversed list:");
        list.display();

        // Test detecting cycle when no cycle exists
        System.out.println("\n8. Testing detectCycle() with no cycle");
        Node cycleNode = detectCycle(list);
        System.out.println(cycleNode == null ? "No cycle detected (correct)" : "Cycle incorrectly detected at node with value: " + cycleNode.getValue());

        // Creating a cycle for testing
        System.out.println("\n9. Creating a cycle and testing detectCycle()");
        System.out.println("Creating cycle: last node points to second node");

        // Get the second node and the last node
        Node secondNode = list.getHead().getNext();
        Node lastNode = list.getHead();
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }

        // Create cycle
        lastNode.setNext(secondNode);
        System.out.println("Cycle created (not displayed to avoid infinite loop)");

        // Test cycle detection
        cycleNode = detectCycle(list);
        System.out.println(cycleNode == null ? "No cycle detected (incorrect)" : "Cycle detected at node with value: " + cycleNode.getValue());

        // Remove cycle
        System.out.println("\n10. Removing cycle and retesting");
        lastNode.setNext(null);
        cycleNode = detectCycle(list);
        System.out.println(cycleNode == null ? "No cycle detected (correct)" : "Cycle incorrectly detected at node with value: " + cycleNode.getValue());

        // Test with empty list
        System.out.println("\n11. Testing operations on empty list");
        LinkedList emptyList = new LinkedList(null);
        System.out.println("Empty list display:");
        emptyList.display();

        System.out.println("Attempting to delete from empty list:");
        emptyList.delete(5);

        System.out.println("Testing findMiddle on empty list:");
        try {
            int middle = findMiddle(emptyList);
            System.out.println("Middle: " + middle);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\n12. Testing operations on single element list");
        emptyList.insert(100);
        emptyList.display();
        System.out.println("Middle value: " + findMiddle(emptyList));

        System.out.println("\n===== Testing Complete =====");
    }
}
