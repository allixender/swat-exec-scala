package io.allixender

import java.io.File

import org.scalatest.{MustMatchers, WordSpec}

class SwatExecutorSpec extends WordSpec with MustMatchers {

  "Swat Executor" should {
    "find path" in {
      val swat = new SwatExecutor()
      swat.isSwatThere() mustBe true
    }

    "run test case" in {
      val swat = new SwatExecutor()

      val testcase = this.getClass.getResource("/swattestcase")
      val testcaseFolder = new File(testcase.getPath)
      testcaseFolder.isDirectory mustBe true

      val out = swat.runSwat(Some(testcaseFolder.getPath))
      out must (include("Soil & Water Assessment Tool") and include("Execution successfully completed"))
    }
  }

}
