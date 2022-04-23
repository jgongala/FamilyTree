import java.util.LinkedList;

public class FamilyTree {
	
	private int uniqueIdentifier = 1;

	private FamilyTreeNode ancestor;
	private LinkedList <FamilyTreeNode> familyList = new LinkedList();
		
	private class FamilyTreeNode {
		private int identifier;
		private String name;
		private FamilyTreeNode partner;
		private FamilyTreeNode firstChild;
		private FamilyTreeNode nextSibling;
		
		public FamilyTreeNode() {
			this.identifier = uniqueIdentifier;
		}

		public int getIdentifier() {
			return identifier;
		}
		
	}
	
	public class UniqueNameConstraintException extends Exception {}
	public class FamilyMemberNotFoundException extends Exception {}
	public class PartnerNeededException extends Exception{};
	public class FamilyMemberHasPartnerException extends Exception{};
	
	public FamilyTree(String ancestorName) {
		this.ancestor = new FamilyTreeNode();
		this.ancestor.name = ancestorName;
		familyList.add(ancestor);
		uniqueIdentifier++;
	}
	
	public void addPartner (String partnerName, int familyMemberId) throws FamilyMemberNotFoundException, FamilyMemberHasPartnerException {
		FamilyTreeNode familyMember = findFamilyMember(familyMemberId);
		
		if (familyMember == null) {
			throw new FamilyMemberNotFoundException();
		} 
		
		if (familyMember.partner != null) {
			throw new FamilyMemberHasPartnerException();
		} else {
			FamilyTreeNode partner = new FamilyTreeNode();
			partner.name = partnerName;
			partner.partner = familyMember;
			familyMember.partner = partner;
			familyList.add(partner);
			uniqueIdentifier++;
		}	
	}
	
	public void addChild (String childName) throws UniqueNameConstraintException, PartnerNeededException {
		FamilyTreeNode child = new FamilyTreeNode();
		child.name = childName;
		
		if (this.ancestor.partner == null) {
			throw new PartnerNeededException();
		} else {
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
					next = next.nextSibling;
				}
				next.nextSibling = child;
			}
		}
		familyList.add(child);
		uniqueIdentifier++;
	}
	
	public String toString() {
		String familyDetails = new String();
		
		if (this.ancestor.partner == null) {
			familyDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ") has no partner\n";
		} else {
			familyDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ") partner " + this.ancestor.partner.name + "(identifier " + this.ancestor.partner.identifier + ")\n";
		}
		
		FamilyTreeNode familyMember = this.ancestor.firstChild;
		if (familyMember == null) {
			familyDetails += " has no children\n";
		} else {
			// TODO
			while (familyMember != null) {
				if(familyMember.partner == null) {
					familyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ") has no partner\n";
				} else {
					familyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ") partner " + familyMember.partner.name + "(identifier " + familyMember.partner.identifier + ")\n";
					familyDetails += getLegacyDetails(familyMember);
				}
				familyMember = familyMember.nextSibling;
			}
		}
		
		return familyDetails;
	}
	
	private String getLegacyDetails(FamilyTreeNode familyMember) {
		String legacyDetails = new String();
		familyMember = familyMember.firstChild;
		if (familyMember == null) {
			legacyDetails += "  has no children\n";
		} else {
			while (familyMember != null) {
				if(familyMember.partner == null) {
					legacyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ") has no partner\n";
				} else {
					legacyDetails += " " + familyMember.name + "(identifier " + familyMember.identifier + ") partner " + familyMember.partner.name + "(identifier " + familyMember.partner.identifier + ")\n";
					legacyDetails += getLegacyDetails(familyMember);
				}
				familyMember = familyMember.nextSibling;
			}
		}
		return legacyDetails;
	}
	
	public String familyMemberDetails(int id) throws FamilyMemberNotFoundException {
		String familyMemberDetails = new String();
		FamilyTreeNode familyMember = findFamilyMember(id);
		if(familyMember.partner == null) {
			familyMemberDetails += familyMember.name + "(identifier " + familyMember.identifier + ") has no partner\n";
		} else {
			familyMemberDetails += familyMember.name + "(identifier " + familyMember.identifier + ") partner " + familyMember.partner.name + "(identifier " + familyMember.partner.identifier + ")\n";
			familyMemberDetails += getLegacyDetails(familyMember);
		}
		
		return familyMemberDetails;
	}
	
	
	public FamilyTreeNode findFamilyMember (int id) throws FamilyMemberNotFoundException {
		
		for (FamilyTreeNode familyMember : familyList) {
			if (familyMember.getIdentifier() == id) {
				return familyMember;
			}
		}
		throw new FamilyMemberNotFoundException();
	}
	
	public void printMembers() {
		for (FamilyTreeNode member : familyList) {
			System.out.println(member.identifier + ". " + member.name);
		}
	}
	
	public void ifHasPartnerThrowException (int id) throws FamilyMemberNotFoundException, FamilyMemberHasPartnerException {
		FamilyTreeNode member = findFamilyMember(id);
		if (member.partner != null) {
			throw new FamilyMemberHasPartnerException();
		}
	}
	
	public void ifAncestorHasPartnerThrowException() throws PartnerNeededException {
		if (this.ancestor.partner == null) {
			throw new PartnerNeededException();
		}
	}
	
}