public class FamilyTreeTest {
	public static void main (String[] args) {
		
		String name = Input.getString("Enter ancestor name: ");
		FamilyTree familyTree = new FamilyTree(name);
		
		Integer option;
		
		while (true) {
			while (true) {
				try {
					System.out.println("0: quit");
					System.out.println("1: add child");
					System.out.println("2: display family tree");
					System.out.println("3: add partner");

					option = Input.getInteger("Enter your option: ");
					break;
				} catch (Exception exception){
					System.out.println("invalid entry");
				}
			}
			switch (option) {
				case 0:
					if (Input.getString("Are you sure? (Y/N): ").equalsIgnoreCase("y")) {
						System.out.println("Confirmed. Quitting program.");
						System.exit(0);
					} else {
						break;
					}
				case 1:
					String childName = Input.getString("Enter child's name: ");
					try {
						familyTree.addChild(childName);
					} catch (FamilyTree.UniqueNameConstraintException e) {
						System.out.println("Sibling with this name already exists.");
					}
					break;
				case 2:
					System.out.println("1: display whole family tree");
					System.out.println("2: display specific family member");

					option = Input.getInteger("Enter your option: ");
					switch (option) {
						case 1:
							System.out.println(familyTree);
							break;
						case 2: 
							// TODO
							System.out.println("specific member");
							break;
						default:
							System.out.println("Please enter a valid option from the menu");
						}
					
					break;
				case 3:
					//TODO
					System.out.println("Adding partner");
					break;
				default:
					System.out.println("Please enter a valid option from the menu");
			}
		}
	}
}
