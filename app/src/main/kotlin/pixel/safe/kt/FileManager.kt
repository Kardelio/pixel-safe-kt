package pixel.safe.kt

import java.awt.image.BufferedImage
import java.io.*
import java.util.*
import javax.imageio.ImageIO

class FileManager {
    companion object {
        fun writeImageToFile(fileName: String, bufferedImage: BufferedImage) {
            try {
                //should be a png file
                val file = File("${fileName}")
                ImageIO.write(bufferedImage, "png", file)
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
            }
        }

        fun readInTextFile(fileName: String): String {
            val file = getFile(fileName)
            return file.inputStream().readBytes().toString(Charsets.UTF_8)
        }

        fun readFileToCharArray(file: File?): ArrayList<Char>? {
            val arrayOfLetters = ArrayList<Char>()
            var line = 0
            try {
                val fin = FileInputStream(file)
                val isr = InputStreamReader(fin, "UTF-8")
                val br = BufferedReader(isr)
                while (br.read().also { line = it } != -1) {
                    if (line == 10) {
                        arrayOfLetters.add('\n')
                    } else {
                        arrayOfLetters.add(line.toChar())
                    }
                }
            } catch (e: java.lang.Exception) {
                println("EXCEPTION: " + e.localizedMessage)
            }
            return arrayOfLetters
        }

        fun readInImageFile(fileName: String): BufferedImage? {
            return try {
                ImageIO.read(File("${fileName}"))
            } catch (e: IOException) {
                println("Error: ${e.localizedMessage}")
                null
            }
        }

        fun checkIfFileExists(fileName: String): Boolean {
            val file = File(fileName)
            return file.exists()
        }

        fun getFile(fileName: String): File {
            var currentName = fileName
            if (fileName.startsWith("~" + File.separator)) {
                currentName = System.getProperty("user.home") + fileName.substring(1)
            } else if (fileName.startsWith("~")) {
                throw UnsupportedOperationException("Home dir expansion not implemented for explicit usernames")
            }
            return File(currentName)
        }

        fun writeStringToFile(str: String, fileName: String) {
            var writer: BufferedWriter? = null
            try {
                writer = BufferedWriter(FileWriter(fileName))
                writer.write(str)
            } catch (e: IOException) {
                throw e
            } finally {
                try {
                    writer?.close()
                } catch (e: IOException) {
                    println("problem writing to and closing file")
                }
            }
        }


    }
}