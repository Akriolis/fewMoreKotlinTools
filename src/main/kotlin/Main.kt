// more interesting stuff in Kotlin

//1.Packages and imports
// your project can contain multiple packages
// and each package can have multiple source files
// each source file can only have one package declaration

package myPackage
import myPackage.Duck
import myPackage.* // imports an entire package
import java.lang.Exception
import myPackage.Duck as Duck2 // if you have a class name conflict

//2. Visibility modifiers
// let you set the visibility of any code that you create
// public, private, protected and internal
// public - makes the declaration visible everywhere (default)
// private - makes the declaration visible only (!) inside its source code
// protected - makes the declaration visible only inside its source code and in its subclasses
// internal - makes the declaration visible only inside the same module

class Duck3{
    private fun doStuff(){

    }
}

open class Parent{
    var a = 1
    private var b = 2
    protected open var c = 3 // if you override a protected member,
    // the subclass version of that member will also be protected by default
    internal var d = 4
}

class Child: Parent(){
    override var c = 6

}

class MyClass private constructor(x: Int) // by "private constructor" keywords you make its constructor private

// 3. Enum classes
// lets you create a set of values that represent the only valid values for a variable

//enum class BandMember { JERRY, BOBBY, PHIL }
// each value in a enum class is a constant
// an enum class can have a constructor
enum class BandMember(val instrument: String){
    Jerry ("lead guitar"){
        override fun sings() = "plaintively"

                         },
    Bobby ("rhythm guitar"){
        override fun sings() = "hoarsely"

                           },
    Phil ("bass"); // ; needs for separate properties from functions
    // methods can be added to enum classes too
    open fun sings() = "occasionally"
}

// 4. Sealed classes
sealed class MessageType

class MessageSuccess (var msg: String): MessageType()

class MessageFailure (var msg: String, var e: Exception): MessageType()

// 5. Nested and inner classes
// a nested class is a class that's defined inside another class

class Outer{
    val x = "This is in the Outer class"
    class Nested {
        val y = "This is in the Nested class"
        fun myFun() = "This is the Nested function"

    }
}
// a nested class in Kotlin is like a static nested class in Java
// a nested class doesn't have access to an instance of the outer class
// however, it can do the inner class

class Outer2{
    val x = "This is in the Outer class"
    val myInner = Inner()

    inner class Inner {
        val y = "This is in the Inner class"
        fun myFun() = "This is the Inner function"
        fun getX() = "The value of x is: $x"

    }
}
// the inner class can use the outer's variable and vice versa
// the inner class is always tied to a specific instance of the outer class
// this means that you can't create an inner object without first creating an outer object

// 6. Object declarations and expressions
// in case if you want to make only a single instance of a given type
// for any purposes, you can use object keyword to make an object declaration
// an object declaration defines a class declaration and creates an instance of it in a single statement

object DuckManager {
    val allDucks = mutableListOf<Duck>()

    fun herdDucks(){
        println("Chorus of: Quack! Quack Quack!")

    }
}
// an object declaration defines a class and creates an instance of it in a single statement
// an object doesn't have a constructor

// an object declaration could be added to a class

class DuckyDuck{
    object DuckFactory{
        fun create(): Duck = Duck()
    }
}
// it creates a single instance of that type which belongs to the class

// a companion object

class DuckyDuck2{
    companion object{
        fun create(): Duck = Duck()
    }
}
//same as class object but without the object's name
// a companion object can be used as the Kotlin equivalent to static methods in Java
// to get a reference you can use Companion keyword
// any functions you add to a companion object are shared by all class instances

// an object expression is an expression that creates an anonymous object with no predefined type


fun main(){
    val duck = myPackage.Duck() // this is the fully qualified name
    val myDuck = Duck() // possible due to myPackage.Duck

    var selectedBandMember: BandMember
    selectedBandMember = BandMember.Jerry
    println(selectedBandMember.instrument)
    println(selectedBandMember.sings())
    // each enum constant exists as a single instance of that enum class

    val messageSuccess = MessageSuccess ("Yay!")
    val messageSuccess2 = MessageSuccess ("It worked!")
    val messageFailure = MessageFailure ("Boo!", Exception("Gone wrong!"))

    var myMessageType: MessageType = messageSuccess2
    val myMessage = when (myMessageType){
        is MessageSuccess -> myMessageType.msg
        is MessageFailure -> myMessageType.msg + "" + myMessageType.e.message
        else -> {}
    }
    println(myMessage)

    val nested = Outer.Nested() // an instance of Nested
    println(nested.y)
    println(nested.myFun())

    val inner = Outer2().Inner()
    println(inner.y)
    println(inner.myFun())
    println(inner.getX())

    val inner2 = Outer2().myInner
    println(inner2.y)

    DuckManager.herdDucks()

    val newDuck = DuckyDuck.DuckFactory.create()
    println(newDuck)

    val newDuck2 = DuckyDuck2.create()

    val x = DuckyDuck2.Companion

    val startingPoint = object{
        val x = 0
        val y = 0
    }

    println("starting point is ${startingPoint.x} and ${startingPoint.y}")
    
}