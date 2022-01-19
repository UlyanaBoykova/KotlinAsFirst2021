package lesson7.task1

import lesson6.task1.plusMinus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FilesKtTest {



@Test
    fun robot() {
        assertEquals("по горизонтали(x): 1, по вертикали(y): 9", robot("input/robot.txt", "dldrdllldlddrrurrdddlllllluu"))
        assertEquals("по горизонтали(x): 3, по вертикали(y): 4", robot("input/robot1.txt", "urddllurruurddd"))
        Assertions.assertThrows(IllegalArgumentException::class.java) { robot("input/robot.txt", "dldzrdllldXlddrrku")}
        Assertions.assertThrows(IllegalArgumentException::class.java) { robot("input/robot2.txt", "dldrdllldlddrrurrdddlllllluu")}
        assertEquals("-1", robot("input/robot3.txt", "urddllurruurddd"))
    }

}
