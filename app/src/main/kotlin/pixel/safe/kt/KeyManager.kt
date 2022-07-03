package pixel.safe.kt

import java.io.File
import java.util.*

enum class ColorCode(val code: String) {
    RED("R"),
    GREEN("G"),
    BLUE("B")
}

class KeyManager {
    companion object {
        private const val KEY_FILE_NAME = ".pixel-safe-key"
        private val FILE_NAME = "${System.getProperty("user.home")}${File.separator}${KEY_FILE_NAME}"
        private const val MAX_KEY_SIZE = 1000

        fun checkIfKeyFileExists(): Boolean {
            return FileManager.checkIfFileExists(FILE_NAME)
        }

        fun generateKeyFile() {
            println("Generating Key File: ${FILE_NAME}")
            val random = Random()
            var outSequence = ""
            for (i in 0..MAX_KEY_SIZE) {
                val r = random.nextInt(ColorCode.values().size)
                outSequence += ColorCode.values()[r].code
            }
            FileManager.writeStringToFile(
                outSequence,
                FILE_NAME
            )
        }
    }
}