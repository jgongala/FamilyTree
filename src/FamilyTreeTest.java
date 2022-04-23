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
					try {
						try {
							// there is an other way of doing this (if statement, that I would much rather use), 
							// but to follow requirement of the assessment I used exception as required 
							familyTree.ifAncestorHasPartnerThrowException();
							String childName = Input.getString("Enter child's name: ");
							familyTree.addChild(childName);
						} catch (FamilyTree.PartnerNeededException e) {
							System.out.println("Cannot add child to ancestor with no partner");
						}
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
							familyTree.printMembers();
							Integer membersFamilyToDisplay = Input.getInteger("Enter family member identifier that you want display: ");
							try {
								System.out.println(familyTree.familyMemberDetails(membersFamilyToDisplay));
							} catch (FamilyTree.FamilyMemberNotFoundException e) {
								System.out.println("member not found");
							}
							break;
						default:
							System.out.println("Please enter a valid option from the menu");
						}
					
					break;
				case 3:
					System.out.println(familyTree);
					Integer memberIdentifier = Input.getInteger("Enter family member identifier that you want to add partner to: ");
					
					try {
						// there is an other way of doing this (if statement, that I would much rather use), 
						// but to follow requirement of the assessment I used exception as required 
						familyTree.ifHasPartnerThrowException(memberIdentifier);
						if (familyTree.findFamilyMember(memberIdentifier) != null) {
							String partnerName = Input.getString("Partner's name: ");
							familyTree.addPartner(partnerName, memberIdentifier);
						}
					
					} catch (FamilyTree.FamilyMemberNotFoundException e) {
						System.out.println("member not found");
					} catch (FamilyTree.FamilyMemberHasPartnerException e) {
						System.out.println("family member has already a partner");
					}
					break;
				default:
					System.out.println("Please enter a valid option from the menu");
			}
		}
	}
}
