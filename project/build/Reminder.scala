import sbt._

class ReminderProject(info: ProjectInfo) extends DefaultProject(info) {

  override def mainClass = Some("no.kvikshaug.reminder.Reminder")
  override def manifestClassPath = Some("scala-library.jar gson-1.4.jar joda-time-1.6.jar mail.jar")

  val javamail = "javamail" % "javamail" % "1.3.3"
  val jodatime = "joda-time" % "joda-time" % "1.6"
  val gson = "com.google.code.gson" % "gson" % "1.4"

}
