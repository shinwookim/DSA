# Data Abstraction and ADT

## Objected Oriented Programming
In Java, most data structures are organized into **classes** which are basically *blueprints* for data. The code below defines a Person class, but there are no instances of that class yet; there are no people because we haven't created them.

```Java
class Person {
	private String name;
	private int age;
	private String address;
	//...
}
```

Classes are useful because they allow us to **encapsulate** the *data and operations* together (typically through *instance data* and *instance methods*). This is particularly useful for restricting access to specific data or implementation details (i.e, **data hiding** through non-public declarations). The author of the class can hide the details of the data type from the user and vary the levels of accessibility by using the `public`, `protected`, and `private` modifiers.

<table>
  <tbody>
    <tr>
      <th></th>
      <th>Class</th>
      <th>Package</th>
      <th>Subclass</th>
      <th>World</th>
    </tr>
    <tr>
      <th><code>public</code></th>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
    </tr>
    <tr>
      <th><code>protected</code></th>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:pink;">n</td>
    </tr>
    <tr>
      <th>No Modifier</th>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:pink;">n</td>
      <td style="text-align:center;background-color:pink;">n</td>
    </tr>
    <tr>
      <th><code>private</code></th>
      <td style="text-align:center;background-color:#b0f4b0;">y</td>
      <td style="text-align:center;background-color:pink;">n</td>
      <td style="text-align:center;background-color:pink;">n</td>
      <td style="text-align:center;background-color:pink;">n</td>
      </tr>
  </tbody>
</table>

However, data hiding also provides certain benefits to the user. Thanks to it, the user does not need to know (or know) the implementation details but can still access the class functionalities and data (provided they know the *nature of the data* and the public method specifications). For instance, a programmer can use the `BigInteger` or `ArrayList` classes without knowing how exactly they work (just that they do work).

This idea of data hiding is called **data abstraction** and is closely related to *abstract data types (ADTs)*. Through **encapsulation**, access to the implementation details of a class can be restricted and through *data abstraction*, a user can use the class effectively without having to know these implementation details. From an implementer's perspective, public members (often public methods) give the interface and functionality of the objects, whereas the private members (often private data) hide the implementation details.

We can use a few keywords to modify the data in the objects. With the `static` keyword, we can define a data that belongs to the class rather than the objects. Data that is defined using this keyword will be shared by all instances (objects) of the class. The `final` keyword provides various functionalities depending on the context. For variables, the `final` keyword means the value cannot change after being initially assigned; for methods, it means they cannot be overridden in a child classes; and for classes, it means they cannot be the parent in an inheritance relationship (no subclasses or child classes can be made from it).

### Instantiating Classes
Once we've defined a class, we can create a new instance of the class by using the `new` keyword and calling the constructor method (defined within the class). These instances are called **objects** and each object has the functionality and data as defined in the class.

### Building New Classes: Composition
Java has many predefined classes in its [**class library**](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/module-summary.html), which contains hundreds of classes where each class is designed for a specific purpose. However, there are many situations where we may need a class that is not already defined. In this case, we must define it ourselves. One technique for doing this is called **Composition (Aggregation)**.

With composition, we can build new classes using components (instance variables) that are from previously-defined classes. We *compose* the class from existent "pieces" and define a "**has-a**" relationship between the new class and the old classes. Consider the following code as an example:

```Java
public class CompoClass {
  private String name;
	private Integer size;
	public CompoClass(String n, int i) {
		name = new String(n);
		size = new Integer(i);
	}
	public void setCharAt(int i, char c) {
		StringBuilder b = new StringBuilder(name);
		b.setCharAt(i, c);
		name = b.toString();
	}
}
```

## Mutable and Immutable Objects
Notice that we are changing the string in a rather convoluted way. This is because we cannot access the inner representation of the String. However, even if we were able to access the string `name` it would prove difficult to change the content of the String. This is because String objects in Java are **immutable**. Immutable objects are instantiated from classes that do not contain mutator methods. The `String` class is a primary example as it cannot be altered after being created. However, other immutable objects exists such as the `Integer` or `Float` classes which have accessor methods but no mutators.

Conversely, objects that have mutator methods (and allow us to change the content of the object) are called **mutable**. For instance, the `StringBuilder` class contains the `append()` method that allows the user to add characters to the current Stringbuilder. Similarly, the `ArrayList` class has an `add()` and `remove()` methods that allow us to manipulate the data that it stores.

As a consequence of being immutable, objects such as strings require much more work for an action that could be done simply with mutation. For immutable objects, modifying them often means creating a new object and reassigning them. For example, consider concatenating `String`s.

```Java
String S1 = "Hello ";
S1 = S1 + "there";
```
Although Java is smart enough to know that we are appending Strings, under the hood, it creates a new String object with "Hello there" as the constructor argument and reassigns it to `S1`. Hence, if this operation is done repeatedly it will cause a lot of overhead, and run-time will be very poor.

Yet, there are issues with being mutable as well. If we add an object to a collection (such as a list), and outside objects/methods that still maintain a reference to the object will still be able to modify the object and possibly destroy the property of the collection. For example, assume we make a subclass of `ArrayList`, called `SortedArrayList`. Now the data must be maintained in order based on the `compareTo()` method. Yet, if we have a `SortedArrayList` of `StringBuilder`s, we can still change one of the `StringBuilder`s (as long we have a reference), and modify it to break the ordering property.

As a potential solution, we can put **copies** of the original objects into the collection (hence any outside references won't be able to change the new copied object). However, we still need to be careful not to mutate the objects within our collection. For example, an some access methods return references to the objects within the collection (which means that the objects within the collections can be mutated again). Hence, to be very safe, we should ensure that our accessor methods only return copies of the objects rather than references to the original.

## Making Copies
But how do we make copies? A typical Java object can be copied using a **copy constructor** or a **`clone()`** method. The `clone()` method is defined in class `Object` meaning it will work for all Java classes. For example, Java arrays and some other classes already implement a `clone()` method for us to use. However, for classes we defined, we need to ensure that they work properly. Hence we need to **override** `.clone()` in our own classes.

### Levels of Copying
If we copy an object, what do we do about any references within that object? 

In a **shallow copy**, we simply assign those references to the new object. Thus, both copies of the object have references to the same "nested" objects.

In a **deep copy**, however, all nested objects must also be copied meaning that each copies of the object have references to different nested objects.
```Java
public class SBArray
{
	private StringBuilder [] A;
	private int size;
	// shallow copy
	public SBArray(SBArray old)
	{
		A = old.A;
		size = old.size;
	}
	// deeper copy
	public SBArray(SBArray old)
	{
		A = new StringBuilder[
				old.A.length];
		size = old.size
		for (int i=0; i<size; i++)
			A[i] = old.A[i];
	}
}
```

| Shallow Copy                                                     | Deeper Copy                                            | Deep Copy                                                  |
| ---------------------------------------------------------------- | ------------------------------------------------------ | ---------------------------------------------------------- |
| Copy references from old object to new object.                   | We make a copy of the array for the new object         | We make a copy of the array for the new object             |
| Objects within the copied object are shared by original and copy | But we do not copy the StringBuilders stored within it | We also copy all of the StringBuilders stored in the array |
|                                                                  | Original and copy are still "connected" at some point  | Original and copy are completely separated from each other |

Generally speaking, (true) deep copying is more difficult than shallow copying. This is because for a deep copy, we need to follow all references in the original and make copies for the copy which could be several levels deep.

For example, a object has a reference to front node. A shallow copy would only copy this single reference, but, a deep copy would have to traverse the entire list, copying each node, and copying the data in each node, and so on.... In fact, it's impossible to say that a copy is truly deep unless all copies made are deep.

### Building New Classes: Inheritance
Besides, composition, another technique to building classes is called **inheritance**. With inheritance, we build a new class (subclass) by extending a previously-defined class (superclass).

Note that the subclass has all of the properties(data and methods) defined in the superclass. Inheritance defines an **is-a** relationship between subclass and superclass. That is, a subclass **is a** superclass, and subclass objects can be assigned to superclass variables. However, the opposite is not true. That is, as superclass **IS NOT** a subclass and superclass objects cannot be assigned to subclass variables.
```Java
// Assume SubFoo is a subclass of Foo – consider the
// statements below
Foo f1;
SubFoo s1;
f1 = new Foo();  // obviously fine
f1 = new SubFoo();  // fine, but now we
        // only have access to the public methods and
        // variables initially defined in class Foo()
f1.foomethod();  // fine
f1.subfoomethod();  // illegal – method does not exist
((SubFoo)f1).subfoomethod(); // fine, since now ref.
		    // has been cast to the actual class
s1 = new SubFoo();
s1.subfoomethod();
s1.foomethod();
s1 = new Foo();  // illegal – isA is one way
```

#### Polymorphism
**Polymorphism** allows superclass and subclass objects to be accessed in a regular, consistent way. For example, an array or collection of **superclass references** can be used to access a **mixture of superclass and subclass objects**. So, if a method is defined in both the superclass and subclass (with identical signatures), the **version corresponding to each class will be used** in a call from the array. The idea is that the methods are similar in nature but the redefinition in the subclass gears the method more specifically to the data / properties of the subclass.

For example, consider the class `Animal` which describes a large number of objects. Let's say each Animal can `move()`. Without more knowledge of the specific animal, we don't really know how the animal would move. But now, consider a subclass of `Animal`, say `Bird`. We can redefine (override) the `move()` method to reflect the specific details of a Bird such as, *it will fly.*

```Java
public class Animal
{
	// omitted decls
	public void move()
	{
		System.out.println("I move");
	}
}

public class Bird extends Animal
{
	// omitted decls
	public void move()
	{
		System.out.println("I fly");
	}
}
```

Generally, each subclass would override the `move()` method in its own way.
```Java
Animal [] A = new Animal[3];
A[0] = new Bird();
A[1] = new Person();
A[2] = new Fish();
for (int i = 0; i < A.length; i++)
		A[i].move();
```
Notice that each call is **syntactically identical**. Their reference and method spec(s) are the same; but the code is executed is based on type of object being stored Polymorphism is implemented utilizing two important ideas: **(1) Method Overriding** and **(2) Dynamic (or late) Binding**

---


---


When a superclass's method is redefined in the subclass with an **identical method signature**, this is called **method overriding**. Since the signatures are identical, rather than overloading the method (ad hoc polymorphism), it is instead **overriding the method**.
- For a subclass object, the definition in the subclass _replaces_ the version in the superclass, even if a superclass reference is used to access the object
- Superclass version can still be accessed via the `super` reference

#### Dynamic (or late) Binding
Dynamic binding means that the code executed for a method call is associated with the call during **run-time**. The actual method executed is determined by the **type of the object**, not the type of the reference.


Polymorphism is very useful if we want to access collections of mixed data types consistently. For example, a collection of different graphical figures could call each `draw()` method consistently, but which would be drawn differently.

### Abstract Classes
Sometimes in a class hierarchy, a class may be defined simply to give cohesion to its subclasses. *I.e.,* No objects of that class will ever be defined, but instance data and methods will still be inherited by all subclasses This is an **abstract class**.
- Keyword `abstract` used in declaration
- One or more methods may be declared to be abstract and are thus not implemented
- No objects may be instantiated

Subclasses of an abstract class must implement all abstract methods, or they too must be declared to be abstract.

#### Advantages of an Abstract Class
- We can still use superclass reference to access all subclass objects in polymorphic way.
	- If a method does not make sense or is not fully realizable in the superclass, we declare it as abstract.
		For example, the `move()` method in `Animal` may be better as an abstract method. In this case `Animal` would need to be an abstract class.
- We can still define any common data and operations in the superclass, which would be inherited by the subclasses.
- Helps to organize class hierarchy

### Interfaces
Unlike some programming langauges, Java allows _only_ **single inheritance**. That is, one class cannot have more than one parent. Java language developers chose to offer only single inheritance for two basic reasons:
	1. simplifying the implementation of the compiler/interpreter
	2. it's easier for programmers to understand what is happening when there is only single inheritance
   
However, it is sometimes useful to access an object through more than one type of superclass references. Often, when a programmer wants multiple inheritance, they want it for polymorphism (i.e. they want a class to be able to look like two different other things). Java offers a way to do this with **interfaces**.

An **interface** is a named set of methods (i.e. method headers, but no bodies). Basically, an interface is an abstract class without any instance data (although there are differences between abstract classes and interfaces).

In interfaces:
1. Static *constants* are allowed
2. Default methods are allowed
3. Static methods are allowed
4. **No instance data** is allowed
5. Regular instance methods have **no bodies**
6. Interface itself **cannot be instantiated**
  
Any Java class can implement an interface (no matter what its inheritance). In fact, any Java class **can implement multiple interfaces**. To implement an interface, a class must declare so in the class header and implement all methods in the interface.

For example, consider the following interfaces:
```Java
public interface Laughable
{
	public void laugh();
}
public interface Booable
{
	public void boo();
}
```

Note that any Java class can implement `Laughable` by implementing the method `laugh()`, implement `Booable` by implementing the method `boo()`.
```Java
public class Comedian implements Laughable, Booable
{
	// various methods here (constructor, etc.)
	public void laugh()
	{
		System.out.println(“Ha ha ha”);
	}
	public void boo()
	{
		System.out.println(“You stink!”);
	}
}
```

All of the polymorphism behavior also applies to interfaces. The interface acts as a superclass and the implementing classes are like subclasses to it. An interface reference variable **can be used to reference any object that implements that interface** and **only interface methods are accessible through that interface reference.**

```Java
Laughable [] funny = new Laughable[3];
funny[0] = new Comedian();
funny[1] = new SitCom(); // implements Laughable
funny[2] = new Clown();  // implements Laughable
for (int i = 0; i < funny.length; i++)
	funny[i].laugh();
```
