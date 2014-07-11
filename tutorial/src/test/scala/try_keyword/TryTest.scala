package try_keyword

import org.junit.{Before, Test, Assert}
import scala._
import scala.RuntimeException
import java.lang.IllegalArgumentException

class TryTest {

  private var res = new TestResource
  private var finallyCalled = false
  private var catchCalled = false
  private var exceptionThrowed = false

  @Before
  def setUp() {
    res = new TestResource
    finallyCalled = false
    catchCalled = false
    exceptionThrowed = false
  }

  @Test
  def testTry() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `;`


      Assert.fail("Exception expected")
    } catch {
      case ignore: RuntimeException =>
    }

    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  @Test
  def testTryFinally() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `finally` {
        finallyCalled = true
      }


      Assert.fail("Exception expected")
    } catch {
      case ex: RuntimeException => {
        Assert.assertTrue("In case of exception managed resource should be closed", res.closed)
      }
    }

    Assert.assertTrue("In case of exception finally block should be clalled", finallyCalled)
    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  @Test
  def testTryCatch() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `catch` {
        case ex: Throwable => catchCalled = true
      } `;`


    } catch {
      case ex: RuntimeException => Assert.fail("Exception does not expected")
    }

    Assert.assertTrue("In case of exception catch block should be clalled", catchCalled)
    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  @Test
  def testTryNotCatch() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `catch` {
        case ex: IllegalArgumentException => catchCalled = true
      } `;`


      Assert.fail("Exception expected")
    } catch {
      case ex: RuntimeException => exceptionThrowed = true
    }

    Assert.assertFalse("In case of unhandled exception catch block should not be called", catchCalled)
    Assert.assertTrue("In case of unhandled exception it should be throed upper", exceptionThrowed)
    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  @Test
  def testTryCatchFinally() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `catch` {
        case ex: Throwable => catchCalled = true
      } `finally` {
        finallyCalled = true
      }


    } catch {
      case ex: RuntimeException => Assert.fail("Exception does not expected")
    }

    Assert.assertTrue("In case of exception catch block should be called", catchCalled)
    Assert.assertTrue("In case of exception finally block should be called", finallyCalled)
    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  @Test
  def testTryNotCatchFinally() {
    try {


      `try` (res) {
        res: TestResource =>
          throw new RuntimeException
      } `catch` {
        case ex: IllegalArgumentException => catchCalled = true
      } `finally` {
        finallyCalled = true
      }


    } catch {
      case ex: RuntimeException => exceptionThrowed = true
    }

    Assert.assertFalse("In case of unhandled exception catch block should not be called", catchCalled)
    Assert.assertTrue("In case of unhandled exception it should be throed upper", exceptionThrowed)
    Assert.assertTrue("In case of unhandled exception finally block should be called", finallyCalled)
    Assert.assertTrue("Managed resource always should be closed", res.closed)
  }

  private class TestResource extends AutoCloseable {
    var closed = false

    def close() {
      closed = true
    }
  }

}
