import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; 

class TestFamilyTree {

	@Test
	void testExceptionThrownWhenNoPartnerAndChildrenAdded() {
		FamilyTree familyTree = new FamilyTree("James");
		assertThrows(FamilyTree.PartnerNeededException.class, () -> familyTree.addChild("firstChild"));
	}
	
	@Test
	void testAddSinglePartner() {
		FamilyTree familyTree = new FamilyTree("James");
		assertAll(() -> familyTree.addPartner("firstPartner", 1));
	}
	
	@Test
	void testAddMultiplePartners() {
		FamilyTree familyTree = new FamilyTree("James");
		assertAll(() -> familyTree.addPartner("firstPartner", 1));
		assertThrows(FamilyTree.FamilyMemberHasPartnerException.class, () -> familyTree.addPartner("secondPartner", 1));
	}
	
	@Test
	void testAddChild() {
		FamilyTree familyTree = new FamilyTree("James");
		assertAll(() -> familyTree.addPartner("firstPartner", 1));
		assertAll(() -> familyTree.addChild("firstChild"));
		assertAll(() -> familyTree.addChild("secondChild"));
		assertAll(() -> familyTree.addChild("thirdChild"));
	}
	
	@Test
	void testExceptionThrownWhenSiblingWithSameName() {
		FamilyTree familyTree = new FamilyTree("James");
		assertAll(() -> familyTree.addPartner("firstPartner", 1));
		assertAll(() -> familyTree.addChild("firstChild"));
		assertThrows(FamilyTree.UniqueNameConstraintException.class, () -> familyTree.addChild("firstChild"));
	}
}
