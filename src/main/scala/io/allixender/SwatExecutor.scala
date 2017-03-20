package io.allixender

import java.io.File
import java.nio.file.Files

import scala.sys.process._

class SwatExecutor {

  lazy val SWAT = this.getClass.getResource("/swat2012_622_linux")

  def isSwatThere(): Boolean = {
    val executable = new File(SWAT.getPath)
    executable.exists && executable.isFile && executable.setExecutable(true) && executable.canExecute
  }

  def runSwat(runFolder: Option[String]): String = {

    val workDir = if (runFolder.isDefined) {
      val folder = new File(runFolder.get)
      if (folder.exists && folder.isDirectory) {
        folder
      } else {
        Files.createTempDirectory("swat-").toFile
      }
    } else {
      Files.createTempDirectory("swat-").toFile
    }

    val output = if (isSwatThere()) {

      try {
        val processOutput = Process(SWAT.getPath, workDir).!!

        val files = workDir.listFiles.toList
          .map(file => file.getPath).mkString(",\n")

        files + ",\n" + processOutput

      } catch {
        case e: RuntimeException => s"RuntimeException ${e.getMessage}" + s"${e.getStackTrace.mkString("\n - ")}"
        case e: Exception => s"Other Exception ${e.getMessage}" + s"${e.getStackTrace}"
        case _ : Throwable => s"Unspecified Error"
      }
    } else {
      s"SWAT not executable"
    }

    output

  }
}
