import java.util.*;

public class CollectionsJava {
    // List: duplicate object
    // -- maintains the order of data as we enter them
    // ArrayList -- read the object in forward direction, is faster for index access, but slow for insertion/removal in the middle.
    // LinkedList -- read the object in forward and backward direction, is faster for frequent changes at the beginning or middle, but slower for searching.

    // Set : store only unique object
    // -- data is not maintained
    // HashSet -- read the object in forward direction -- it used Hashtable algorithm to store the data, there is no arrangement of elements.
    // LinkedHashSet -- read the object in forward direction, save the insertion order.
    // TreeSet -- store the object in ascending, sorts the elements automatically.

    public static void main(String[] args) {
        arrayList();
        linkedList();
        hashSet();
        linkedHashSet();
        treeSet();
    }

    // ArrayList - Save the insert order, allows duplicates
    public static void arrayList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");

        System.out.println("ArrayList: " + arrayList);
    }

    // LinkedList - Dynamic structure, allows duplicates
    public static void linkedList() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.add("Cat");
        linkedList.add("Elephant");

        System.out.println("LinkedList: " + linkedList);
    }

    // HashSet - No duplicates, no guaranteed order
    public static void hashSet() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Python");
        hashSet.add("Java");
        hashSet.add("C++");
        hashSet.add("Java"); // This element will not be added

        System.out.println("HashSet: " + hashSet);
    }

    // LinkedHashSet - Save the insert order, no duplicates
    public static void linkedHashSet() {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Mercedes");
        linkedHashSet.add("BMW");
        linkedHashSet.add("Audi");

        System.out.println("LinkedHashSet: " + linkedHashSet);
    }

    // TreeSet - Save elements in sorted order and does not allow duplicates
    public static void treeSet() {
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Zebra");
        treeSet.add("Tiger");
        treeSet.add("Lion");

        System.out.println("TreeSet: " + treeSet);
    }
}
