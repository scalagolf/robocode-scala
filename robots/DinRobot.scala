package scalagolf

import robocode.HitByBulletEvent
import robocode.Robot
import robocode.ScannedRobotEvent

class DinRobot extends Robot {
  var timming = 100;
  var heat = false;
  
  override def run() {
    while (true) {
      if ( heat == true) {
        ahead(timming/2)
        heat = false;
      }
      ahead(timming)
      turnGunRight(360)
      back(timming)
      turnGunRight(360)
    }
  }

  override def onScannedRobot(e:ScannedRobotEvent) {
    fire(1)
  }
  
  override def onHitByBullet(e: HitByBulletEvent) {
    heat = true;
  } 
}
