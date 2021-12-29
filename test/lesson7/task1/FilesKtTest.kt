package lesson7.task1

import lesson6.task1.plusMinus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FilesKtTest {


    @Test
    fun myFunw() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { myFunw(listOf("Ма Швор"), listOf("")) }
        assertEquals(
            listOf("Мария Иванова"), myFunw(
                listOf(
                    "Мария Иванова - Фикика 5, Химия 4, Английский 3",
                    "Данил Иванов - Фикика 3, Химия 4, Английский 5"
                ), listOf("Английский", "Музыка")
            )
        )
        assertEquals(
            listOf("Данил Иванов"), myFunw(
                listOf(
                    "Мария Иванова - Фикика 5, Химия 4, Английский 3, Музыка 3",
                    "Данил Иванов - Фикика 4, Химия 4, Английский 5"
                ), listOf("Английский", "Музыка")
            )
        )
    }

    @Test
    fun shashki() {
        assertEquals(
            "eeeeeeee" + "\n"
                    + "eeeeeeee" + "\n"
                    + "eeeeeeee" + "\n"
                    + "eeeeeeee" + "\n"
                    + "eeeeeBee" + "\n"
                    + "eeeeeeee" + "\n"
                    + "eeeeeeee" + "\n"
                    + "eeeeeeee",

            shashki(
                "eeeeeeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeeB" + "\n"
                        + "eeeeeeWe" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeee", "G6-D3"
            )
        )
        assertThrows(
            IllegalArgumentException::
            class.java
        ) {
            shashki(
                "eeeeeeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeWe" + "\n"
                        + "eeeeeBee" + "\n"
                        + "eeeeBeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeee" + "\n"
                        + "eeeeeeee", "G6-D3"
            )
        }
    }

    @Test
    fun robot() {
        assertEquals("по горизонтали(x): 1, по вертикали(y): 9", robot("input/robot.txt", "dldrdllldlddrrurrdddlllllluu"))
        assertEquals("по горизонтали(x): 3, по вертикали(y): 4", robot("input/robot1.txt", "urddllurruurddd"))
        Assertions.assertThrows(IllegalArgumentException::class.java) { robot("input/robot.txt", "dldzrdllldXlddrrku")}
        Assertions.assertThrows(IllegalArgumentException::class.java) { robot("input/robot2.txt", "dldrdllldlddrrurrdddlllllluu")}
        assertEquals("-1", robot("input/robot3.txt", "urddllurruurddd"))
    }

}
