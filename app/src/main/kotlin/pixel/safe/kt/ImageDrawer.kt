package pixel.safe.kt

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.io.IOException


class ImageDrawer {

    companion object {

        val MAX_IMAGE_WIDTH = 100

//        fun drawToFile(fileName: String) {
//            val bufImg = BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)
//            val g2d = bufImg.createGraphics()
//
//            g2d.color = Color.decode("#FF0000")
//            g2d.fillRect(10, 10, 10, 10)
//
//            g2d.dispose()
//            FileManager.writeImageToFile("${fileName}", bufImg)
////           try{
////               val file = File("${fileName}.png")
////               ImageIO.write(bufImg,"png",file)
////           }catch (e: Exception){
////               println("Error: ${e.localizedMessage}")
////           }
//        }


        fun addPixelsToExistingImage() {
            val img = FileManager.readInImageFile("cat.jpeg")
            img?.let {
                println("Image width: ${it.width}, Image Height: ${it.height}")
                val g2d = it.createGraphics()
                g2d.color = Color.decode("#FFFF00")
                g2d.fillRect(10, 10, 10, 10)

                g2d.dispose()
                FileManager.writeImageToFile("ggg", it)
            }
        }

    }


}