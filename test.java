package prototype_test;

public class test {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static void main(String[] args) {

		String sum = Integer.toString(807);
		ListNode outputF = new ListNode();
		
		outputF.val = Character.getNumericValue(sum.charAt(sum.length() - 1));

		outputF.next = new ListNode ();
		ListNode output = outputF.next;
			for (int i = 1; i < sum.length(); i++) {
				System.out.println(sum.charAt(sum.length() - 1 - i));
				output.val = Character.getNumericValue(sum.charAt(sum.length() - 1- 1));
				
				output.next = new ListNode();
				output = output.next;
			}
			
		}
	}

