package io.allixender

// In order to evaluate tasks, we'll need a Scheduler
import java.io.File
import java.nio.file.Files

import monix.execution.Scheduler

import scala.concurrent.Future
import scala.sys.process.Process

// A Future type that is also Cancelable
import monix.execution.CancelableFuture

// Task is in monix.eval
import monix.eval.Task

class SwatTask {

  // get the SWAT executable from resources
  lazy val SWAT = if (System.getProperty("os.name").startsWith("Win")) {
    this.getClass.getResource("/swat2012_64rel.exe")
  } else {
    this.getClass.getResource("/swat2012_622_linux")
  }

  /**
   * check if SWAT executable is available and can be executed
   * @return
   */
  def isSwatThere(): Boolean = {
    val executable = new File(SWAT.getPath)
    executable.exists && executable.isFile && executable.setExecutable(true) && executable.canExecute
  }

  /**
   * run SWAT in an optional given folder (presumable where the input files will be located)
   * @param runFolder folder of the SWAT inputfiles where to run
   * @return
   */
  def runSwat(runFolder: Option[String]): Future[String] = {

    // The default scheduler
    import monix.execution.Scheduler.Implicits.global

    // Creating a special scheduler meant for I/O
    lazy val ioScheduler = Scheduler.io(name = "my-io")

    val task = Task {

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
          case _: Throwable => s"Unspecified Error"
        }
      } else {
        s"SWAT not executable"
      }

      println(s"Ends on thread: ${Thread.currentThread.getName} ${SWAT.toExternalForm}")

      output
    }

    // Or you can convert it into a Future
    val future: CancelableFuture[String] = {
      val forked = Task.fork(task, ioScheduler)
      println("as future")
      forked.runAsync

    }

    // Printing the result asynchronously
    future.foreach(println)

    future
  }
}

/*
    // Tasks get evaluated only on runAsync!
    val cancelable = task.runOnComplete(
      new Callback[String] {
        def onSuccess(value: String): Unit =
          println(value)

        def onError(ex: Throwable): Unit =
          System.err.println(s"ERROR: ${ex.getMessage}")
      }
    )

    // If we change our mind...
    cancelable.cancel()

 */
