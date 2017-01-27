//jak testowac prywatne metody ?

package MyStackExample;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberStackTests {

	@Test  //podkreslenia niemile w Javie ale trudno
	public void givenNewlyCreatedStack_WhenCountingItems_ThenZeroIsReturned(){
		NumberStack s = new NumberStack(1);

		int count = s.getCount();
		assertEquals(0, count);

	}
	@Test
	public void givenStack_WhenPushingItem_ThenCountIsIncremented(){
		NumberStack s = new NumberStack(1);

		s.push(2);

		assertEquals(1,s.getCount());
	}

	@Test 
	public void givenNonEmptyStack_WhenPushingSecondItem_ThenCountIsTwo(){

		NumberStack s = new NumberStack(2);

		s.push(2);
		s.push(111);

		assertEquals(2, s.getCount());

	}

	@Test  //mozna zrobic expected
	public void givenEmptyStack_WhenPopItem_ThenExceptionisThrown(){
		NumberStack s = new NumberStack(1);

		try{
			s.pop();
			fail("Expected exception"); //jedyny sposob zeby to nie zostalo wywolane to zeby s.pop rzucilo wyjatkiem
		}
		catch (IllegalStateException e){
		}
	}
	
	@Test
	public void givenStackWithSingleElement_WhenPoppingItem_ThenCountIsZero(){
		
		NumberStack s = new NumberStack(1);
		s.push(47);
		
		s.pop();
		
		assertEquals(0, s.getCount());
		
	}
	
	
	@Test
	public void givenStackWithTwoItems_WhenPoppingSingleItem_ThenCountIsOne(){
		
		NumberStack s = new NumberStack(2);
		s.push(5);
		s.push(10);
		
		s.pop();
		
		assertEquals(1, s.getCount());
		
	}
	
	@Test 
	public void givenStackWithItems_WhenPopping_ThenLastItemValueIsReturned(){
		NumberStack s = new NumberStack(2);
		s.push(5);
		s.push(10);
		
		int value = s.pop();
		assertEquals(10, value);
	}
	
	@Test 
	public void givenStackWithTwoItems_WhenPoppingSecondTime_ThenFirstItemValueIsReturned(){
		NumberStack s = new NumberStack(2);
		s.push(5);
		s.push(10);

		int value = s.pop();
		assertEquals(5, value);
		
	}
	
	@Test
	public void givenStackWithNoItems_WhenCallIsEmpty_ReturnTrue(){
		NumberStack s = new NumberStack(1);
		assertTrue(s.isEmpty());
	}
	
	@Test 
	public void givenStackWithItems_WhenCallIsEmpty_ReturnFalse(){
		NumberStack s = new NumberStack(2);
		s.push(5);
		s.push(10);
		
		assertFalse(s.isEmpty());
	}
	
	@Test 
	public void givenStackWithOneItem_AfterPop_IsEmptyReturnTrue(){
		NumberStack s = new NumberStack(1);
		s.push(5);
		s.pop();
		
		assertTrue(s.isEmpty());
	}
	
	@Test 
	public void givenStackWithOneItem_AfterClear_IsEmptyReturnTrue(){
		NumberStack s = new NumberStack(1);
		s.push(5);
		s.clear();
		
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void givenStackWithTwoItems_AfterClear_IsEmptyReturnTrue(){
		NumberStack s = new NumberStack(2);
		s.push(5);
		s.push(-123);
		
		s.clear();
		
		assertTrue(s.isEmpty());
	}


	@Test
	public void givenStackofNCapacity_WhenGetCapacityIsCalled_ThenNisReturned(){
		int n = 8;
		NumberStack s = new NumberStack(n);
		assertEquals(n,s.getCapacity());
	}

	@Test
	public void givenFullStackofGivenCapacity_WhenPutNewItem_ThenExceptionIsThrown(){
		int n = 11;
		NumberStack s = new NumberStack(n);
		for (int i=1;i<=11;++i){
			s.push(i);
		}
		try{
			s.push(-100);
			fail("Expected exception"); //jedyny sposob zeby to nie zostalo wywolane to zeby s.pop rzucilo wyjatkiem
		}
		catch (IllegalStateException e){
		}
	}

	//stos ma byc n elementowy - zrobione
	//gorna granica do dopisania  zeby push sie nie dalo zrobic - zro
	
	//ZADANIE A, mozna enuma wziac albo jakis rejestr (money wartosc trzymana na incie, na floacie mozna miec problemy z sumowaniem prawo lewo bo jest plynacy przecinek)
	//Lepiej przyjac ze ostatnie dwie liczby w int to sa grosze albo cos w tym stylu
	//na doublu jest podobnie dlatego np. w Szwajcarii jest przepis ze pieniadze tylko na incie
	
	//Zadanie B
	
	//Zadanie C do pominiecia
	
	//Zadanie D stos (jak wyzej)
}
