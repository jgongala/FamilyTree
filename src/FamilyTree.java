public class FamilyTree {
	
	private int uniqueIdentifier = 1;
	private FamilyTreeNode ancestor;
	
	//TODO adding all members for list easy search
	
	private class FamilyTreeNode {
		private int identifier;
		private String name;
		private FamilyTreeNode partner;
		private FamilyTreeNode firstChild;
		private FamilyTreeNode nextSibling;
		
		public FamilyTreeNode() {
			this.identifier = uniqueIdentifier;
			uniqueIdentifier++;
		}
	}
	
	public class UniqueNameConstraintException extends Exception {}
	public class FamilyMemberNotFoundException extends Exception {}

	
	
	
	public FamilyTree(String ancestorName) {
		this.ancestor = new FamilyTreeNode();
		this.ancestor.name = ancestorName;
	}
	
	public void addPartner (String partnerName, int familyMemberId) throws FamilyMemberNotFoundException {
		FamilyTreeNode familyMember = findFamilyMember(familyMemberId);
		
		if (familyMember == null) {
			throw new FamilyMemberNotFoundException();
		} else {
			FamilyTreeNode partner = new FamilyTreeNode();
			partner.name = partnerName;
			partner.partner = familyMember;
			familyMember.partner = partner;
		}	
	}
	
	public void addChild (String childName) throws UniqueNameConstraintException {
		FamilyTreeNode child = new FamilyTreeNode();
		child.name = childName;
		
		if (this.ancestor.firstChild == null) {
			this.ancestor.firstChild = child;
			this.ancestor.partner.firstChild = child;
		} else {
			FamilyTreeNode next = this.ancestor.firstChild;
			
			//Checks first sibling name constraint
			if(next.name.equalsIgnoreCase(childName)) {
				throw new UniqueNameConstraintException();
			}
			while (next.nextSibling != null) {
				//checks next sibling's name constraint
				if(next.nextSibling.name.equalsIgnoreCase(childName)) {
					throw new UniqueNameConstraintException();
				}
				next= next.nextSibling;
			}
			next.nextSibling = child;
		}
	}
	
	public String toString() {
		String familyDetails = new String();
		
		familyDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ") partner " + this.ancestor.partner.name + "(identifier " + this.ancestor.partner.identifier + ")\n";
		FamilyTreeNode familyMember = this.ancestor.firstChild;
		if (familyMember == null) {
			familyDetails += " has no children\n";
		} else {
			// TODO
			while (familyMember != null) {
				familyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ")\n";
				familyMember = familyMember.nextSibling;
			}
		}
		
		return familyDetails;
	}

	
	public FamilyTreeNode findFamilyMember (int id) throws FamilyMemberNotFoundException {
		
		//TODO
		throw new FamilyMemberNotFoundException();

	}
	
}