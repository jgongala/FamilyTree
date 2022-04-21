public class FamilyTree {
	
	private int uniqueIdentifier = 1;
	
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
	
	private FamilyTreeNode ancestor;
	
	public FamilyTree(String ancestorName, String partnerName) {
		this.ancestor = new FamilyTreeNode();
		this.ancestor.name = ancestorName;
		
		FamilyTreeNode partner = new FamilyTreeNode();
		partner.name = partnerName;
		
		partner.partner = this.ancestor;
		this.ancestor.partner = partner;
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
		
		familyDetails += getFamilyDetails(this.ancestor);
		familyDetails += getFamilyDetails(this.ancestor.partner);
		
		return familyDetails;
	}
	
	private String getFamilyDetails(FamilyTreeNode familyMemberNode) {
		String familyDetails = new String();
		
		familyDetails += familyMemberNode.name + "(identifier " + familyMemberNode.identifier + ") partner " + familyMemberNode.partner.name + "(identifier " + familyMemberNode.partner.identifier + ")\n";
		FamilyTreeNode familyMember = familyMemberNode.firstChild;
		if (familyMember == null) {
			familyDetails += " has no children\n";
		} else {
			while (familyMember != null) {
				familyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ")\n";
				familyMember = familyMember.nextSibling;
			}
		}
		
		return familyDetails;
	}
}