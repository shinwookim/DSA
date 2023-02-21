# Memory

Let us first talk about some consequences with using the Java programming language, mainly how Java treats *References, Pointers, and Memory*. Recall that Java has two kinds of variables: **primitive** and **reference**. Primitive types are the basic building block data of the language and include the `int`, `double`, `boolean`, and `char` (among others) where as Reference types are variable that _refer_ to objects. As we will be programming primarily in Java, it is important know how Java treats each type of variable.


|Variable Type|**Primitive Type**|**Reference Type**|
|------------:|------------------|------------------|
|How do we define it?|`int i;`|`String s;`|
|How do we initialize it?|`i = 445;`|`s = new String("Hello!");`|
|What does it store?| The actual underlying type is stored at a location in memory | The address of an object is stored at a location in memory|
|How do we copy it?| `int j = i;` copies the value at `i` to `j`.| `String t = s;` copies the reference of `s` to `t` and does not directly affect the object. <br> `String u = new String(s)` creates a new object whose contents are identical to `s`|
|How do we compare it?| `if (i == j)` compares the two values| `if (s == t)` returns true because they both have same references. However, `if (s == u)` returns false since `s` and `u` have different references (even though their value is the same).| 

One important consequence of having two types is that operators can only operate properly on primitive types. If we were to use an operator (such as `==`) on a reference type, it will not work as expected. This is because the operators operate directly on the references (comparing the memory address held by the reference variables) and not the objects the variables are referring to.

Instead, to compare the actual objects at the reference, we need to use named methods that compares the content (since Java, unlike C++ or Python, does not support operator overloading). Typically, these methods are called `.equals()` or `.comparesTo()` and are predefined in most library classes, but can also be redefined in our own classes as needed.

## References vs Pointers
Many lower level programming languages, such as C, support **pointer variables** which stores memory addresses and allow us to directly access data in the RAM. 

![Pointers](assets/Pointers.png)

Here, variables `x` and `y` are pointers which store memory locations. The objects themselves live at the memory locations the pointer points to. Note, since these variables holds an address, operations on the variables means operations on the addresses (and not the data at the address). For instance, if we run `x = y;`, we would have both `x` and `y` pointing to the same memory location (and hence the same object). 

![aliasing.png](assets/aliasing.png)

Now, `x` is no longer pointing to the original memory address, but at the same address `y` is pointing to. When, two (or more) pointers point to the same object, this is called **aliasing**.

To gain access to the object that `x` points to, we can **dereference a pointer** (explicitly tell the computer to go to that address). In C, this is done with the `*` operator. Hence, if we really wanted to have two separate objects, but want one of them to be a copy of the other, we could do the following (assuming the data is set up properly):

``` C
*x = *y;
```

Now, `x` is points to its original location (as does `y`), but the values at the position `x` is pointing to is changing:

![dereferencing a pointer](assets/dereferencing%20a%20pointer.png)

Java, by contrast, does not have any pointers. Instead, it only has **references** which behave similarly to pointers but with more restrictions. For instance, we cannot explicitly dereference variables (thus there is no dereference operator). Instead, dereferencing is handled implicitly and we can access data and methods using the "dot" operator. For example, to copy an an entire object we would use a method such as a `clone()` method (also called a **copy constructor**). 

Yet, even without pointers, **aliasing** can still occur in Java.

```Java
StringBuilder S1 = new StringBuilder("Hello");
StringBuilder S2 = S1;
S1.append(" There");
S2.append(" CS 0445 Students");
System.out.println(S1.toString());
// Output: Hello There CS 0445 Students
```
Here, we mutated using two different variables but both mutated the same object. Hence, you must be aware of when you want a new object or a reference to an old one.

## Java Memory Use
Java is a high-level language in that memory allocation is handled dynamically and implicitly. Specifically, memory is allocated using the `new` operator and once allocated, objects exist for an indefinite period of time, as long as there is an active reference to the object. When there are no references to objects, they are no longer accessible in the program, and marked for **Garbage Collection**.

The Java **garbage collector** is a process that runs in the background during program execution. When the amount of available memory runs low, the garbage collector reclaims objects that have been marked for collection. A fairly sophisticated algorithm is used to determine which objects can be garbage collected but due to its long run-time, if we have plenty of memory is available, the garbage collector will not run.
