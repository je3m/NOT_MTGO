package devops.hw1.core;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import back_end.Card;
import back_end.MTGDuelDecks;


public class RandomTester {

	/**
	 * Creates a new random instance of the given type.
	 * 
	 * @param type - The class/primitive type to randomly instantiate. 
	 * @return     - The instance of the object.
	 */
	static Random r = new Random();
	public static Object generateTestValue(Class type) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(type == int.class){
			return r.nextInt();
		} else if (type == char.class){
			return (char)r.nextInt((int)(Math.pow(2,7)-1));
		} else if (type == String.class) {
			char[] text = new char[r.nextInt(200)];
		    for (int i = 0; i < text.length; i++)
		    {
		        text[i] = (char)r.nextInt((int)(Math.pow(2,7)-1));
		    }
		    return new String(text);
		} else if (type == Boolean.class || type == boolean.class) {
			if(r.nextInt(2) == 0){
				return true;
			} else {
				return false;
			}
		} else if (type.isEnum()) {
			Object[] enums = type.getEnumConstants();
			return enums[r.nextInt(enums.length)];
		} else if (type == ArrayList.class){
			ArrayList<String> arr = new ArrayList<String>();
			int numAbility = r.nextInt(10);
			for(int i = 0; i < numAbility; i++){
				arr.add(generateTestValue(String.class).toString());
			}
			return arr;
			//Included to run test on functions other than card to give them valid cards
		} else if (type == back_end.Card.class) {
			return new Card("Forest", "", "", "Basic Land- Forest", "T:G", 
					new ArrayList<String>(), 0, 0, MTGDuelDecks.FOREST_PATH, false);
		} else {
			System.out.println(type);
			return generateRandomInstance(type);
		}
	}
	
	/**
	 * Returns a heterogeneous array of random values. 
	 * 
	 * @param p - The types to generate.
	 * @return  - The randomly instantiated values.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object[] generateTestValues(Class[] p) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object[] arr = new Object[p.length];
		for(int i = 0; i < arr.length; i++){
			arr[i] = generateTestValue(p[i]);
		}
		return arr;
	}
	
	/**
	 * Given a method, returns the parameters appropriate for calling the given method.
	 * 
	 * @param m - The method to return.
	 * @return  - The parameters to pass to the method.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object[] generateTestValuesForMethod(Method m) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] params = m.getParameterTypes();
		return generateTestValues(params);
	}
	
	/**
	 * Given a constructor, returns the parameters needed to call it.
	 * 
	 * @param m  
	 * @return   
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object[] generateTestValuesForMethod(Constructor m) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] params = m.getParameterTypes();
		return generateTestValues(params);
	}
	
	/**
	 * Generates a randomly constructed instance of the specified object.
	 * 
	 * @param obj - the type to construct.
	 * @return    - the newly constructed instance. 
	 * @throws IllegalAccessException - if this Constructor object enforces Java language access control and the underlying constructor is inaccessible. 
	 * @throws IllegalArgumentException - if the number of actual and formal parameters differ; if an unwrapping conversion for primitive arguments fails; or if, after possible unwrapping, a parameter value cannot be converted to the corresponding formal parameter type by a method invocation conversion; if this constructor pertains to an enum type. 
	 * @throws InstantiationException - if the class that declares the underlying constructor represents an abstract class. 
	 * @throws InvocationTargetException - if the underlying constructor throws an exception.
	 */
	public static Object generateRandomInstance(Class obj) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		boolean generated = false;
		Constructor[] cs = obj.getConstructors();
		Constructor constructor = cs[r.nextInt(cs.length)];
		Object[] params = generateTestValuesForMethod(constructor);
		return constructor.newInstance(params);
	}
	
	/**
	 * 
	 * @param className - the fully qualified name of the class to instantiate. 
	 * @param nMethodCalls - the number of random method calls to make.
	 * @throws ClassNotFoundException - if the named class does not exist.
	 * @throws IllegalAccessException - if this Constructor object enforces Java language access control and the underlying constructor is inaccessible. 
	 * @throws IllegalArgumentException - if the number of actual and formal parameters differ; if an unwrapping conversion for primitive arguments fails; or if, after possible unwrapping, a parameter value cannot be converted to the corresponding formal parameter type by a method invocation conversion; if this constructor pertains to an enum type. 
	 * @throws InstantiationException - if the class that declares the underlying constructor represents an abstract class. 
	 * @throws InvocationTargetException - if the underlying constructor throws an exception.
	 */
	public static void testClass(String className, int nMethodCalls) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 1. Look up class by name
		Class<?> testedClass = Class.forName(className);
		// 2. Create a random instance of the classs
		Object instanceClass = generateRandomInstance(testedClass);
		// 3. Look up the declared methods
		Method[] declaredMethods = testedClass.getDeclaredMethods();
		
		// 4. Generate nMethodCalls random function calls on the instance.
		// get random method
			// generate random parameters
			// log method name and parameters
			// call the method with the passed parameters
		for(int i = 0; i < nMethodCalls; i++){
			Method method = declaredMethods[r.nextInt(declaredMethods.length)];
			Object[] params = generateTestValuesForMethod(method);
			System.out.println("Method: " + method);
			System.out.print("Params: ");
			for(int j = 0; j < params.length; j++){
				System.out.print(params[j]);
				if(!(j == params.length - 1)){
					System.out.print(", ");
				}
			}
			System.out.println();
			try {
				System.out.println("Return: " + method.invoke(instanceClass, params));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * Randomly tests the specified class.
	 * @param args - arg[0] is the name of the class. arg[1] is how many methods to randomly call.
	 */
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		testClass("back_end.ItemOnStack",100);
	}
}
