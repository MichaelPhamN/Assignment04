
public class DoublyLinkedListTest {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		int n = 40000;
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		
		System.out.println(list.get(39999));
		System.out.println(list.size());
		list.add(40001);
		list.add(40002);
		list.add(40003);
		list.add(40004);
		System.out.println(list.get(40000));
		System.out.println(list.size());
		
//		{
//			int i = 0;
//			for (int s : list) {
//				if (i != s) {
//					throw new RuntimeException("Oops");
//				}
//				i++;
//			}
//		}
//		for (int i = 0; i < n-1; i++) {
//			// If we keep removing the second thing in the list,
//			// the second thing will always be i+1
//			if (i+1 != list.remove(1)) {
//				throw new RuntimeException("Oops2");			
//			}
//		}
//		list.remove(0); // Remove very last thing
//		list.add(-1);
//		System.out.println(list.get(0));
	}

}
