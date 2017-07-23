package outwatchd3

import scala.scalajs.js.JSApp
import scala.scalajs.js
import org.scalajs.dom.raw.{Element, Event}
import org.scalajs.dom.console
import rxscalajs._
import outwatch.dom._
import outwatch.Sink
import org.scalajs.d3v4._
import scala.scalajs.js.JSConverters._

object Outwatchd3 extends JSApp {
  def main(): Unit = {
    val data = createHandler[Double]()
    val dataList = data.scan(List.empty[Double]){ (acc, cur) => cur :: acc }

    // val insertSink = Sink.create[Element] { elem =>
    //   val node = d3.select(elem).selectAll[Double]("div")
    //   node.data(js.Array[Double](1,2,3))
    //     .enter()
    //     .append("div")
    //     .style("background-color", "#668833")
    //     .style("height", "20px")
    //     .style("width", (x:Double) => s"${x*10}px")
    // }

    val d3Container = div()
    val dataButton = button("add data", click((_: Event) => util.Random.nextDouble * 10) --> data)

    dataList.subscribe{ list =>
      d3Container.asProxy.elm.foreach { elem =>
        val node = d3.select(elem).selectAll[Double]("div").data(list.toJSArray)
        println(s"list: ${list.toJSArray}")
        console.log(elem)

        node.enter()
          .append("div")
          .style("background-color", "#668833")
          .style("height", "20px")

        node
          .style("width", (x: Double) => s"${x * 10}px")

        node.exit().remove()
      }
    }

    OutWatch.render(
      "#app",
      div(
        h1("Hello World"),
        dataButton,

        d3Container,
        children <-- dataList.map(_.map(x => div(x.toString)))
      )
    )
  }
}

/*
 *
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <script src="https://d3js.org/d3.v4.min.js"></script>
</head>

<body>
  <script>
    // Feel free to change or delete any of the code you see in this editor!
    var elem = d3.select("body").append("div");

        var node = elem.selectAll("div").data([1,2,3]);

        node.enter()
          .append("div")
          .style("background-color", "#668833")
          .style("height", "20px");

        node
          .style("width", function(x) {return (x*10)+"px";});

        node.exit().remove();


  </script>
</body>
 *
 * */
