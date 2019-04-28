package com.sergiocruz.recyclerviewlisteners

import org.junit.Test

class MyKotlinTests {


    @Test
    fun kotlimTest() {

        val numbers = listOf(1, -2, 3, -4, 5, -6)
        val evenOdd = numbers.partition { it % 2 == 0 }
        val (positives, negatives) = numbers.partition { it > 0 }

        println(positives)
        println(negatives)

        val (list1: List<Person?>, list2: List<Person?>) = people.partition {
            it?.name?.contains("e") ?: false
        }
        println(list1.toString())
        println(list2.toString())

        println(list1.firstOrNull { it?.id == "11" })

        val notnull: List<String> = people.mapNotNull { it?.name + " !noting! " }
        println(people)

        val mapear: List<Person> = people.map { Person(it?.id, it?.name + "x", it?.email) }
        println(mapear)


        val numbers2 = listOf(1, -2, 3, -4, 5, -6)     // 1

        val doubled = numbers2.map { x -> x * 2 }      // 2

        val tripled = numbers2.map { it * 3 }          // 3
        println(doubled)
        println(tripled)

    }

    @Test
    fun kotlinTest2() {

        val numbers = listOf(1, -2, 3, -4, 5, -6)                // 1

        val anyNegative: Boolean = numbers.any { it < 0 }             // 2

        val anyGT6 = numbers.any { it > 6 }

        println(anyNegative)

        val phoneBook: Map<String?, Person> =
            people.associateBy { it.email }                          // 3
        val cityBook: Map<String?, String?> =
            people.associateBy(Person::id, Person::name)           // 4
        val peopleCities: Map<String?, List<String?>> =
            people.groupBy(Person::name, Person::id)            // 5


        println(phoneBook)
        println(cityBook)
        println(peopleCities)


    }

    @Test
    fun kotlinTest3() {

        val maxe: Person? = people.maxBy { it.name?.length ?: 0 }
        println(maxe)

        //public inline fun <T, R> T.let(
        //    block: (T) → R
        //): R
        //Calls the specified function block with this value as its argument and returns its result.
        val see: String? = maxe?.let { it: Person ->
            val t: MyKotlinTests = this
            val tt: Person? = it
            "something"
        }

        //public inline fun <T, R> T.run(
        //    block: T.() → R
        //): R
        //Calls the specified function block with this value as its receiver and returns its result.
        val se: String? = maxe?.run(fun Person.(): String {
            val tt: Person = this
            // val t = it // ERROR
            name
            id
            email
            return "this"
        })

    }

    @Test
    fun kotlinTest4() {
        val jake = Person()
        val jake2: Person = jake
            .apply {
                id = "30"
                name = "Jake"
                email = "Android developer"
            }
            .also {
                println("also $it")
            }

        println(jake)
        println(jake2)

    }

    @Test
    fun kotlinTest4_1() {


        val jake = Person()
        val jake2: Person = jake
            .apply {
                id = "30"
                name = "Jake"
                email = "Android developer"
            }
            .also {
                println("also $it")
            }

        val wit: String? = with(jake, { name })

        println(jake)
        println(jake2)

    }

    data class User(val username: String, val email: String)    // 1

    fun getUser() = User("Mary", "mary@somewhere.com")

    fun main() {
        val user = getUser()
        val (username, email) = user                            // 2
        println(username == user.component1())                  // 3

        val (_: String, emailAddress: String) = getUser()                       // 4

        val map: Map<String, Int> = mapOf("Alice" to 21, "Bob" to 25)
        for ((name: String, age: Int) in map) {                                      // 2
            println("$name is $age years old")
        }

    }

    val people: List<Person> = listOf(
        Person("1", "Elsie", "eponten0@jimdo.com"),
        Person("2", "Annalise", "averty1@jimdo.com"),
        Person("3", "Shaughn", "shecks2@ucla.edu"),
        Person("4", "Garwin", "gdami3@slideshare.net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo.com"),
        Person("6", "Maurene", "mriceards5@webmd.com"),
        Person(null, "Annalise", "averty1@jimdo.com"),
        Person("9", "Shaughn", "shecks2@ucla.edu"),
        Person("10", "Garwin", "gdami3@slideshare.net")
    )

    interface SoundBehavior {                                                          // 1
        fun makeSound()
    }

    class ScreamBehavior(val n:String): SoundBehavior {                                // 2
        override fun makeSound() = println("${n.toUpperCase()} !!!")
    }

    class RockAndRollBehavior(val n:String): SoundBehavior {                           // 2
        override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
    }

    // Tom Araya is the "singer" of Slayer
    class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)                       // 3

    // You should know ;)
    class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)              // 3

    @Test
    fun kotlinTest5() {
        val tomAraya = TomAraya("Trash Metal")
        tomAraya.makeSound()

        val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
        elvisPresley.makeSound()

    }




}