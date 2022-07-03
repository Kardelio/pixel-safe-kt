package pixel.safe.kt

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Decrypter {

    fun crypt(inFile: File, code: String): String {
        println("DECRYPT")
        println("${inFile.absolutePath}")
        println("${code}")

        val bfImg: BufferedImage = ImageIO.read(inFile)

        val width = bfImg.width
        val height = bfImg.height
        var messageOut = ""
        var counter = 0

        var currentCodeCount = 0 // keeps track of number for that specifc count of individaul code number
        var codeIterator = 0 //keeps track of number in position in code
        var standardCodeIterator = 0 //keeps track of number in position in code

        for (i in 0 until height) {
            for (j in 0 until width) {
                val clr = bfImg.getRGB(j, i)
                val red = clr and 0x00ff0000 shr 16
                val green = clr and 0x0000ff00 shr 8
                val blue = clr and 0x000000ff

                if (currentCodeCount > (code[codeIterator].toString().toInt() - 1)) {
                    currentCodeCount = 1
                    codeIterator++
                    standardCodeIterator++
                    if (codeIterator > (code.length - 1)) {
                        codeIterator = 0
                    }
                    if (standardCodeIterator > (STANDARD_CODE.length - 1)) {
                        standardCodeIterator = 0
                    }
                } else {
                    currentCodeCount++
                }
//                println("======")
//                println("----> ${code[codeIterator].toString().toInt()}")
//                println("+++> ${STANDARD_CODE[standardCodeIterator]}")

                when (STANDARD_CODE[standardCodeIterator]) {
                    'R' -> {
                        messageOut += red.toChar()
                    }
                    'G' -> {
                        messageOut += green.toChar()
                    }
                    'B' -> {
                        messageOut += blue.toChar()
                    }
                }
                counter++
            }
        }
        return messageOut
    }
}