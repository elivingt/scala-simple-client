package example

import java.util.Scanner

import scala.io.StdIn

object HttpCaller extends App {

  @volatile var done = false
  print("type your client ID:")

  val client = StdIn.readLine()
  println("Type the Enter key to stop")

  new Thread {
    override def run() {
      httpCalLoop(client)
    }
  }.start()

  val keyboard = new Scanner(System.in)
  keyboard.nextLine()
  done = true
  print("finishing..")


  def httpCalLoop(client: String): Unit = {
    val r = scala.util.Random
    val url = s"http://localhost:8080/?clientId=$client"
    println(s"http request to $url")

    while (!done) {
      try {
        println(scala.io.Source.fromURL(url).mkString)
      }
      catch {
        case _: Exception => println("server blocked")
      }
      Thread.sleep(r.nextInt(500))  // sleep between 0-0.5 seconds
    }
  }

}




