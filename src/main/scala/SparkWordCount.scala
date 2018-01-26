import org.apache.spark.sql.SparkSession

object SparkWordCount {
  def main(args: Array[String]) {

    val session = SparkSession.builder().appName("wordcount").master("local[*]").getOrCreate()
    val sc = session.sparkContext

    /*creating an inputRDD to read text file (in.txt) through Spark context*/
    val input = sc.textFile("in.txt")
    /* Transform the inputRDD into countRDD */

    val count = input.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    /* saveAsTextFile method is an action that effects on the RDD */
    count.saveAsTextFile("outfile")
    println("OK")
  }
} 