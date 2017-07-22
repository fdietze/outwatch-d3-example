package outwatchd3

import scala.scalajs.js.JSApp
import org.scalajs.dom.raw.Element
import rxscalajs._
import outwatch.dom._
import outwatch.Sink
import org.scalajs.d3v4._

object Outwatchd3 extends JSApp {
  def main(): Unit = {
    val insertSink = Sink.create[Element] { elem =>
      val node = d3.select(elem)
      node.append("div").text("hello from d3")
    }
    val d3Container = div(insert --> insertSink)

    OutWatch.render(
      "#app",
      div(
        h1("Hello World"),
        d3Container
      )
    )
  }
}
