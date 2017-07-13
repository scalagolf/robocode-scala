package scalagolf
import robocode._

class ThomasFirstScalaRobot extends Robot {
  override def run() {
    while (true) {
      turnGunRight(170)
      fire(2)
      ahead(510)
      turnGunRight(170)
      fire(2)
      back(510)
    }
  }

  override def onScannedRobot(e:ScannedRobotEvent) {
    fire(2)
  }
}
