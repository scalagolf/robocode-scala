package scalagolf
import robocode._

class ThomasFirstScalaRobot extends Robot {
  override def run() {
    while (true) {
      ahead(100)
      turnGunRight(360)
      back(100)
      turnGunRight(360)
    }
  }

  override def onScannedRobot(e:ScannedRobotEvent) {
    fire(1)
  }
}
