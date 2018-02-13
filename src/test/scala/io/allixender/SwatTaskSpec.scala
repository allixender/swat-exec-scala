package io.allixender

import java.io.File

import org.scalatest.{ MustMatchers, WordSpec }

class SwatTaskSpec extends WordSpec with MustMatchers {

  "Swat Task" should {
    "find path" in {
      val swat = new SwatTask()
      println(swat.SWAT)
      swat.isSwatThere() mustBe true
    }

    "run test case" in {
      val swat = new SwatTask()

      val testcase = this.getClass.getResource("/swattestcase")
      val testcaseFolder = new File(testcase.getPath)
      testcaseFolder.isDirectory mustBe true

      import scala.concurrent.ExecutionContext.Implicits.global

      val out = swat.runSwat(Some(testcaseFolder.getPath))
      println(s"run test case on thread: ${Thread.currentThread.getName}")

      out.map { s =>
        println(s)
        s must (include("Soil & Water Assessment Tool") and include("Execution successfully completed"))
      }
    }
  }

}
