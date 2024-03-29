package pixel.safe.kt

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.random.Random

class Encrypter {

    companion object {
        const val MAX_WIDTH = 100
        const val LOW_RANDOM_RANGE = 32
        const val HIGH_RANDOM_RANGE = 127
    }

    fun crypt(fileContents: ArrayList<Char>, code: String, outFile: String = "out.png") {
        var usualableWidth = MAX_WIDTH
        if (fileContents.size < MAX_WIDTH) {
            usualableWidth = fileContents.size
        }

        var requiredHeight = fileContents.size / MAX_WIDTH
        // Handle remainder
        if (requiredHeight < fileContents.size) {
            requiredHeight++
        }
        println("size: ${fileContents.size}")
        println("HEIGHT: ${requiredHeight}")
        println("usualableWidth: ${usualableWidth}")

        val bfImg = BufferedImage(usualableWidth, requiredHeight, BufferedImage.TYPE_INT_RGB)
        val g2d = bfImg.createGraphics()
        var rowCounter = 0
        var colCounter = 0

        var counter = 0
        var currentCodeCount = 0 // keeps track of number for that specifc count of individaul code number
        var codeIterator = 0 //keeps track of number in position in code
        var standardCodeIterator = 0 //keeps track of number in position in code
        //923456
        fileContents.forEach {

            val randomA = Random.nextInt(HIGH_RANDOM_RANGE - LOW_RANDOM_RANGE) + LOW_RANDOM_RANGE
            val randomB = Random.nextInt(HIGH_RANDOM_RANGE - LOW_RANDOM_RANGE) + LOW_RANDOM_RANGE
            val a = Integer.toHexString(randomA)
            val b = Integer.toHexString(randomB)

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

            var valOfChar = Integer.toHexString(it.code)
            if (valOfChar.length == 1) {
                valOfChar = "0$valOfChar"
            }

            when (STANDARD_CODE[standardCodeIterator]) {
                'R' -> {
                    g2d.color = Color.decode("#${valOfChar}${a}${b}")
                }
                'G' -> {
                    g2d.color = Color.decode("#${a}${valOfChar}${b}")
                }
                'B' -> {
                    g2d.color = Color.decode("#${a}${b}${valOfChar}")
                }
            }
            g2d.fillRect(rowCounter, colCounter, 1, 1)
            rowCounter++
            if (rowCounter > (usualableWidth - 1)) {
                colCounter++
                rowCounter = 0
            }
            counter++
        }
        g2d.dispose()
        FileManager.writeImageToFile(outFile, bfImg)
    }
}